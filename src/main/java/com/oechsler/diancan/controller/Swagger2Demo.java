package com.oechsler.diancan.controller;
import com.oechsler.diancan.pojo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@RestController
@Api(value = "用户类")
@Slf4j
public class Swagger2Demo {
    private static final String TOKEN = "rocky";

    @ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer", paramType = "path")
    @RequestMapping(value = "user/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable(value = "id") Integer id) {

        log.info("用户ID{}进入方法",id);
        User user = new User();
        if (id == 1) {
            user.setAge(10);
            user.setName("zhangsan");
        }
        if (id == 2) {
            user.setAge(16);
            user.setName("lisi");
        }
        log.info("用户{}返回",user);
        return user;

    }

    /**
     * @deprecated  微信请求验证
     * @param response
     * @param req
     * @throws IOException
     */
    @RequestMapping("weChat")
    public void doGet(HttpServletResponse response, HttpServletRequest req) throws IOException {

        log.info("-----开始校验签名-----");
        String signature = req.getParameter("signature");
        String timestamp = req.getParameter("timestamp");
        String nonce = req.getParameter("nonce"); //随机数
        String echostr = req.getParameter("echostr");//随机字符串

        String sortStr=sort(TOKEN,timestamp,nonce);

        String mySignture=shal(sortStr);

        if(!"".equals(signature) && !"".equals(mySignture) && signature.equals(mySignture)){
            log.info("-----签名校验通过-----");
            response.getWriter().write(echostr);
        }else {
            log.info("-----校验签名失败-----");
        }

    }

    public String sort(String token, String timestamp, String nonce){
        String[] strArray = {token, timestamp, nonce};
        Arrays.sort(strArray);

        StringBuffer sb =new StringBuffer();

        for (String str:strArray){
            sb.append(str);
        }
        return sb.toString();
    }

    /**
     * 字符串进行shal加密
     * @param str
     * @return
     */
    public String shal(String str){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(str.getBytes());
            byte messageDigest[] = digest.digest();

            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

}
