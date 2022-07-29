package com.example.projeto.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Data
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @CPF
    @NotBlank(message = "Cpf obrigatorio!")
    @Column(name = "cpf")
    private String cpf;

    @Column(name = "nome", length = 100)
    @NotBlank(message = "Nome obrigatorio!")
    private String nome;

    @Email
    @NotBlank(message = "Email obrigatorio!")
    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "senha", length = 100)
    private String senha;
}
