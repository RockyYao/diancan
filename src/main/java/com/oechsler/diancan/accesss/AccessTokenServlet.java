/*
package com.oechsler.diancan.accesss;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.oechsler.diancan.util.NetWorkUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@EnableAsync
@Controller
@RequestMapping("/a")
public class AccessTokenServlet extends HttpServlet {

    final String appId = getInitParameter("appId");
    final String appSecret = getInitParameter("appSecret");


  @Override
    public void init() throws ServletException{

      log.info("-----启动AccessTokenServlet-----");
      super.init();

     AccessTokenService accessTokenService=new AccessTokenService();
     accessTokenService.getAccessToken();

     log.info("token{}",AccessTokenInfo.accessToken);

  }






    public AccessToken getAccessToken(String appId, String appSecret) {
        NetWorkUtil netHelper = new NetWorkUtil();
        */
/**
         * 接口地址为https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET，其中grant_type固定写为client_credential即可。
         *//*

        String Url = String.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s", appId, appSecret);
        //此请求为https的get请求，返回的数据格式为{"access_token":"ACCESS_TOKEN","expires_in":7200}
        String result = netHelper.getHttpsResponse(Url, "");
        log.info("获取到的access_token="+result);

        //使用FastJson将Json字符串解析成Json对象
        JSONObject json = JSON.parseObject(result);
        AccessToken token = new AccessToken();
        token.setTokenName(json.getString("access_token"));
        token.setExpireSecond(json.getInteger("expires_in"));
        return token;
    }


    private class AccessTokenService{

      @Async
      public void  getAccessToken(){

          while(true){

              try {
              AccessTokenInfo.accessToken=AccessTokenServlet.this.getAccessToken(appId,appSecret);
              if (AccessTokenInfo.accessToken!=null) {
                      Thread.sleep(7000 * 1000);
              }else {
                  Thread.sleep(1000 * 3);
                 }
              } catch (InterruptedException e) {
                  log.error(e.getMessage());
                  e.printStackTrace();
              }

          }



      }


    }






}
*/
