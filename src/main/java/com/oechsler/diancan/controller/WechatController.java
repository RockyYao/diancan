package com.oechsler.diancan.controller;

import com.oechsler.diancan.dto.CartDTO;
import com.oechsler.diancan.enums.ResultEnum;
import com.oechsler.diancan.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author rocky
 * 微信
 */
@Controller
@RequestMapping("wechat")
@Slf4j
public class WechatController {

    @Autowired
    private WxMpService wxMpService;

    /**
     * 获取code重定向
     * @param returnUrl
     * @return
     */
    @GetMapping("/authorize")
public String authorize(@RequestParam("returnUrl")String  returnUrl){

    String url="http://wiwwrj.natappfree.cc/sell/wechat/userInfo";
    String recriptUrl= wxMpService.oauth2buildAuthorizationUrl(url,WxConsts.OAuth2Scope.SNSAPI_USERINFO,returnUrl);
    log.info("【微信网页授权】获取code,result={}",recriptUrl);

    return "redirect:"+recriptUrl;
}


@GetMapping("/userInfo")
public String userInfo(@RequestParam("code") String code,@RequestParam("state")String returnUrl){
    WxMpOAuth2AccessToken wxMpOAuth2AccessToken=new WxMpOAuth2AccessToken();
    try {
        //拿到ACCESSToken
        wxMpOAuth2AccessToken= wxMpService.oauth2getAccessToken(code);
    } catch (WxErrorException e) {
        log.error("【微信网页授权异常】");
        throw new SellException(ResultEnum.WECHAT_MP_ERROR,e.getError().getErrorMsg());

    }
    String openId= wxMpOAuth2AccessToken.getOpenId();
    log.info("code={},openId={}",code,openId);

    return "redirect:"+returnUrl+"?openid="+openId;

}


}
