package com.sys.bio.back.services.order;

import com.sys.bio.back.models.order.Order;
import com.sys.bio.back.models.order.OrderProduct;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface OrderService {

    Order createOrder(Order order);

    Order updateOrder(Order order);
    Set<Order> getOrders();
    Order getOrder(Long orderId);
    void deleteOrder(Long orderId);
    List<Order> getOrdersByPickUpDateRange(Date startDate, Date endDate);

    long getUnprocessedOrdersCount();
}
