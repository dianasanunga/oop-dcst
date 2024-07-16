package com.ups.oop.repository;

import com.ups.oop.entity.Animal;
import org.springframework.data.repository.CrudRepository;

public interface AnimalRepository extends CrudRepository<Animal, Long> {
}
