package com.microworld.vault.modules.dict.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 城市行政区
 * </p>
 *
 * @author birdBro
 * @since 2022-04-11
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class DictCity extends Model<DictCity> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 地区code
     */
    private String code;

    /**
     * 地区名称
     */
    private String name;

    /**
     * 上级id
     */
    private Integer upId;

    /**
     * 地区简称
     */
    private String abbreviation;

    /**
     * 级别
     */
    private Integer level;

    /**
     * 区号
     */
    private String cityCode;

    /**
     * 邮政编码
     */
    private String zipCode;

    /**
     * 直辖地全称
     */
    private String merger;

    /**
     * 拼音
     */
    private String pinyin;

    /**
     * 维度
     */
    private String lng;

    /**
     * 经度
     */
    private String lat;

    /**
     * 首字母
     */
    private String initial;


    @Override
    public Serializable pkVal() {
        return this.id;
    }


    @TableField(exist = false)
    @ApiModelProperty(value = "子节点")
    private List<DictCity> children;

}
