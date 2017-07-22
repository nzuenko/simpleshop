package com.nz.simpleshop.repository;

import com.nz.simpleshop.model.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Long> {
    Client getClientByLogin(String login);
}