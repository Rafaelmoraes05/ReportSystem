package com.example.demo.repositorio;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.entidade.Funcionario;
import com.example.demo.entidade.Setor;

public class FuncionarioRepository implements Repository<Funcionario, Integer> {

    public static final FuncionarioRepository current = new FuncionarioRepository();

    private FuncionarioRepository() {}

    @Override
    public void create(Funcionario funcionario) throws SQLException {
        String sql = "insert into funcionario (nomefuncionario, matriculafuncionario, emailfuncionario, telefonefuncionario, setorfuncionario) values (?, ?, ?, ?, ?)";
        
        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
        pstm.setString(1, funcionario.getNomeFuncionario());  
        pstm.setString(2, funcionario.getMatriculaFuncionario());  
        pstm.setString(3, funcionario.getEmailFuncionario());  
        pstm.setString(4, funcionario.getTelefoneFuncionario());  
        pstm.setInt(5, funcionario.getSetorFuncionario().getIdSetor());  
        pstm.execute();
    }

    @Override
    public Funcionario read(Integer id) throws SQLException {
        String sql = "select * from funcionario where idfuncionario = ?";
        
        Funcionario funcionario = null;
        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
        pstm.setInt(1, id);  
        ResultSet result = pstm.executeQuery();

        if (result.next()) {
            funcionario = new Funcionario();
            funcionario.setIdFuncionario(result.getInt("idfuncionario"));  
            funcionario.setNomeFuncionario(result.getString("nomefuncionario"));  
            funcionario.setMatriculaFuncionario(result.getString("matriculafuncionario")); 
            funcionario.setEmailFuncionario(result.getString("emailfuncionario")); 
            funcionario.setTelefoneFuncionario(result.getString("telefonefuncionario")); 

            Setor setor = new Setor();
            setor.setIdSetor(result.getInt("setorfuncionario"));  

            funcionario.setSetorFuncionario(setor);
        }

        return funcionario;
    }
    public List<Funcionario> readAll() throws SQLException {
        String sql = "SELECT f.*, s.nomesetor FROM funcionario f " +
                     "LEFT JOIN setor s ON f.setorfuncionario = s.idsetor";
        
        List<Funcionario> funcionarios 
        = new ArrayList<>();
        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
        ResultSet result = pstm.executeQuery();
    
        while (result.next()) {
            Funcionario funcionario = new Funcionario();
            funcionario.setIdFuncionario(result.getInt("idfuncionario"));  
            funcionario.setNomeFuncionario(result.getString("nomefuncionario"));  
            funcionario.setMatriculaFuncionario(result.getString("matriculafuncionario")); 
            funcionario.setEmailFuncionario(result.getString("emailfuncionario")); 
            funcionario.setTelefoneFuncionario(result.getString("telefonefuncionario")); 
    
            Setor setor = new Setor();
            // Preenchendo o setor apenas se o ID não for nulo
            if (result.getObject("setorfuncionario") != null) {
                setor.setIdSetor(result.getInt("setorfuncionario"));  
                setor.setNomeSetor(result.getString("nomesetor"));  // Obtenha o nome do setor da consulta
                funcionario.setSetorFuncionario(setor);
            } else {
                funcionario.setSetorFuncionario(null); // Não há setor associado
            }
    
            funcionarios.add(funcionario);
        }
    
        return funcionarios;
    }
    

    @Override
    public void update(Funcionario funcionario) throws SQLException {
        String sql = "update funcionario set nomefuncionario = ?, matriculafuncionario = ?, emailfuncionario = ?, telefonefuncionario = ?, setorfuncionario = ? where idfuncionario = ?";
        
        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
        pstm.setString(1, funcionario.getNomeFuncionario());  
        pstm.setString(2, funcionario.getMatriculaFuncionario());  
        pstm.setString(3, funcionario.getEmailFuncionario());  
        pstm.setString(4, funcionario.getTelefoneFuncionario());  
        pstm.setInt(5, funcionario.getSetorFuncionario().getIdSetor());  
        pstm.setInt(6, funcionario.getIdFuncionario());  
        pstm.execute();
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String sql = "delete from funcionario where idfuncionario = ?";
        
        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
        pstm.setInt(1, id);  
        pstm.execute();
    }
}
