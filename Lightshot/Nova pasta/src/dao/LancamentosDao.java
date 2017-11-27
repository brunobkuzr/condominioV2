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
import java.util.List;
import javax.swing.JOptionPane;
import model.Apartamento;
import model.Lancamentos;

/**
 *
 * @author Lucas_Reinert
 */
public class LancamentosDao {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private final String INSERT = "INSERT INTO tbLancDesp(tbcondominio_idCondominio, tbDespesas_idDespesas, Referencia,"
            + "Valor, TipoRateio)"
            + "VALUES (?,?,?,?,?)";
    private final String UPDATE = "UPDATE tbLancDesp SET "
            + "Valor = ?,"
            + "TipoRateio = ? "
            + "WHERE (tbcondominio_idCondominio = ?) and (tbDespesas_idDespesas = ?) and (Referencia = ?)";
    private final String LIST = "SELECT * FROM tbLancDesp where (tbcondominio_idCondominio = ?) and (Referencia = ?)";
    private final String DELETE = "DELETE FROM tbLancDesp WHERE (tbcondominio_idCondominio = ?) and (tbDespesas_idDespesas = ?) and (Referencia = ?)";
    private final String FindKey = "SELECT * from tbLancDesp WHERE (tbcondominio_idCondominio = ?) and (tbDespesas_idDespesas = ?) and (Referencia = ?)";

    public void adicionar(Lancamentos lanc) {

        if (lanc != null) {
            Connection conn;
            conn = null;
            try {
                conn = Conectar.getConexao();
                PreparedStatement pstm;
                pstm = conn.prepareStatement(INSERT);
                pstm.setInt(1, lanc.getIdCond());
                pstm.setInt(2, lanc.getIdDesp());
                pstm.setString(3, lanc.getReferencia());
                pstm.setFloat(4, lanc.getValor());
                pstm.setInt(5, lanc.getTipoRateio());
                pstm.execute();

                JOptionPane.showMessageDialog(null, "Lançamento cadastrado com sucesso");
                Conectar.fechaConexao(conn, pstm);

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao inserir lançamento no banco de"
                        + "dados " + e.getMessage());
            }
        } else {
            System.out.println("O lançamento enviado por parâmetro está vazio");
        }
    }

    public void atualizar(Lancamentos lanc) {
        if (lanc != null) {
            Connection conn;
            conn = null;
            try {
                conn = Conectar.getConexao();
                PreparedStatement pstm;
                pstm = conn.prepareStatement(UPDATE);                
                pstm.setFloat(1, lanc.getValor());
                pstm.setInt(2, lanc.getTipoRateio());
                pstm.setInt(3, lanc.getIdCond());
                pstm.setInt(4, lanc.getIdDesp());
                pstm.setString(5, lanc.getReferencia());
                pstm.execute();
                JOptionPane.showMessageDialog(null, "Lançamento alterado com sucesso");
                Conectar.fechaConexao(conn);

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao atualizar o " + lanc.getIdDesp() + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "O lançamento enviado por parâmetro está vazio");
        }
    }

    public void remover(int codCond, int codDesp, String Referencia) {

        Connection conn;
        conn = null;
        try {
            conn = Conectar.getConexao();
            PreparedStatement pstm;
            pstm = conn.prepareStatement(DELETE);

            pstm.setInt(1, codCond);
            pstm.setInt(2, codDesp);
            pstm.setString(3, Referencia);

            pstm.execute();
            Conectar.fechaConexao(conn, pstm);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir lançamento do banco de dados " + e.getMessage());
        }
    }

    public List<Lancamentos> listar(Lancamentos lancamento) {
        Connection conn;
        conn = null;
        PreparedStatement pstm;
        pstm = null;
        ResultSet rs;
        rs = null;
        ArrayList<Lancamentos> lancamentos = new ArrayList<>();
        try {
            conn = Conectar.getConexao();
            pstm = conn.prepareStatement(LIST);
            pstm.setInt(1, lancamento.getIdCond());
            pstm.setString(2, lancamento.getReferencia());
            rs = pstm.executeQuery();
            while (rs.next()) {
                Lancamentos l = new Lancamentos();
                if ((rs.getInt("tbcondominio_idCondominio") == lancamento.getIdCond())
                        && (rs.getString("Referencia").equals(lancamento.getReferencia()))) {
                    l.setIdCond(rs.getInt("tbcondominio_idCondominio"));
                    l.setIdDesp(rs.getInt("tbDespesas_idDespesas"));
                    l.setReferencia(rs.getString("Referencia"));
                    l.setValor(rs.getFloat("Valor"));
                    l.setTipoRateio(rs.getInt("TipoRateio"));
                }
                lancamentos.add(l);
            }
            Conectar.fechaConexao(conn, pstm, rs);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar clientes" + e.getMessage());
        }
        return lancamentos;
    }

    public boolean FindKey(int codCond, int codDesp, String Referencia) {
        Connection conn;
        conn = null;
        PreparedStatement pstm;
        pstm = null;
        ResultSet rs;
        rs = null;
        boolean achouChave = false;

        try {
            conn = Conectar.getConexao();
            pstm = conn.prepareStatement(FindKey);
            pstm.setInt(1, codCond);
            pstm.setInt(2, codDesp);
            pstm.setString(3, Referencia);
            rs = pstm.executeQuery();

            while (rs.next()) {
                achouChave = true;
                break;
            }
            Conectar.fechaConexao(conn, pstm, rs);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar lançamentos" + e.getMessage());
        }
        return achouChave;
    }
}
