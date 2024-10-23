package com.sys.bio.back.services.order;

import com.sys.bio.back.controllers.user.AuthenticationController;
import com.sys.bio.back.models.order.Order;
import com.sys.bio.back.repositories.order.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepo;

    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

    @Override
    public Order createOrder(Order order) {
        return orderRepo.save(order);
    }
    @Override
    public Order updateOrder(Order order) {
        return orderRepo.save(order);
    }

    @Override
    public Set<Order> getOrders() {
        return new LinkedHashSet<>(orderRepo.findAll());
    }

    @Override
    public Order getOrder(Long orderId) {
        return orderRepo.findById(orderId).get();
    }

    @Override
    public void deleteOrder(Long orderId) {
        Order order = new Order();
        order.setOrderId(orderId);
        orderRepo.delete(order);
    }


    public List<Order> getOrdersByPickUpDateRange(Date startDate, Date endDate) {
        return orderRepo.findByPickUpDateBetween(startDate, endDate);
    }

    @Override
    public long getUnprocessedOrdersCount() {
        return orderRepo.countUnprocessedOrders();
    }



}
