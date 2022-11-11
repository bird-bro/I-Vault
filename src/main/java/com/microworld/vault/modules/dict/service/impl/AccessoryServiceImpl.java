package com.microworld.vault.modules.dict.service.impl;

import cn.hutool.core.util.IdUtil;
import com.bird.common.tools.PictureTool;
import com.microworld.vault.modules.dict.service.IAccessoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author bird
 * @date 2022-4-18 11:15
 **/
@Slf4j
@Service
@Configuration
public class AccessoryServiceImpl implements IAccessoryService {

    @Value("${upload.folder}")
    private String folder;
    @Value("${upload.head-photo}")
    private String headPhoto;


    @Override
    public String headIcon (String value){
        try {
            String directory = (new SimpleDateFormat("yyyy/MM/dd")).format(new Date());
            File dir = new File(folder + headPhoto + directory);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            return PictureTool.headIcon(
                    value, headPhoto + directory,
                    IdUtil.simpleUUID(),
                    folder,
                    headPhoto
            );
        }catch (IOException e){
            log.error("头像生成失败！",e.getMessage());
            return "";
        }
    }


}
