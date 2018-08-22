<html>
<head>
    <meta charset="UTF-8">
    <title>商品详细表</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/sell/css/style.css">
</head>
<body>
<div id="wrapper" class="toggled">
<#--边栏 -->
<#include "../common/nav.ftl">

    <div id="page-content-wrapper">

        <div class="container">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>
                                订单ID
                            </th>
                            <th>
                                订单总金额
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>
                            ${orderDto.orderId}
                            </td>
                            <td>
                            ${orderDto.orderAmount}
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <table class="table">
                        <thead>
                        <tr>
                            <th>
                                商品ID
                            </th>
                            <th>
                                商品名称
                            </th>
                            <th>
                                价格
                            </th>
                            <th>
                                数量
                            </th>
                            <th>
                                总额
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list orderDto.orderDetailList as  orderDetail>
                        <tr>
                            <td>
                            ${orderDetail.detailId}
                            </td>
                            <td>
                            ${orderDetail.productName}
                            </td>
                            <td>
                            ${orderDetail.productPrice}
                            </td>
                            <td>
                            ${orderDetail.productQuantity}
                            </td>
                            <td>
                            ${orderDetail.productPrice*orderDetail.productQuantity}
                            </td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                <#if orderDto.orderStatus=0>
                    <div class="col-md-12 column">
                        <a href="/sell/seller/order/finish?orderId=${orderDto.orderId}" type="button" class="btn btn-default btn-success">完结订单</a>
                        <a href="/sell/seller/order/cancel?orderId=${orderDto.orderId}" type="button" class="btn btn-default btn-info">取消订单</a>
                    </div>
                </#if>
                </div>
            </div>
        </div>


    </div>
</div>






</body>
</html