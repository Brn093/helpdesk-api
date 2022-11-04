package com.helpdesk.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.helpdesk.api.model.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {

}
