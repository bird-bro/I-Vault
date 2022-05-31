package com.microworld.vault.modules.dict.request;

import com.microworld.common.BasisPage;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author bird
 * @date 2022-4-13 9:54
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class DictDataPageRequest extends BasisPage {


    @ApiModelProperty(value = "类型ID", required = true)
    private Integer typeId;

}
