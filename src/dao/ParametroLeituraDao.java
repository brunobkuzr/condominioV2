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
    private final String LIST = "SELECT *FROM tbParamLei";
    private final String DELETE = "DELETE FROM tbParamLei WHERE idCondominio =?";
    private final String FindKey = "SELECT * from tbParamLei WHERE idCondominio = ? ";
    private final String FindSeq = "SELECT MAX(Seq) from tbParamLei";

    public void adicionar(ParametroLeitura paramLei) {

        if (paramLei != null) {
            Connection conn;
            conn = null;
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
}
