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

import com.example.demo.entidade.Funcionario;
import com.example.demo.repositorio.FuncionarioRepository;

@CrossOrigin(origins = "*")
@RequestMapping("/funcionario")
@RestController
public class FuncionarioController {

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody Funcionario funcionario) throws SQLException {
        FuncionarioRepository.current.create(funcionario);
        return ResponseEntity.status(HttpStatus.CREATED).body("Funcionário criado com sucesso!");
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Funcionario> read(@PathVariable Integer id) throws SQLException {
        Funcionario funcionario = FuncionarioRepository.current.read(id);
        if (funcionario != null) {
            return ResponseEntity.ok(funcionario);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/readAll")
    public ResponseEntity<List<Funcionario>> readAll() throws SQLException {
        List<Funcionario> funcionarios = FuncionarioRepository.current.readAll();
        return ResponseEntity.ok(funcionarios);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Integer id, @RequestBody Funcionario funcionario) throws SQLException {
        funcionario.setIdFuncionario(id);
        FuncionarioRepository.current.update(funcionario);
        return ResponseEntity.ok("Funcionário atualizado com sucesso!");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) throws SQLException {
        FuncionarioRepository.current.delete(id);
        return ResponseEntity.ok("Funcionário deletado com sucesso!");
    }
}
