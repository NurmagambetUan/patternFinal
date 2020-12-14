package com.example.pattern.Repositories;

import com.example.pattern.models.entities.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {

    List<Bank> findAllByDeletedAtIsNull();
    Bank findByIdAndDeletedAtIsNull(Long id);


}
