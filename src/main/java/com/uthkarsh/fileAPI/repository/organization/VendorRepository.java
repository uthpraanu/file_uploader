package com.uthkarsh.fileAPI.repository.organization;

import com.uthkarsh.fileAPI.entity.organization.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
