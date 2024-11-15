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
        uniqueConstraints = @UniqueConstraint(columnNames = {"vendor_id", "order_id"})
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderQuotation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quotationId;

    @Column(name = "name", nullable = false)
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

    @Column(name = "file_hash", nullable = false)
    private String fileHash;

    @Column(name = "file_url", nullable = false)
    private String path;

    // Foreign key and relations

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id", nullable = false)
    private Vendor vendor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private PurchaseOrder order;
}
