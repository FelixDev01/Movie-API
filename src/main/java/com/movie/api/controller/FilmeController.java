package com.movie.api.controller;

import com.movie.api.dto.FilmeRequestDTO;
import com.movie.api.dto.FilmeResponseDTO;
import com.movie.api.service.FilmeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("filmes")
public class FilmeController {

    private final FilmeService service;

    public FilmeController(FilmeService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity <FilmeResponseDTO> filme (@RequestBody FilmeRequestDTO dto){
        FilmeResponseDTO filme = service.adicionarFilme(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(filme.getId()).toUri();
        return ResponseEntity.created(uri).body(filme);
    }

    @GetMapping
    public ResponseEntity<Page<FilmeResponseDTO>> listaFilmes(Pageable pageable){
        Page<FilmeResponseDTO> filmes = service.listarFilmes(pageable);
        return ResponseEntity.ok(filmes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmeResponseDTO> encontrarFilme(@PathVariable Long id){
        FilmeResponseDTO filme = service.encontrarFilme(id);
        return ResponseEntity.ok(filme);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FilmeResponseDTO> atualizarFilme(@RequestBody FilmeRequestDTO dto, @PathVariable Long id){
        FilmeResponseDTO filme = service.editarFilme(dto, id);
        return ResponseEntity.ok(filme);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFilme(@PathVariable Long id){
        service.excluirFilme(id);
        return ResponseEntity.noContent().build();
    }
}
