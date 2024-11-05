package com.uthkarsh.fileAPI.entity.organization;

import com.uthkarsh.fileAPI.entity.general.Address;
import com.uthkarsh.fileAPI.entity.orders.PurchaseOrder;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "company")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyId;

    @Column(name = "name")
    private String companyName;

    @Column(name = "email")
    private String companyEmail;

    @Column(name = "phone_code")
    private int companyPhoneCode;

    @Column(name = "phone_number")
    private long companyPhoneNumber;

    @Column(name = "active")
    private Boolean active;

    // Foreign keys or RelationShips

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false, orphanRemoval = true)
    @JoinColumn(name = "address_id", referencedColumnName = "addressId", unique = true)
    private Address address;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "company")
    private List<PurchaseOrder> orders = new ArrayList<>();
}
