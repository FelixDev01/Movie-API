package com.movie.api.service;

import com.movie.api.dto.FilmeRequestDTO;
import com.movie.api.dto.FilmeResponseDTO;
import com.movie.api.model.Filme;
import com.movie.api.repository.FilmeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FilmeService {

    private final FilmeRepository repository;

    public FilmeService(FilmeRepository repository) {
        this.repository = repository;
    }

    public FilmeResponseDTO adicionarFilme (FilmeRequestDTO dto){
        Filme filme = new Filme();
        copyToDTO(dto, filme);
        filme = repository.save(filme);

        return new FilmeResponseDTO(filme);
    }

    public Page<FilmeResponseDTO> listarFilmes (Pageable pageable){
        Page<Filme> filmes = repository.findAll(pageable);

        return filmes.map(x -> new FilmeResponseDTO(x));
    }

    public FilmeResponseDTO encontrarFilme (Long id){
        Filme filme = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Id não encontrado"));

        return new FilmeResponseDTO(filme);
    }

    public FilmeResponseDTO editarFilme (FilmeRequestDTO dto, Long id){
        try {
            Filme filme = repository.getReferenceById(id);
            copyToDTO(dto, filme);
            filme = repository.save(filme);
            return new FilmeResponseDTO(filme);
        }
        catch (EntityNotFoundException e) {
            throw new RuntimeException("Id não encontrado");
        }
    }

    public void excluirFilme (Long id){
        if (!repository.existsById(id)){
            throw new RuntimeException("Id não encontrado");
        }
            repository.deleteById(id);
    }



    private void copyToDTO(FilmeRequestDTO dto, Filme filme) {
        filme.setTitulo(dto.getTitulo());
        filme.setDiretor(dto.getDiretor());
        filme.setAnoLancamento(dto.getAnoLancamento());
        filme.setGenero(dto.getGenero());
        filme.setSinopse(dto.getSinopse());
    }
}
