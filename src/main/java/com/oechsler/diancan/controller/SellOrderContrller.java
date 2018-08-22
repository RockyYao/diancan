package com.oechsler.diancan.controller;
import com.oechsler.diancan.dto.OrderDto;
import com.oechsler.diancan.enums.ResultEnum;
import com.oechsler.diancan.exception.SellException;
import com.oechsler.diancan.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.Map;

/**
 * 卖家端订单
 * @author rocky
 */
@RequestMapping("/seller/order")
@Controller
@Slf4j
public class SellOrderContrller {



    @Autowired
    private OrderService orderService;
@GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1")Integer page,@RequestParam(value = "size",defaultValue = "10")Integer size,Map<String,Object> map){
    PageRequest pageRequest=new PageRequest(page-1,size);

            Page<OrderDto> orderDtoPage= orderService.findList(pageRequest);


            map.put("currentPage",page);
            map.put("orderDtoPage",orderDtoPage);
            return new ModelAndView("order/list",map);

}

@GetMapping("/cancel")
public ModelAndView cancel(@RequestParam("orderId")String orderId,Map<String,Object> map){

        try {
            OrderDto orderDto=orderService.findOne(orderId);
            orderService.cancel(orderDto);
        }
        catch (SellException e){
                log.error("【卖家取消订单】查询不到订单");
                map.put("msg", e.getMessage());
                map.put("url","/sell/seller/order/list");
                return new ModelAndView("common/error",map);

        }

             map.put("msg", ResultEnum.SUCCESS.getMessage());
             map.put("url","/sell/seller/order/list");
             return new ModelAndView("common/success",map);
}

@GetMapping("/finish")
    public ModelAndView finish(@RequestParam("orderId")String orderId,Map<String,Object> map){
    try {
        OrderDto orderDto=orderService.findOne(orderId);
        orderService.finish(orderDto);
    }
    catch (SellException e){
        log.error("【卖家结束订单】查询不到订单");
        map.put("msg", e.getMessage());
        map.put("url","/sell/seller/order/list");
        return new ModelAndView("common/error",map);

    }

    map.put("msg", ResultEnum.SUCCESS.getMessage());
    map.put("url","/sell/seller/order/list");
    return new ModelAndView("common/success",map);


}




@GetMapping("detail")
    public ModelAndView detail(@RequestParam("orderId")String orderId,Map<String,Object> map){
    try {
        OrderDto orderDto=orderService.findOne(orderId);
        map.put("orderDto",orderDto);
    }
    catch (SellException e){
        log.error("【卖家取消订单】查询不到订单");
        map.put("msg", e.getMessage());
        map.put("url","/sell/seller/order/list");
        return new ModelAndView("common/error",map);

    }

    return new ModelAndView("order/detail",map);

}

}
