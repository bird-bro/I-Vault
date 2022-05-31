package com.microworld.vault.modules.dict.request;

import com.microworld.common.BasisPage;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author bird
 * @date 2022-4-12 15:44
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class DictTypePageRequest extends BasisPage {

    @ApiModelProperty(value = "分类", required = false)
    private Integer cls;

}
