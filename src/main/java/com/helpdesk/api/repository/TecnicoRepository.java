package com.helpdesk.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.helpdesk.api.model.Tecnico;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {

}
