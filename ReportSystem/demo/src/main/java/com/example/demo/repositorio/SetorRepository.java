package com.example.demo.repositorio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.entidade.Setor;

public class SetorRepository implements Repository<Setor, Integer> {

    public static final SetorRepository current = new SetorRepository();

    private SetorRepository() {}

    @Override
    public void create(Setor setor) throws SQLException {
        String sql = "insert into setor (nomesetor) values (?)";
        
        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
        pstm.setString(1, setor.getNomeSetor());  
        pstm.execute();
    }

    @Override
    public Setor read(Integer id) throws SQLException {
        String sql = "select * from setor where idsetor = ?";
        Setor setor = null;
        
        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
        pstm.setInt(1, id);
        ResultSet result = pstm.executeQuery();

        if (result.next()) {
            setor = new Setor();
            setor.setIdSetor(result.getInt("idsetor"));
            setor.setNomeSetor(result.getString("nomesetor"));  
        }
        return setor;
    }

    public List<Setor> readAll() throws SQLException {
        String sql = "select * from setor";
        List<Setor> setores = new ArrayList<>();
        
        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
        ResultSet result = pstm.executeQuery();

        while (result.next()) {
            Setor setor = new Setor();
            setor.setIdSetor(result.getInt("idsetor"));  
            setor.setNomeSetor(result.getString("nomesetor")); 
            setores.add(setor);
        }

        return setores;
    }

    @Override
    public void update(Setor setor) throws SQLException {
        String sql = "update setor set nomesetor = ? where idsetor = ?";
        
        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
        pstm.setString(1, setor.getNomeSetor());  
        pstm.setInt(2, setor.getIdSetor());  
        pstm.execute();
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String sql = "delete from setor where idsetor = ?";
        
        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
        pstm.setInt(1, id);  
        pstm.execute();
    }
}
