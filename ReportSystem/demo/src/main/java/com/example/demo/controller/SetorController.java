package com.example.demo.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entidade.Setor;
import com.example.demo.repositorio.SetorRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/setor")
public class SetorController {

    @PostMapping("/create")
    public ResponseEntity<String> createSetor(@RequestBody Setor setor) throws SQLException {
        SetorRepository.current.create(setor);
        return ResponseEntity.status(HttpStatus.CREATED).body("Novo setor adicionado com sucesso!");
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Setor> getSetor(@PathVariable Integer id) throws SQLException {
        Setor setor = SetorRepository.current.read(id);
        
        if (setor != null) {
            return ResponseEntity.ok(setor);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/readAll")
    public ResponseEntity<List<Setor>> getAllSetores() throws SQLException {
        List<Setor> setores = SetorRepository.current.readAll();
        return ResponseEntity.ok(setores);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateSetor(@PathVariable Integer id, @RequestBody Setor setor) throws SQLException {
        setor.setIdSetor(id);
        SetorRepository.current.update(setor);
        return ResponseEntity.ok("Dados do setor atualizados com sucesso!");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteSetor(@PathVariable Integer id) throws SQLException {
        SetorRepository.current.delete(id);
        return ResponseEntity.ok("Setor removido com sucesso!");
    }
}
