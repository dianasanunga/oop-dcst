package com.ups.oop.repository;

import com.ups.oop.entity.LoanDetail;
import org.springframework.data.repository.CrudRepository;

public interface LoanDetailRepository extends CrudRepository<LoanDetail, Long> {
}
