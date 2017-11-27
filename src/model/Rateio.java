/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.AguaDao;
import dao.EsgotoDao;
import dao.GasDao;
import dao.ParametroLeituraDao;
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
            if (lancamentos.size() > 0) {
                valor = 0;
                //rateio de despesas
                for (Lancamentos lancamento : lancamentos) {
                    switch (lancamento.getTipoRateio()) {
                        case 0:
                            valor = valor + ((apartamento.getCoeficienteApartamento() / condominio.getCoeficiente()) * lancamento.getValor());
                            break;

                        case 1:
                            valor = valor + ((1 / apartamentos.size()) * lancamento.getValor());
                            break;
                    }

                }
                idCond = apartamento.getIdCond();
                idBloco = apartamento.getIdBloco();
                idApart = apartamento.getIdApart();
                referencia = lancamentos.get(0).getReferencia();// todos os lançamentos vão vir com a mesma referência, então eu pego a referência do primeiro.
                int mes = Integer.parseInt(referencia.substring(1, 2));
                int ano = Integer.parseInt(referencia.substring(4, 4));
                // rateio de leitura

                AguaDao aguDao = new AguaDao();
                Agua agu = new Agua();
                agu.setIdCond(idCond);
                agu.setIdBloco(idBloco);
                agu.setIdApart(idApart);
                agu.setAno(ano);
                
                EsgotoDao esgDao = new EsgotoDao();
                Esgoto esg = new Esgoto();
                esg.setIdCond(idCond);
                esg.setIdBloco(idBloco);
                esg.setIdApart(idApart);
                esg.setAno(ano);
                
                GasDao gasDao = new GasDao();
                Gas gas = new Gas();
                gas.setIdCond(idCond);
                gas.setIdBloco(idBloco);
                gas.setIdApart(idApart);
                gas.setAno(ano);
                
                
                ParametroLeituraDao pl = new ParametroLeituraDao();

                switch (mes) {
                    case 1:
                        valor = valor + pl.valorRateio(1, aguDao.listar(agu).getLei01(), apartamento);
                        valor = valor + pl.valorRateio(2, esgDao.listar(esg).getLei01(), apartamento);
                        valor = valor + pl.valorRateio(3, gasDao.listar(gas).getLei01(), apartamento);
                        break;
                    case 2:
                        valor = valor + pl.valorRateio(1, aguDao.listar(agu).getLei02(), apartamento);
                        valor = valor + pl.valorRateio(2, esgDao.listar(esg).getLei02(), apartamento);
                        valor = valor + pl.valorRateio(3, gasDao.listar(gas).getLei02(), apartamento);
                        break;
                    case 3:
                        valor = valor + pl.valorRateio(1, aguDao.listar(agu).getLei03(), apartamento);
                        valor = valor + pl.valorRateio(2, esgDao.listar(esg).getLei03(), apartamento);
                        valor = valor + pl.valorRateio(3, gasDao.listar(gas).getLei03(), apartamento);
                        break;
                    case 4:
                        valor = valor + pl.valorRateio(1, aguDao.listar(agu).getLei04(), apartamento);
                        valor = valor + pl.valorRateio(2, esgDao.listar(esg).getLei04(), apartamento);
                        valor = valor + pl.valorRateio(3, gasDao.listar(gas).getLei04(), apartamento);
                        break;
                    case 5:
                        valor = valor + pl.valorRateio(1, aguDao.listar(agu).getLei05(), apartamento);
                        valor = valor + pl.valorRateio(2, esgDao.listar(esg).getLei05(), apartamento);
                        valor = valor + pl.valorRateio(3, gasDao.listar(gas).getLei05(), apartamento);
                        break;
                    case 6:
                        valor = valor + pl.valorRateio(1, aguDao.listar(agu).getLei06(), apartamento);
                        valor = valor + pl.valorRateio(2, esgDao.listar(esg).getLei06(), apartamento);
                        valor = valor + pl.valorRateio(3, gasDao.listar(gas).getLei06(), apartamento);
                        break;
                    case 7:
                        valor = valor + pl.valorRateio(1, aguDao.listar(agu).getLei07(), apartamento);
                        valor = valor + pl.valorRateio(2, esgDao.listar(esg).getLei07(), apartamento);
                        valor = valor + pl.valorRateio(3, gasDao.listar(gas).getLei07(), apartamento);
                        break;
                    case 8:
                        valor = valor + pl.valorRateio(1, aguDao.listar(agu).getLei08(), apartamento);
                        valor = valor + pl.valorRateio(2, esgDao.listar(esg).getLei08(), apartamento);
                        valor = valor + pl.valorRateio(3, gasDao.listar(gas).getLei08(), apartamento);
                        break;
                    case 9:
                        valor = valor + pl.valorRateio(1, aguDao.listar(agu).getLei09(), apartamento);
                        valor = valor + pl.valorRateio(2, esgDao.listar(esg).getLei09(), apartamento);
                        valor = valor + pl.valorRateio(3, gasDao.listar(gas).getLei09(), apartamento);
                        break;
                    case 10:
                        valor = valor + pl.valorRateio(1, aguDao.listar(agu).getLei10(), apartamento);
                        valor = valor + pl.valorRateio(2, esgDao.listar(esg).getLei10(), apartamento);
                        valor = valor + pl.valorRateio(3, gasDao.listar(gas).getLei10(), apartamento);
                        break;
                    case 11:
                        valor = valor + pl.valorRateio(1, aguDao.listar(agu).getLei11(), apartamento);
                        valor = valor + pl.valorRateio(2, esgDao.listar(esg).getLei11(), apartamento);
                        valor = valor + pl.valorRateio(3, gasDao.listar(gas).getLei11(), apartamento);
                        break;
                    case 12:
                        valor = valor + pl.valorRateio(1, aguDao.listar(agu).getLei12(), apartamento);
                        valor = valor + pl.valorRateio(2, esgDao.listar(esg).getLei12(), apartamento);
                        valor = valor + pl.valorRateio(3, gasDao.listar(gas).getLei12(), apartamento);
                        break;
                }

                // salvar..
                dao.adicionar(this);
            }
        }
    }

}
