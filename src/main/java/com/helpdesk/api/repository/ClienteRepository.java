package com.helpdesk.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.helpdesk.api.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
