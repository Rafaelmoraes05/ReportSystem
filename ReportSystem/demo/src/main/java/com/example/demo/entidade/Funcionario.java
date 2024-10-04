package com.example.demo.entidade;

public class Funcionario {
    private int idFuncionario;
    private String nomeFuncionario;
    private String matriculaFuncionario;
    private String emailFuncionario;
    private String telefoneFuncionario;
    private Setor setorFuncionario;

    public int getIdFuncionario() {
        return this.idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNomeFuncionario() {
        return this.nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public String getMatriculaFuncionario() {
        return this.matriculaFuncionario;
    }

    public void setMatriculaFuncionario(String matriculaFuncionario) {
        this.matriculaFuncionario = matriculaFuncionario;
    }

    public String getEmailFuncionario() {
        return this.emailFuncionario;
    }

    public void setEmailFuncionario(String emailFuncionario) {
        this.emailFuncionario = emailFuncionario;
    }

    public String getTelefoneFuncionario() {
        return this.telefoneFuncionario;
    }

    public void setTelefoneFuncionario(String telefoneFuncionario) {
        this.telefoneFuncionario = telefoneFuncionario;
    }

    public Setor getSetorFuncionario() {
        return this.setorFuncionario;
    }

    public void setSetorFuncionario(Setor setorFuncionario) {
        this.setorFuncionario = setorFuncionario;
    }
}
