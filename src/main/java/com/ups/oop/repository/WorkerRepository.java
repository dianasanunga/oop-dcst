package com.ups.oop.repository;

import com.ups.oop.entity.Worker;
import org.springframework.data.repository.CrudRepository;

public interface WorkerRepository  extends CrudRepository<Worker, Long> {
}
