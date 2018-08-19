package com.oechsler.diancan.convertor;

import com.oechsler.diancan.dto.OrderDto;
import com.oechsler.diancan.entity.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author rocky
 *  转换器Order转成OrderDTO
 */
public class OrderMaster2OrderDTO {

    public  static OrderDto convert(OrderMaster orderMaster){

        OrderDto orderDto=new OrderDto();
        BeanUtils.copyProperties(orderMaster,orderDto);
        return orderDto;
    }

    public static List<OrderDto> convert(List<OrderMaster > orderMasters){

     return  orderMasters.stream().map(e->convert(e)).collect(Collectors.toList());

    }

}
