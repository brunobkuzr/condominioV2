/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.CondominioDao;
import dao.ParametroLeituraDao;
import java.util.List;

/**
 *
 * @author Bruno Fernandes
 */
public class ParametroLeitura {

    private int condominio;
    private float txInicial;
    private float txFinal;
    private float valor;
    private int tipo;
    private int seq;

    public ParametroLeitura() {
    }

    public ParametroLeitura(int condominio, float txInicial, float txFinal, float valor, int tipo, int seq) {
        this.condominio = condominio;
        this.txInicial = txInicial;
        this.txFinal = txFinal;
        this.valor = valor;
        this.tipo = tipo;
        this.seq = seq;
    }

    public int getCondominio() {
        return condominio;
    }

    public void setCondominio(int condominio) {
        this.condominio = condominio;
    }

    public float getTxInicial() {
        return txInicial;
    }

    public void setTxInicial(float txInicial) {
        this.txInicial = txInicial;
    }

    public float getTxFinal() {
        return txFinal;
    }

    public void setTxFinal(float txFinal) {
        this.txFinal = txFinal;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public boolean salvar(ParametroLeitura param) {
        ParametroLeituraDao dao = new ParametroLeituraDao();
        boolean cadastrado = false;
        if (param != null) {
            dao.adicionar(param);
            cadastrado = true;
        }
        return cadastrado;
    }

    /**
     * Método utilizado para realizar a busca do condominio o qual vai ser
     * cadastrado o parametro
     * @param idCond é o código do condominio que vai ser buscado.
     * @return nome do condominio
     */  
    public String buscarNome(int idCond) {
        CondominioDao dao = new CondominioDao();
        return dao.buscarCondominio(idCond);

    }
    public List<ParametroLeitura> listar(int cond){
        ParametroLeituraDao dao = new ParametroLeituraDao();
        return dao.listar(cond);
    }
}
