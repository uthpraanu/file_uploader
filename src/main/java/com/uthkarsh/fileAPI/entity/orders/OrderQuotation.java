package com.uthkarsh.fileAPI.entity.orders;

import com.uthkarsh.fileAPI.entity.organization.Vendor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(
        name = "order_quotation",
        uniqueConstraints = @UniqueConstraint(columnNames = {"vendor_id", "order"})
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderQuotation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quotationId;

    @Column(name = "name")
    private String name;

    @Column(name = "file_size")
    private Long fileSize;

    @Column(name = "file_type")
    private String fileType;

    @Column(name = "upload_date")
    private LocalDate uploadDate;

    @Column(name = "upload_by")
    private String uploadBy;

    @Column(name = "status")
    private String status;

    @Column(name = "description")
    private String description;

    @Column(name = "url")
    private String path;

    @Column(name = "file_hash")
    private String fileHash;

    // Foreign key and relations

    @ManyToOne(fetch = FetchType.LAZY)
    private Vendor vendor_id;

    @ManyToOne(fetch = FetchType.LAZY)
    private PurchaseOrder order;
}
