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

    public Bloco() {
    }

    public Bloco(int idCondominio, int idBloco, String nome) {
        this.idCondominio = idCondominio;
        this.idBloco = idBloco;
        this.nome = nome;
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
    
    public void salvar(){
        BlocoDao dao = new BlocoDao();
        if (this != null) {
            dao.adicionar(this);
        }
    }    
    
    
}
