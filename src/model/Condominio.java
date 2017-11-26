/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.CondominioDao;
import java.util.List;

/**
 *
 * @author Lucas_Reinert
 */
public class Condominio {
    private int id;
    private String nome;
    private String telefone;
    private String email;
    private float coeficiente;
    private int logradouro;
    private String endereco;
    private String bairro;
    private String numero;
    private String complemento;

    public Condominio() {
    }

    public Condominio(int id, String nome, String telefone, String email, float coeficiente, int logradouro, String endereco, String bairro, String numero, String complemento) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.coeficiente = coeficiente;
        this.logradouro = logradouro;
        this.endereco = endereco;
        this.bairro = bairro;
        this.numero = numero;
        this.complemento = complemento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public float getCoeficiente() {
        return coeficiente;
    }

    public void setCoeficiente(float coeficiente) {
        this.coeficiente = coeficiente;
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
    
    public void salvar(){
        CondominioDao dao = new CondominioDao();
        dao.adicionar(this);
    }
    
    public List<Condominio> listar(){
        CondominioDao dao = new CondominioDao();
        return dao.listar();
    }
    
    public void atualizar(){
        CondominioDao dao = new CondominioDao();
            dao.atualizar(this);
        
    }
    
    public boolean FindKey(int cod){
        CondominioDao dao = new CondominioDao();
        return dao.FindKey(cod);
    }
    
    public void deletar(int cod){
        CondominioDao dao = new CondominioDao();
        dao.remover(cod);
    }
    

    @Override
    public String toString() {
        return "Condominio{" + "id=" + id + ", nome=" + nome + ", telefone=" + telefone + ", email=" + email + ", coeficiente=" + coeficiente + ", logradouro=" + logradouro + ", endereco=" + endereco + ", bairro=" + bairro + ", numero=" + numero + ", complemento=" + complemento + '}';
    }
    
    
    
}
