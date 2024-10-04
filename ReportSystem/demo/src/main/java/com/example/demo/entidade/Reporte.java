package com.example.demo.entidade;

import java.util.Date;

public class Reporte {

    private int idReporte;
    private String tipoReporte;
    private Date dataReporte;
    private Setor setor;

    public int getIdReporte() {
        return this.idReporte;
    }

    public void setIdReporte(int idReporte) {
        this.idReporte = idReporte;
    }

    public String getTipoReporte() {
        return this.tipoReporte;
    }

    public void setTipoReporte(String tipoReporte) {
        this.tipoReporte = tipoReporte;
    }

    public Date getDataReporte() {
        return this.dataReporte;
    }

    public void setDataReporte(Date dataReporte) {
        this.dataReporte = dataReporte;
    }

    public Setor getSetor() {
        return this.setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }
}
