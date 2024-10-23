package com.sys.bio.back.repositories.order;

import com.sys.bio.back.models.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByPickUpDateBetween(Date startDate, Date endDate);

    @Query("SELECT COUNT(o) FROM Order o WHERE o.state IN ('EMITIDA', 'EN PROCESO', 'LISTO PARA RETIRO')")
    long countUnprocessedOrders();

}
