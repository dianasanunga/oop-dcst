package com.ups.oop.repository;
import com.ups.oop.entity.Loan;
import org.springframework.data.repository.CrudRepository;

public interface LoanRepository extends CrudRepository<Loan, Long>{
}
