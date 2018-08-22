package com.oechsler.diancan.exception;

import com.oechsler.diancan.enums.ResultEnum;

/**
 * @author rocky
 */


public class SellException extends RuntimeException {
private Integer code;
public SellException(ResultEnum resultEnum){

    super(resultEnum.getMessage());
    this.code=resultEnum.getCode();

}
public SellException(ResultEnum resultEnum,String message){
    super(message);
    this.code=resultEnum.getCode();
}

}
