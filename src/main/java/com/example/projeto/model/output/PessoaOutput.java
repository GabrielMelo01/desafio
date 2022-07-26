package com.example.projeto.model.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import javax.swing.text.MaskFormatter;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaOutput implements Serializable {

    private Integer id;
    private String cpf;
    private String nome;
    private String email;
    private String senha;


    @SneakyThrows
    public void setCpf(String cpf) {
        final MaskFormatter formatador =new MaskFormatter("###.###.###-##");
        formatador.setValidCharacters("1234567890");
        formatador.setValueContainsLiteralCharacters(false);
        this.cpf = formatador.valueToString(cpf);
    }
}
