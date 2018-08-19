package com.oechsler.diancan.vo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 *
 * 前端最外层对象
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)//null值不返回
public class ResultVo<T> {

    /** 错误码*/
    private Integer code;
    /** 提示信息*/
    private String msg;
    /** 返回内容*/
    private T data;

}
