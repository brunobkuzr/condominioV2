/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.EscritorioDao;

/**
 *
 * @author Lucas_Reinert
 */
public class Escritorio {
    private String nome;
    private String telefone;
    private String email;
    private int logradouro;
    private String endereco;
    private String bairro;
    private String numero;
    private String complemento;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(int logradouro) {
        this.logradouro = logradouro;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Escritorio(String nome, String telefone, String email, int logradouro, String endereco, String bairro, String numero, String complemento) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.logradouro = logradouro;
        this.endereco = endereco;
        this.bairro = bairro;
        this.numero = numero;
        this.complemento = complemento;
    }

    public Escritorio() {
    }

    @Override
    public String toString() {
        return "Escritorio{" + "nome=" + nome + ", telefone=" + telefone + ", email=" + email + ", logradouro=" + logradouro + ", endereco=" + endereco + ", bairro=" + bairro + ", numero=" + numero + ", complemento=" + complemento + '}';
    }
    
    public boolean salvar(){
        boolean cadastrado = false;
        EscritorioDao dao = new EscritorioDao();
        
        if (this != null) {            
            dao.adicionar(this);
            cadastrado = true;
        }   
        return cadastrado;
    }
    
    public boolean atualizar(Escritorio e){
        EscritorioDao dao = new EscritorioDao();
        boolean atualizado = false;
        if (e != null) {
            dao.atualizar(e);
            atualizado = true;
        }
        
        return atualizado;
    }


}
