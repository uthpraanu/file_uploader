package com.uthkarsh.fileAPI.repository.orders;

import com.uthkarsh.fileAPI.entity.orders.OrderQuotation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderQuotationRepository extends JpaRepository<OrderQuotation, Long> {
}
