package com.microworld.vault.enums;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author birdbro
 * @date 15:52 2022-4-28
 **/
@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class EnumsResponse {


    private List<DictClsEnum> clsEnums;


}
