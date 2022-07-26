package com.example.projeto.model.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projeto.model.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer>, JpaSpecificationExecutor<Pessoa> {
}
