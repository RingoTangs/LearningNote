package com.ymy.boot.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ResponseBean", description = "封装返回结果")
public class ResponseBean {

    @ApiModelProperty(value = "状态码")
    private Integer status;

    @ApiModelProperty(value = "信息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Object data;

    public static ResponseBean ok(String message) {
        return new ResponseBean(200, message, null);
    }

    public static ResponseBean ok(String message, Object data) {
        return new ResponseBean(200, message, data);
    }

    public static ResponseBean failure(String message) {
        return new ResponseBean(500, message, null);
    }

    public static ResponseBean failure(String message, Object data) {
        return new ResponseBean(500, message, data);
    }
}
