/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Lucas_Reinert
 */
public class Apartamento {
    private int    idApart;
    private int    idBloco;
    private int    idCond;
    private String nomeProprietario;
    private String telefoneProprietario;
    private String emailProprietario;
    private String logradouroProprietario;
    private String enderecoProprietario;
    private String bairroProprietario;
    private String numeroProprietario;
    private String complementoProprietario;
    private String nomeMorador;
    private String telefoneMorador;
    private String emailMorador;
    private float  coeficienteApartamento;

    public Apartamento() {
    }

    public Apartamento(int idApart, int idBloco, int idCond, String nomeProprietario, String telefoneProprietario, String emailProprietario, String logradouroProprietario, String enderecoProprietario, String bairroProprietario, String numeroProprietario, String complementoProprietario, String nomeMorador, String telefoneMorador, String emailMorador, float coeficienteApartamento) {
        this.idApart = idApart;
        this.idBloco = idBloco;
        this.idCond = idCond;
        this.nomeProprietario = nomeProprietario;
        this.telefoneProprietario = telefoneProprietario;
        this.emailProprietario = emailProprietario;
        this.logradouroProprietario = logradouroProprietario;
        this.enderecoProprietario = enderecoProprietario;
        this.bairroProprietario = bairroProprietario;
        this.numeroProprietario = numeroProprietario;
        this.complementoProprietario = complementoProprietario;
        this.nomeMorador = nomeMorador;
        this.telefoneMorador = telefoneMorador;
        this.emailMorador = emailMorador;
        this.coeficienteApartamento = coeficienteApartamento;
    }

    

    public int getIdCond() {
        return idCond;
    }

    public void setIdCond(int idCond) {
        this.idCond = idCond;
    }
    
    public int getIdApart() {
        return idApart;
    }

    public void setIdApart(int idApart) {
        this.idApart = idApart;
    }

    public int getIdBloco() {
        return idBloco;
    }

    public void setIdBloco(int idBloco) {
        this.idBloco = idBloco;
    }

    public String getNomeProprietario() {
        return nomeProprietario;
    }

    public void setNomeProprietario(String nomeProprietario) {
        this.nomeProprietario = nomeProprietario;
    }

    public String getTelefoneProprietario() {
        return telefoneProprietario;
    }

    public void setTelefoneProprietario(String telefoneProprietario) {
        this.telefoneProprietario = telefoneProprietario;
    }

    public String getEmailProprietario() {
        return emailProprietario;
    }

    public void setEmailProprietario(String emailProprietario) {
        this.emailProprietario = emailProprietario;
    }

    public String getLogradouroProprietario() {
        return logradouroProprietario;
    }

    public void setLogradouroProprietario(String logradouroProprietario) {
        this.logradouroProprietario = logradouroProprietario;
    } 

    public String getNumeroProprietario() {
        return numeroProprietario;
    }

    public void setNumeroProprietario(String numerProprietario) {
        this.numeroProprietario = numerProprietario;
    }   
    

    public String getEnderecoProprietario() {
        return enderecoProprietario;
    }

    public void setEnderecoProprietario(String enderecoProprietario) {
        this.enderecoProprietario = enderecoProprietario;
    }

    public String getBairroProprietario() {
        return bairroProprietario;
    }

    public void setBairroProprietario(String bairroProprietario) {
        this.bairroProprietario = bairroProprietario;
    }

    public String getComplementoProprietario() {
        return complementoProprietario;
    }

    public void setComplementoProprietario(String complementoProprietario) {
        this.complementoProprietario = complementoProprietario;
    }

    public String getNomeMorador() {
        return nomeMorador;
    }

    public void setNomeMorador(String nomeMorador) {
        this.nomeMorador = nomeMorador;
    }

    public String getTelefoneMorador() {
        return telefoneMorador;
    }

    public void setTelefoneMorador(String telefoneMorador) {
        this.telefoneMorador = telefoneMorador;
    }

    public String getEmailMorador() {
        return emailMorador;
    }

    public void setEmailMorador(String emailMorador) {
        this.emailMorador = emailMorador;
    }

    public float getCoeficienteApartamento() {
        return coeficienteApartamento;
    }

    public void setCoeficienteApartamento(float coeficienteApartamento) {
        this.coeficienteApartamento = coeficienteApartamento;
    }
    
    
}
