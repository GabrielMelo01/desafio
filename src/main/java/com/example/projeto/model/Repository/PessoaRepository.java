package com.example.projeto.model.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projeto.model.entity.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
    
}
