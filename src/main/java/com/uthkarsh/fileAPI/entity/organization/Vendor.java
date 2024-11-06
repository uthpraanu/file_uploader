package com.uthkarsh.fileAPI.entity.organization;

import com.uthkarsh.fileAPI.entity.general.Address;
import com.uthkarsh.fileAPI.entity.orders.OrderQuotation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vendor")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vendorId;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "phone_code")
    private int phoneCode;

    @Column(name = "phone_number", unique = true)
    private Long phoneNumber;

    @Column(name = "active")
    private Boolean active;

    // Foreign key and relations

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false, orphanRemoval = true)
    @JoinColumn(name = "address_id", referencedColumnName = "addressId", unique = true)
    private Address address;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "vendor")
    private List<OrderQuotation> quotation = new ArrayList<>();
}
