package com.njit.entity;

import java.sql.Date;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (StoreCompare)表实体类
 *
 * @author makejava
 * @since 2020-05-09 09:32:55
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "门店信息表")
public class StoreCompare extends Model<StoreCompare> {
    @ApiModelProperty(value = "老店号")
    private Integer oldStoreNo;
    @ApiModelProperty(value = "新店号")
    private Integer newStoreNo;
    @ApiModelProperty(value = "门店描述")
    private String name;
    @ApiModelProperty(value = "地区号")
    private Integer areaNo;
    @ApiModelProperty(value = "开店日期")
    private Date startDate;
    @ApiModelProperty(value = "EBS店号")
    private Integer ebsStoreNo;
    @ApiModelProperty(value = "门店状态")
    private Integer status;

    @Override
    public String toString() {
        return "StoreCompare{" +
            "oldStoreNo=" + oldStoreNo +
            ", newStoreNo=" + newStoreNo +
            ", name='" + name + '\'' +
            ", areaNo=" + areaNo +
            ", startDate=" + startDate +
            ", ebsStoreNo=" + ebsStoreNo +
            ", status=" + status +
            '}';
    }
}