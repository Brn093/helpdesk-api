package com.helpdesk.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.helpdesk.api.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

}
