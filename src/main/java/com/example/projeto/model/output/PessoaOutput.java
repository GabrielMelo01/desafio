package com.example.projeto.model.output;

import com.example.projeto.model.entity.Pessoa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import javax.swing.text.MaskFormatter;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class PessoaOutput implements Serializable {

    private Integer id;
    private String cpf;
    private String nome;
    private String email;


    public PessoaOutput(Pessoa pessoa) {
        this.id = pessoa.getId();
        this.cpf = pessoa.getCpf();
        this.nome = pessoa.getNome();
        this.email = pessoa.getEmail();

    }

    @SneakyThrows
    public void setCpf(String cpf) {
        final MaskFormatter formatador =new MaskFormatter("###.###.###-##");
        formatador.setValidCharacters("1234567890");
        formatador.setValueContainsLiteralCharacters(false);
        this.cpf = formatador.valueToString(cpf);
    }
}
