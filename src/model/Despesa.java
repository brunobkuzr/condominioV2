/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.DespesaDao;
import java.util.List;

/**
 *
 * @author Lucas_Reinert
 */
public class Despesa {

    private int id;
    private String nome;

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

    public boolean salvar() {
        DespesaDao dao = new DespesaDao();
        boolean cadastrado = false;
        if (this != null) {
            dao.adicionar(this);
            cadastrado = true;
        }
        return cadastrado;
    }

    public List<Despesa> listar() {
        DespesaDao dao = new DespesaDao();
        return dao.listar();
    }

    public boolean atualizar() {
        DespesaDao dao = new DespesaDao();
        boolean atualizado = false;
        if (this != null) {
            dao.atualizar(this);
            atualizado = true;
        }

        return atualizado;
    }

    public boolean FindKey() {
        DespesaDao dao = new DespesaDao();
        return dao.FindKey(this.id);
    }

    public void deletar() {
        DespesaDao dao = new DespesaDao();
        dao.remover(this.id);
    }
}
