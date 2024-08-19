package com.ups.oop.repository;

import com.ups.oop.entity.Client;
import org.springframework.data.repository.CrudRepository;



public interface ClientRepository  extends CrudRepository<Client, Long> {
}
