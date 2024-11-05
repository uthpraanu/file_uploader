package com.uthkarsh.fileAPI.repository.general;

import com.uthkarsh.fileAPI.entity.general.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
