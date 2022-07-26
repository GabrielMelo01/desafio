package com.example.projeto.model.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaInput implements Serializable {
    private Integer id;
    @NotBlank(message = "Cpf obrigatorio!")
    private String cpf;
    @NotBlank(message = "Nome obrigatorio!")
    private String nome;
    @Email
    @NotBlank(message = "Email obrigatorio!")
    private String email;
    private String senha;
}
