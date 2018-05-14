package com.hiberus.training.pm.core.db;

import com.hiberus.training.pm.core.db.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<Phone, Long> {
    Phone findByPhoneNumber(String phoneNumber);
}
