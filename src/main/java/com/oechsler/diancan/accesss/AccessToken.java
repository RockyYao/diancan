package com.oechsler.diancan.accesss;

import lombok.Data;

@Data
public class AccessToken {
    private String tokenName; //获取到的凭证
    private int expireSecond;    //凭证有效时间  单位:秒
}
