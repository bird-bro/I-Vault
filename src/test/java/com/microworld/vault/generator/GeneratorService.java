package com.microworld.vault.generator;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import org.junit.Test;


import java.util.ArrayList;
import java.util.List;

/**
 * @author birdbro
 * @date 14:19 2022-4-28
 **/
public class GeneratorService {


    //需要的表名，多个表名传数组
    static String[] TABLE = {"dict_city"};
    static String DBURL = "jdbc:mysql://rm-8vbe18d772cn401jdvo.mysql.zhangbei.rds.aliyuncs.com:3306/sys?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC";
    static String USERNAME = "visual_root";
    static String PASSWORD="visual@1026";
    static String BASE_PACKAGE="com.microworld.vault.modules.dict";
    static String PATH_PRO = "/src/main/resources/mapper/dict/";


//    @Test
//    public void generateCode() {
    public static void main(String[] args){
        //user -> UserService, 设置成true: user -> IUserService
        boolean serviceNameStartWithI = true;
        generateByTables(serviceNameStartWithI, BASE_PACKAGE, TABLE);
    }

    public static void generateByTables(boolean serviceNameStartWithI, String packageName, String... tableNames) {
        GlobalConfig globalConfig = new GlobalConfig();
        String dbUrl = DBURL;
        String projectPath = System.getProperty("user.dir");
        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        PackageConfig packageConfig = new PackageConfig();
        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {
            }
        };

        // 数据源配置
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(dbUrl)
                .setUsername(USERNAME)
                .setPassword(PASSWORD)
                .setDriverName("com.mysql.cj.jdbc.Driver");

        // 策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setEntityLombokModel(true)  //实体类使用lombok
                .setCapitalMode(true)
                .setNaming(NamingStrategy.underline_to_camel)   //数据表命名策略
                .setColumnNaming(NamingStrategy.underline_to_camel) //数据表列生成策略
                .setRestControllerStyle(true)   //rest风格controller
                //.setEntityTableFieldAnnotationEnable(true) //字段注释
                .setInclude(tableNames);    //需要生成的表

        // 全局配置
        globalConfig.setActiveRecord(false)
                .setDateType(DateType.ONLY_DATE)
                .setAuthor("birdBro")
                .setOutputDir(projectPath + "/src/main/java")   //代码生成目录
                .setFileOverride(true)
                .setActiveRecord(true)            // 开启 activeRecord 模式
                .setEnableCache(false)           // XML 二级缓存
                .setBaseResultMap(true)         // XML ResultMap
                .setBaseColumnList(true)        // XML columList
                .setOpen(false);                //生成后打开文件夹
        if (!serviceNameStartWithI) {
            globalConfig.setServiceName("%sService");
        }
        packageConfig.setParent(BASE_PACKAGE)        // 自定义包路径
                .setController("controller")         // 这里是控制器包名，默认 web
                .setEntity("entity")
                .setMapper("mapper")
                .setService("service")
                .setServiceImpl("service.impl");

        // 如果模板引擎是 freemarker
//         String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名
                return projectPath + PATH_PRO + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        injectionConfig.setFileOutConfigList(focList);
        templateConfig.setXml(null);

        new AutoGenerator().setGlobalConfig(globalConfig)

                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig)
                .setTemplateEngine(new VelocityTemplateEngine())
                .setCfg(injectionConfig)
                .setTemplate(templateConfig)
                .execute();
    }


    private void generateByTables(String packageName, String... tableNames) {
        generateByTables(true, packageName, tableNames);
    }

}
