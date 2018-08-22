<html>
<head>
<meta charset="UTF-8">
    <title>卖家商品列表</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/sell/css/style.css">
</head>
<body>
<div id="wrapper" class="toggled">
    <#--边栏 -->
    <#include "../common/nav.ftl">

    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered table-hover ">
                        <thead>
                        <tr>
                            <th>订单ID</th>
                            <th>姓名</th>
                            <th>手机号</th>
                            <th>地址</th>
                            <th>金额</th>
                            <th>订单状态</th>
                            <th>支付状态</th>
                            <th>创建时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                    <tbody>
                    <#list orderDtoPage.content as orderDto>
                    <tr class="success">
                        <td>${orderDto.orderId}</td>
                        <td>${orderDto.buyerName}</td>
                        <td>${orderDto.buyerPhone}</td>
                        <td>${orderDto.buyerAddress}</td>
                        <td>${orderDto.orderAmount}</td>
                        <td>${orderDto.getOrderStatusEnum(orderDto.orderStatus).getMessage()}</td>
                        <td>${orderDto.getPayStatusEnum(orderDto.payStatus).getMessage()}</td>
                        <td>${orderDto.createTime}</td>

                        <td>
                            <#if orderDto.getOrderStatusEnum(orderDto.orderStatus).getMessage()="完结"
                            ||orderDto.getOrderStatusEnum(orderDto.orderStatus).getMessage()="已取消">
                                <a  style="opacity: 0.2;"  href="#">取消</a>
                            <#else >
                                <a  href="/sell/seller/order/cancel?orderId=${orderDto.orderId}">取消</a>
                            </#if>
                        </td>
                        <td>
                            <a  href="/sell/seller/order/detail?orderId=${orderDto.orderId}">详情</a>
                        </td>
                    </tr>
                    </tbody>
                    </#list>
                    </table>
                </div>
                <!-- 分页-->
                <div class="col-md-12 column">
                    <ul class="pagination pull-right" >
                    <#if currentPage lte 1>
                        <li class="disabled">
                            <a href="#">上一页</a>
                        </li>
                    <#else >
                        <li >
                            <a href="/sell/seller/order/list?page=${currentPage -1}&size=10">上一页</a>
                        </li>
                    </#if>
                    <#list  1..orderDtoPage.getTotalPages() as index>
                        <#if currentPage==index>
                            <li class="disabled">
                                <a href="#">${index}</a>
                            </li>
                        <#else >
                            <li>
                                <a href="/sell/seller/order/list?page=${index}&size=10">${index}</a>
                            </li>
                        </#if>
                    </#list>
                    <#if currentPage gte orderDtoPage.getTotalPages()>
                        <li class="disabled">
                            <a href="#">下一页</a>
                        </li>
                    <#else >
                        <li >
                            <a href="/sell/seller/order/list?page=${currentPage+1}&size=10">下一页</a>
                        </li>
                    </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>

</div>

</body>


</html>
