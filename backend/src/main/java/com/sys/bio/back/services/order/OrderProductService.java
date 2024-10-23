package com.sys.bio.back.services.order;

import com.sys.bio.back.models.order.OrderProduct;

import java.util.List;
import java.util.Set;

public interface OrderProductService {

    OrderProduct addOrderProduct(OrderProduct orderProduct);
    OrderProduct updateOrderProduct(OrderProduct orderProduct);
    Set<OrderProduct> getOrderProducts();
    OrderProduct getOrderProduct(Long orderProductId);
    void deleteOrderProduct(Long orderProductId);
    /*
    void toggleOrderProductStatus(Long orderProductId, boolean newStatus);

     */

    List<OrderProduct> findAll();

    void saveAll(List<OrderProduct> orderProducts);

    List<OrderProduct> getOrderProductsByOrderId(Long orderId);
}
