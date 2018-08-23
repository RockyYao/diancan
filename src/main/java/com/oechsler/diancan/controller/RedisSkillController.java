package com.oechsler.diancan.controller;
import com.oechsler.diancan.enums.ResultEnum;
import com.oechsler.diancan.exception.SellException;
import com.oechsler.diancan.util.KeyUtil;
import com.oechsler.diancan.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.text.DateFormat;
import java.util.HashMap;
import java.util.Map;
/**
 * 模拟秒杀
 * @author rocky
 */
@RestController
public class RedisSkillController {
    @Autowired
private RedisUtil redisUtil;
static Map<String,Integer> products;
static Map<String,Integer> stocks;
static Map<String,Object> orders;
private static final int TIMEOUT=10*1000;
static long timetotal;
    {
        products= new HashMap();
        products.put("123",1000);
        stocks= new HashMap();
        stocks.put("123",1000);
        orders=new HashMap<>();
    }

    @RequestMapping("a")
    private String queryMap(String productId){
        return "国庆活动，皮蛋限量"+products.get(productId)+"份，还剩下"+stocks.get(productId)+"份,目前人数"+orders.size()+"人";
    }

    @RequestMapping("b")
    public  String orderPro(String productId){


        long time=System.currentTimeMillis()+TIMEOUT;
        if (!redisUtil.lock(productId,String.valueOf(time))){
            System.out.println("没排到队下次再来");
        }

        //加锁
        long startTime=System.currentTimeMillis();
        int stocksNum=stocks.get(productId);

        if (stocksNum==0){
            throw new SellException(ResultEnum.ORDER_NOT_EXIT);

        }else {
          orders.put(KeyUtil.genUniqueKey(),productId);
          stocksNum=stocksNum-1;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stocks.put(productId,stocksNum);
            System.out.println("访问人数"+orders.size()+"人");
            System.out.println("还剩下"+stocks.get(productId)+"份");
        }
        long endTime=System.currentTimeMillis();

        timetotal=(endTime-startTime)+timetotal;
        //解锁
        redisUtil.unlock(productId,String.valueOf(time));
        System.out.println(timetotal);
        return "国庆活动，皮蛋限量"+products.get(productId)+"份，还剩下"+stocks.get(productId)+"份,目前人数"+orders.size()+"人";

    }












}
