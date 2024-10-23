package com.sys.bio.back.services.order;


import com.sys.bio.back.models.order.OrderProduct;
import com.sys.bio.back.repositories.order.OrderProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class OrderProductServiceImpl implements OrderProductService {

    @Autowired
    private OrderProductRepository orderProductRepo;

    @Override
    public OrderProduct addOrderProduct(OrderProduct orderProduct) {
        return orderProductRepo.save(orderProduct);
    }

    @Override
    public OrderProduct updateOrderProduct(OrderProduct orderProduct) {
        return orderProductRepo.save(orderProduct);
    }

    @Override
    public Set<OrderProduct> getOrderProducts() {
        return new LinkedHashSet<>(orderProductRepo.findAll());
    }

    @Override
    public OrderProduct getOrderProduct(Long orderProductId) {
        return orderProductRepo.findById(orderProductId).get();
    }

    @Override
    public void deleteOrderProduct(Long orderProductId) {
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setOrderProductId(orderProductId);
        orderProductRepo.delete(orderProduct);
    }
/*
    @Override
    public void toggleOrderProductStatus(Long orderProductId, boolean newStatus) {
        OrderProduct orderProduct = orderProductRepo.findById(orderProductId).orElse(null);
        if (orderProduct != null) {
            orderProduct.setEnabled(newStatus);
            orderProductRepo.save(orderProduct);
        }
    }
    */

    @Override
    public List<OrderProduct> findAll() {
        return  orderProductRepo.findAll();
    }

    @Override
    public void saveAll(List<OrderProduct> orderProducts) {
        orderProductRepo.saveAll(orderProducts);
    }

    @Override
    public List<OrderProduct> getOrderProductsByOrderId(Long orderId) {
        return orderProductRepo.findByOrderOrderId(orderId);
    }


}
