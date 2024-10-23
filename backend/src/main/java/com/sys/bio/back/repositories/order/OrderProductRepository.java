package com.sys.bio.back.repositories.order;

import com.sys.bio.back.models.cutting.CutBox;
import com.sys.bio.back.models.order.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {

    List<OrderProduct> findByOrderOrderId(Long orderId);

}
