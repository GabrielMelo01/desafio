package com.example.projeto.model.Service;

import java.util.List;

import com.example.projeto.model.PessoaFilter;
import com.example.projeto.model.Service.exceptions.ObjectNotFoundExeption;
import com.example.projeto.model.input.PessoaInput;
import com.example.projeto.model.output.PessoaOutput;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.projeto.model.Repository.PessoaRepository;
import com.example.projeto.model.entity.Pessoa;

@Service
public class PessoaService {
    
    @Autowired private PessoaRepository pessoaRepository;
    @Autowired private ModelMapper modelMapper;


    public Page<PessoaOutput> findAll(Pageable pageable, PessoaFilter filter) {
        return pessoaRepository.findAll(filter.getSpecification(), pageable).map(
                content -> modelMapper.map(content, PessoaOutput.class));
    }

    public List<Pessoa> findAll() {
        List<Pessoa> pessoa = pessoaRepository.findAll();
        if(pessoa == null) {
            throw new ObjectNotFoundExeption("Requisição não encontrada.");
        }
        return pessoa;
    }

    public Pessoa post(PessoaInput pessoaInput ) {
        Pessoa pessoaTosave = modelMapper.map(pessoaInput,Pessoa.class);
        return pessoaRepository.save(pessoaTosave);
    }

    public Pessoa put(PessoaInput pessoaInput ) {
        Pessoa pessoaToUpdate = modelMapper.map(pessoaInput,Pessoa.class);
        return pessoaRepository.save(pessoaToUpdate);
    }

    public void deleteById(Integer id) {
        pessoaRepository.deleteById(id);
    }
}
