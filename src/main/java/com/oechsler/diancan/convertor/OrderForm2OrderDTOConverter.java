package com.oechsler.diancan.convertor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.oechsler.diancan.dto.OrderDto;
import com.oechsler.diancan.entity.OrderDetail;
import com.oechsler.diancan.enums.ResultEnum;
import com.oechsler.diancan.exception.SellException;
import com.oechsler.diancan.from.OrderFrom;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rocky
 * From表单转成orderDto
 */
@Slf4j
public class OrderForm2OrderDTOConverter {

    public static OrderDto convert(OrderFrom orderFrom){

        Gson gson=new Gson();
        OrderDto orderDto=new OrderDto();
        orderDto.setBuyerName(orderFrom.getName());
        orderDto.setBuyerPhone(orderFrom.getPhone());
        orderDto.setBuyerAddress(orderFrom.getAddress());
        orderDto.setBuyerOpenid(orderFrom.getOpenid());
        List<OrderDetail> orderDetails=new ArrayList<>();
        try {
            orderDetails=gson.fromJson(orderFrom.getItems(),new TypeToken<List<OrderDetail>>(){}.getType());
        }catch (Exception e){
            log.error("【对象转换】错误,String={}",orderDto);
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        orderDto.setOrderDetailList(orderDetails);

        return orderDto;


    }

}
