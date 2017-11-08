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
            + "Tipo = ?,"
            + "tbcondominio_idCondominio = ?,"
            + "seq = ?,"
            + "WHERE tbcondominio_idCondominio = ?";
    private final String DELETE = "DELETE FROM tbParamLei WHERE idCondominio =?";
    private final String FindKey = "SELECT * from tbParamLei WHERE idCondominio = ? ";
    private final String FindSeq = "SELECT MAX(Seq) from tbParamLei";
    private final String FindTx = "SELECT taxIni, taxFim FROM condominio.tbparamlei "
            + "where tbcondominio_idCondominio = ? and tipo = ?";
    private final String LIST = "SELECT * FROM tbparamlei WHERE tbcondominio_idCondominio = ?";

    public void adicionar(ParametroLeitura paramLei) {

        if (paramLei != null) {
            Connection conn;
            conn = null;
            if (validarTaxas(paramLei.getCondominio(), paramLei.getTipo(), paramLei.getTxInicial(), paramLei.getTxFinal()) == true) {
                try {
                    conn = Conectar.getConexao();
                    PreparedStatement pstm;
                    pstm = conn.prepareStatement(INSERT);
                    pstm.setFloat(1, paramLei.getTxInicial());
                    pstm.setFloat(2, paramLei.getTxFinal());
                    pstm.setFloat(3, paramLei.getValor());
                    pstm.setInt(4, paramLei.getTipo());
                    pstm.setFloat(5, paramLei.getCondominio());
                    pstm.setInt(6, buscarSeq());

                    pstm.execute();
                    JOptionPane.showMessageDialog(null, "Parametro cadastrado com sucesso");
                    Conectar.fechaConexao(conn, pstm);

                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Erro ao inserir parametro no banco de"
                            + "dados " + e.getMessage());
                }
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
                seq = Integer.parseInt(str) + 1;

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir sequencial no banco de"
                    + " dados, consulte o administrador e informe o código: " + e.getMessage());
        }

        return seq;
    }

    public boolean validarTaxas(int cond, int tipo, float taxaInicial, float taxaFinal) {
        boolean valida = false;
        boolean msg = false;
        boolean msg2 = false;
        boolean msg3 = false;
        Connection conn;
        conn = null;
        ResultSet rs;
        try {
            conn = Conectar.getConexao();
            PreparedStatement pstm;
            pstm = conn.prepareStatement(FindTx);
            pstm.setInt(1, cond);
            pstm.setInt(2, tipo);
            rs = pstm.executeQuery();
            if (!rs.wasNull()) {
                while (rs.next()) {
                    float txIni = rs.getFloat("taxIni");
                    float txFim = rs.getFloat("taxFim");

                    if (taxaInicial < taxaFinal) {
                        if ((taxaInicial >= txIni) && (taxaInicial <= txFim)) {
                            if (msg2 == false) {
                                JOptionPane.showMessageDialog(null, "Valor de taxa inicial já existe em um intervalo");
                                valida = false;
                                msg2 = true;
                            }

                        } else if ((taxaFinal >= txIni) && (taxaFinal <= txFim) || ((taxaInicial < txIni) && (taxaFinal > txFim))) {
                            if (msg3 == false) {
                                JOptionPane.showMessageDialog(null, "Valor de taxa final já existe em um intervalo");
                                valida = false;
                                msg3 = true;
                            }

                        } else {
                            valida = true;
                        }

                    } else {
                        if (msg == false) {
                            JOptionPane.showMessageDialog(null, "Valor da taxa inicial não pode ser maior que o valor da taxa final.");
                            valida = false;
                            msg = true;
                        }
                    }
                }
            } else {
                valida = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir taxa no banco de"
                    + " dados, consulte o administrador e informe o código: " + e.getMessage());
        }
        return valida;
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
}
