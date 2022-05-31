package com.microworld.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author birdbro
 * @date 15:10 2022-4-28
 **/
@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class BasisPage {
    @Transient
    @ApiModelProperty(value = "当前页码", required = true)
    @NotNull(message = "当前页码--[pageNum] constraint is declared as NOT NULL！")
    @Min(value = 1, message = "当前页码需大于1")
    private int pageNum = 1;

    @Transient
    @ApiModelProperty(value = "当前页显示数量", required = true)
    @NotNull(message = "当前页显示数量--[pageSize] constraint is declared as NOT NULL！")
    @Min(value = 1, message = "当前页显示数量需大于1条")
    @Max(value = 100,message = "当前页显示数量不能大于100")
    private int pageSize = 50;
}
