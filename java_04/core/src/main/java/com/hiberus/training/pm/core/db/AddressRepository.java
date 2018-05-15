package com.hiberus.training.pm.core.db;

import com.hiberus.training.pm.core.db.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
