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
import model.Despesa;

/**
 *
 * @author Lucas_Reinert
 */
public class DespesaDao {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private final String INSERT = "INSERT INTO tbDespesas(idDespesas,"
            + "nomeDesp)"
            + "VALUES (?,?)";
    private final String UPDATE = "UPDATE tbDespesas SET "
            + "nomeDesp = ?"
            + "WHERE (idDespesas = ?) ";
    private final String LIST = "SELECT * FROM tbDespesas";
    private final String DELETE = "DELETE FROM tbDespesas WHERE (idDespesas = ?)";
    private final String FindKey = "SELECT * from tbDespesas WHERE (idDespesas = ?)";

    public void adicionar(Despesa desp) {

        if (desp != null) {
            Connection conn;
            conn = null;
            try {
                conn = Conectar.getConexao();
                PreparedStatement pstm;
                pstm = conn.prepareStatement(INSERT);
                pstm.setInt(1, desp.getId());
                pstm.setString(2, desp.getNome());

                pstm.execute();
                JOptionPane.showMessageDialog(null, "Despesa cadastrada com sucesso");
                Conectar.fechaConexao(conn, pstm);

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao inserir a despesa no banco de"
                        + "dados " + e.getMessage());
            }
        } else {
            System.out.println("A Despesa enviado por parâmetro está vazio");
        }
    }

    public void atualizar(Despesa desp) {
        if (desp != null) {
            Connection conn;
            conn = null;
            try {
                conn = Conectar.getConexao();
                PreparedStatement pstm;
                pstm = conn.prepareStatement(UPDATE);

                pstm.setString(1, desp.getNome());
                pstm.setInt(2, desp.getId());
                pstm.execute();
                JOptionPane.showMessageDialog(null, "Despesa alterada com sucesso");
                Conectar.fechaConexao(conn);

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao atualizar o " + desp.getId() + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "A Despesa enviado por parâmetro está vazio");
        }
    }

    public void remover(int codDesp) {

        Connection conn;
        conn = null;
        try {
            conn = Conectar.getConexao();
            PreparedStatement pstm;
            pstm = conn.prepareStatement(DELETE);
            pstm.setInt(1, codDesp);
            pstm.execute();
            Conectar.fechaConexao(conn, pstm);
            JOptionPane.showMessageDialog(null, "Despesa excluida com sucesso.");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir despesa do banco de dados " + e.getMessage());
        }
    }

    public List<Despesa> listar() {
        Connection conn;
        conn = null;
        PreparedStatement pstm;
        pstm = null;
        ResultSet rs;
        rs = null;
        ArrayList<Despesa> despesas = new ArrayList<Despesa>();
        try {
            conn = Conectar.getConexao();
            pstm = conn.prepareStatement(LIST);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Despesa d = new Despesa();
                d.setId(rs.getInt("idDespesas"));
                d.setNome(rs.getString("nomeDesp"));
                despesas.add(d);
            }
            Conectar.fechaConexao(conn, pstm, rs);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar clientes" + e.getMessage());
        }
        return despesas;
    }

    public boolean FindKey(int codDesp) {
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
            pstm.setInt(1, codDesp);
            rs = pstm.executeQuery();

            while (rs.next()) {
                achouChave = true;
                break;
            }
            Conectar.fechaConexao(conn, pstm, rs);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar blocos" + e.getMessage());
        }
        return achouChave;
    }

}
