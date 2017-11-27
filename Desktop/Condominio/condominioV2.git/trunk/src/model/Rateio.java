/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.RateioDao;
import java.util.List;

/**
 *
 * @author Lucas_Reinert
 */
public class Rateio {

    private int idCond;
    private int idBloco;
    private int idApart;
    private String referencia;
    private float valor;

    public Rateio() {
    }

    public int getIdCond() {
        return idCond;
    }

    public void setIdCond(int idCond) {
        this.idCond = idCond;
    }

    public int getIdBloco() {
        return idBloco;
    }

    public void setIdBloco(int idBloco) {
        this.idBloco = idBloco;
    }

    public int getIdApart() {
        return idApart;
    }

    public void setIdApart(int idApart) {
        this.idApart = idApart;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public void realizarRateio(List<Apartamento> apartamentos, List<Lancamentos> lancamentos, Condominio condominio) {

        RateioDao dao = new RateioDao();
        for (Apartamento apartamento : apartamentos) {
            valor = 0;
            for (Lancamentos lancamento : lancamentos) {
                switch (lancamento.getTipoRateio()) {
                    case 0:
                        valor = valor + ((apartamento.getCoeficienteApartamento() / condominio.getCoeficiente()) * lancamento.getValor());
                        break;

                    case 1:
                        valor = valor + ((1 / apartamentos.size()) * lancamento.getValor());
                        break;
                }
                idCond = apartamento.getIdCond();
                idBloco = apartamento.getIdBloco();
                idApart = apartamento.getIdApart();
                referencia = lancamento.getReferencia();

            }
            // método salvar..
            dao.adicionar(this);
        }
    }

}
