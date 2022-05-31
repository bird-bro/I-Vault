package com.microworld.vault.modules.system.request;

import com.microworld.common.BasisPage;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author bird
 * @date 2022-4-15 13:54
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class MenuPageRequest extends BasisPage {

    /**
     * 标题
     */
    private String title;

    /**
     * 子系统
     */
    private Integer app;

    /**
     * 父节点
     */
    private Integer parentId;

}
