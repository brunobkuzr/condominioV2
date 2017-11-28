/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.ParametroLeitura;
import java.util.List;
import model.Apartamento;

/**
 *
 * @author Bruno Fernandes
 */
public class ParametroLeituraDao {

    private final String INSERT = "INSERT INTO tbParamLei (taxIni, taxFim,"
            + "Valor, Tipo, tbcondominio_idCondominio, seq)"
            + "VALUES (?,?,?,?,?,?)";
    private final String UPDATE = "UPDATE tbParamLei SET "
            + "taxIni = ?,"
            + "taxFim = ?,"
            + "Valor = ?,"
            + "C,"
            + "tbcondominio_idCondominio = ?,"
            + "seq = ?,"
            + "WHERE tbcondominio_idCondominio = ?";
    private final String DELETE = "DELETE FROM tbParamLei WHERE idCondominio =?";
    private final String FindKey = "SELECT * from tbParamLei WHERE idCondominio = ? ";
    private final String FindSeq = "SELECT MAX(Seq) from tbParamLei";
    private final String FindTx = "SELECT MAX(taxFim) FROM condominio.tbparamlei where tbcondominio_idCondominio = ? and tipo = ?";
    private final String LIST = "SELECT * FROM tbparamlei WHERE tbcondominio_idCondominio = ?";
    private final String DelSeq = "DELETE FROM tbparamlei WHERE Seq = ?";
    private final String LISTRATEIO = "Select * FROM tbparamlei WHERE (tbapartamento_tbBloco_tbCondominio_idCondominio = ?) and (tbapartamento_tbBloco_idBloco = ?) and (tbapartamento_idApart = ?) and (Tipo = ?)";
    

    public void adicionar(ParametroLeitura paramLei) {

        if (paramLei != null) {
            Connection conn;
            conn = null;

            try {

                conn = Conectar.getConexao();
                PreparedStatement pstm;
                pstm = conn.prepareStatement(INSERT);

                pstm.setFloat(1, buscarTaxaInicial(paramLei.getCondominio(), paramLei.getTipo(), paramLei.getTxFinal()));
                pstm.setFloat(2, paramLei.getTxFinal());
                pstm.setFloat(3, paramLei.getValor());
                pstm.setInt(4, paramLei.getTipo());
                pstm.setFloat(5, paramLei.getCondominio());
                pstm.setInt(6, buscarSeq());
                if (validarTaxa(buscarTaxaInicial(paramLei.getCondominio(), paramLei.getTipo(), paramLei.getTxFinal()), paramLei.getTxFinal()) == true) {
                    pstm.execute();
                    JOptionPane.showMessageDialog(null, "Parametro cadastrado com sucesso");
                    Conectar.fechaConexao(conn, pstm);
                } else {
                    JOptionPane.showMessageDialog(null, "Taxa inicial não pode ser maior que a taxa final.");
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao inserir parametro no banco de"
                        + "dados " + e.getMessage());
            }

        } else {
            System.out.println("O condomínio enviado por parâmetro está vazio");
        }
    }

    /**
     * Metodo utiliazado para buscar o proximo sequencial
     *
     * @return
     */
    public int buscarSeq() {
        Connection conn;
        conn = null;
        int seq = 0;
        ResultSet rs;
        try {
            conn = Conectar.getConexao();
            PreparedStatement pstm;
            pstm = conn.prepareStatement(FindSeq);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String str = rs.getString("MAX(Seq)");
                if(str == "9"){
                    seq = 10;
                }
                if (str == null) {
                    seq = 1;
                } else {
                    seq = Integer.parseInt(str) + 1;
                }

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir taxa inicial no banco de"
                    + " dados, consulte o administrador e informe o código: " + e.getMessage());
        }

        return seq;
    }

    public float buscarTaxaInicial(int cond, int tipo, float taxFinal) {
        Connection conn;
        conn = null;
        float taxIni = 0;
        ResultSet rs;
        try {
            conn = Conectar.getConexao();
            PreparedStatement pstm;
            pstm = conn.prepareStatement(FindTx);
            pstm.setInt(1, cond);
            pstm.setInt(2, tipo);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String str = rs.getString("MAX(taxFim)");
                System.out.println(str);
                if (str == null) {
                    taxIni = (float) 0.00001;
                } else {
                    taxIni = (float) (Float.parseFloat(str) + +0.00001);
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir taxa inicial no banco de"
                    + " dados, consulte o administrador e informe o código: " + e.getMessage());
        }

        return taxIni;
    }

    public boolean validarTaxa(float txIni, float txFim) {
        if (txIni < txFim) {
            return true;
        } else {
            return false;
        }
    }

    public List<ParametroLeitura> listar(int cond) {
        Connection conn;
        conn = null;
        PreparedStatement pstm;
        pstm = null;
        ResultSet rs;
        rs = null;
        ArrayList<ParametroLeitura> parametros = new ArrayList<ParametroLeitura>();
        try {
            conn = Conectar.getConexao();
            pstm = conn.prepareStatement(LIST);
            pstm.setInt(1, cond);
            rs = pstm.executeQuery();
            while (rs.next()) {
                ParametroLeitura p = new ParametroLeitura();
                p.setSeq(rs.getInt("Seq"));
                p.setTipo(rs.getInt("Tipo"));
                p.setTxInicial(rs.getFloat("TaxIni"));
                p.setTxFinal(rs.getFloat("TaxFIm"));
                p.setValor(rs.getFloat("Valor"));
                parametros.add(p);
            }
            Conectar.fechaConexao(conn, pstm, rs);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar parametros" + e.getMessage());
        }
        return parametros;
    }

    public void excluirSeq(int seq) {
        Connection conn;
        conn = null;

        ResultSet rs;
        try {
            
            conn = Conectar.getConexao();
            PreparedStatement pstm;
            pstm = conn.prepareStatement(DelSeq);
            pstm.setInt(1, seq);
            pstm.execute();
            System.out.println("Executou");
        } catch (Exception e) {

        }
    }
    
    public float valorRateio(int tipoLeitura, float consumo, Apartamento ap){
        Connection conn;
        conn = null;

        ResultSet rs;
        try {            
            conn = Conectar.getConexao();
            PreparedStatement pstm;
            float maior = 0;
            float valor;
            pstm = conn.prepareStatement(LISTRATEIO);
            pstm.setInt(1, ap.getIdCond());
            pstm.setInt(2, ap.getIdBloco());
            pstm.setInt(3, ap.getIdApart());   
            pstm.setInt(4, tipoLeitura);
            rs = pstm.executeQuery();
            while (rs.next()) {
                if ((consumo > rs.getFloat("taxIni")) && (consumo <= rs.getFloat("taxFim")) ) {
                    valor = (consumo * rs.getFloat("valor"));
                    return valor;
                }
            }
        } catch (Exception e) {

        }
        return 0;
    }
}
