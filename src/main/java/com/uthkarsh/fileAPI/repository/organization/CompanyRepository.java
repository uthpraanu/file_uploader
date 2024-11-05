package com.uthkarsh.fileAPI.repository.organization;

import com.uthkarsh.fileAPI.entity.organization.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
