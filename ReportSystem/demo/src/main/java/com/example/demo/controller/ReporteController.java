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

import com.example.demo.entidade.Reporte;
import com.example.demo.repositorio.ReporteRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/reporte")
public class ReporteController {

    @PostMapping("/create")
    public ResponseEntity<String> createReporte(@RequestBody Reporte reporte) throws SQLException {
        ReporteRepository.current.create(reporte);
        return ResponseEntity.status(HttpStatus.CREATED).body("Novo reporte registrado com êxito!");
    }

    @GetMapping("/readAll")
    public ResponseEntity<List<Reporte>> getAllReportes() throws SQLException {
        List<Reporte> reportes = ReporteRepository.current.readAll();
        return ResponseEntity.ok(reportes);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Reporte> getReporte(@PathVariable Integer id) throws SQLException {
        Reporte reporte = ReporteRepository.current.read(id);
        return reporte != null ? ResponseEntity.ok(reporte) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateReporte(@PathVariable Integer id, @RequestBody Reporte reporte) throws SQLException {
        reporte.setIdReporte(id);
        ReporteRepository.current.update(reporte);
        return ResponseEntity.ok("Reporte atualizado com sucesso!");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteReporte(@PathVariable Integer id) throws SQLException {
        ReporteRepository.current.delete(id);
        return ResponseEntity.ok("Reporte removido com êxito!");
    }
}
