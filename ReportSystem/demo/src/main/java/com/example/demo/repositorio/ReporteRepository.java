package com.example.demo.repositorio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.entidade.Reporte;
import com.example.demo.entidade.Setor;

public class ReporteRepository implements Repository<Reporte, Integer> {

    public static final ReporteRepository current = new ReporteRepository();

    private ReporteRepository() {
    }

    @Override
    public void create(Reporte reporte) throws SQLException {
        String sql = "insert into reporte (tiporeporte, datareporte, idsetorreporte) values (?, ?, ?)";

        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
        
        pstm.setString(1, reporte.getTipoReporte());  
        pstm.setDate(2, new java.sql.Date(reporte.getDataReporte().getTime()));  
        pstm.setInt(3, reporte.getSetor().getIdSetor());  

        pstm.execute(); 
    }

    public List<Reporte> readAll() throws SQLException {
        String sql = "select r.*, s.nomesetor as nomesetor from reporte r "
                + "left join setor s on r.idsetorreporte = s.idsetor"; 

        List<Reporte> reportes = new ArrayList<>();  

        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
        
        ResultSet result = pstm.executeQuery();

        while (result.next()) {
            Reporte r = new Reporte();
            r.setIdReporte(result.getInt("idreporte"));  
            r.setTipoReporte(result.getString("tiporeporte")); 
            r.setDataReporte(result.getDate("datareporte"));  

            // Mapear os dados de setor
            Setor s = new Setor();
            s.setIdSetor(result.getInt("idsetorreporte"));  
            s.setNomeSetor(result.getString("nomesetor"));  

            r.setSetor(s);  

            reportes.add(r);  
        }

        return reportes;  
    }

    @Override
    public void update(Reporte reporte) throws SQLException {
        String sql = "update reporte set tiporeporte = ?, datareporte = ?, idsetorreporte = ? where idreporte = ?";

        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
        
        pstm.setString(1, reporte.getTipoReporte()); 
        pstm.setDate(2, new java.sql.Date(reporte.getDataReporte().getTime()));  
        pstm.setInt(3, reporte.getSetor().getIdSetor());  
        pstm.setInt(4, reporte.getIdReporte());  

        pstm.execute();
    }

    @Override
    public Reporte read(Integer id) throws SQLException {
        String sql = "select r.*, s.setor_nome as setor_nome from reporte r "
                + "left join setor s on r.idsetorreporte = s.idsetor where r.idreporte = ?";

        Reporte reporte = null;

        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
        pstm.setInt(1, id);

        ResultSet result = pstm.executeQuery();

        if (result.next()) {
            reporte = new Reporte();
            reporte.setIdReporte(result.getInt("idreporte"));  
            reporte.setTipoReporte(result.getString("tiporeporte"));  
            reporte.setDataReporte(result.getDate("datareporte"));  

            Setor s = new Setor();
            s.setIdSetor(result.getInt("idsetorreporte")); 
            s.setNomeSetor(result.getString("setor_nome")); 

            reporte.setSetor(s); 
        }

        return reporte;
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String sql = "delete from reporte where idreporte = ?";

        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
        pstm.setInt(1, id);  

        pstm.execute();
    }
}
