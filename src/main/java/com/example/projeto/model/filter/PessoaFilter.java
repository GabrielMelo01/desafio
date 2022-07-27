package com.example.projeto.model.filter;

import com.example.projeto.core.searchSpec.GenericSpesification;
import com.example.projeto.core.searchSpec.SearchCriteria;
import com.example.projeto.core.searchSpec.SearchOperation;
import com.example.projeto.model.entity.Pessoa;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Getter
@Setter
public class PessoaFilter {

    private Integer id;
    private String cpf;
    private String nome;
    private String email;
    private String senha;

    public Specification<Pessoa> getSpecification() {
        GenericSpesification<Pessoa> genericSpesification = new GenericSpesification<Pessoa>();

        if(Objects.nonNull(getId())) {
            genericSpesification.add(new SearchCriteria("id",getId(), SearchOperation.EQUAL));
        }
        if(Objects.nonNull(getNome())) {
            genericSpesification.add(new SearchCriteria("nome",getNome(), SearchOperation.MATCH));
        }
        if(Objects.nonNull(getCpf())) {
            genericSpesification.add(new SearchCriteria("cpf",getCpf(), SearchOperation.EQUAL));
        }

        return genericSpesification;
    }
}
