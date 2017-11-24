/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.BlocoDao;
import dao.CondominioDao;
import java.util.List;

/**
 *
 * @author Lucas_Reinert
 */
public class Bloco {
    private int idCondominio;
    private int idBloco;
    private String nome;
    private int logradouro;
    private String endereco;
    private String bairro;
    private String numero;
    private String complemento;

    public Bloco() {
    }

    public Bloco(int idCondominio, int idBloco, String nome, int logradouro, String endereco, String bairro, String numero, String complemento) {
        this.idCondominio = idCondominio;
        this.idBloco = idBloco;
        this.nome = nome;
        this.logradouro = logradouro;
        this.endereco = endereco;
        this.bairro = bairro;
        this.numero = numero;
        this.complemento = complemento;
    }

    public int getIdCondominio() {
        return idCondominio;
    }

    public void setIdCondominio(int idCondominio) {
        this.idCondominio = idCondominio;
    }

    public int getIdBloco() {
        return idBloco;
    }

    public void setIdBloco(int idBloco) {
        this.idBloco = idBloco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
    
    
    public boolean salvar(Bloco bloco){
        BlocoDao dao = new BlocoDao();
        boolean cadastrado = false;
        if (bloco != null) {
            dao.adicionar(bloco);
            cadastrado = true;
        }
        return cadastrado;
    }
    
    public List<Bloco> listar(){
        BlocoDao dao = new BlocoDao();
        return dao.listar();
    }
        public boolean atualizar(Bloco b){
        BlocoDao dao = new BlocoDao();
        boolean atualizado = false;
        if (b != null) {
            dao.atualizar(b);
            atualizado = true;
        }
        
        return atualizado;
    }
    
    public boolean FindKey(){
        BlocoDao dao = new BlocoDao();
        return dao.FindKey(this.idCondominio, this.idBloco);
    }
    
    public void deletar(){
        BlocoDao dao = new BlocoDao();
        dao.remover(this.idCondominio,this.idBloco);
    }
    
    
}
