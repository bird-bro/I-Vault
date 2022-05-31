package com.microworld.vault.modules.system.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author birdBro
 * @since 2022-04-25
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class OrgUser extends Model<OrgUser> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 用户ID
     */
    private Integer uid;

    /**
     * 机构ID
     */
    private Integer oid;

    /**
     * 部门ID
     */
    private Integer did;

    /**
     * 角色-默认
     */
    private Integer roleDef;

    /**
     * 职位
     */
    private Integer job;

    /**
     * 默认机构 0否 1是
     */
    private Integer orgDef;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
