package com.uthkarsh.fileAPI.entity.orders;

import com.uthkarsh.fileAPI.entity.orders.OrderQuotation;
import com.uthkarsh.fileAPI.entity.organization.Company;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "purchaseOrder")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(name = "name")
    private String orderName;

    @Column(name = "url")
    private String orderUrlPath;

    @Column(name = "file_size")
    private Long fileSize;

    @Column(name = "file_type")
    private Long fileType;

    @Column(name = "upload_date")
    private LocalDate uploadDate;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    @Column(name = "upload_person")
    private String uploadBy;

    @Column(name = "status")
    private String status;

    @Column(name = "description")
    private String description;

    @Column(name = "file_hash")
    private String fileHash;

    // Foreign key and relations

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Company company;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    private List<OrderQuotation> quotation = new ArrayList<>();
}
