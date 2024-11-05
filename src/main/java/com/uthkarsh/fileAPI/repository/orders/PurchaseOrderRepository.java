package com.uthkarsh.fileAPI.repository.orders;

import com.uthkarsh.fileAPI.entity.orders.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {
}
