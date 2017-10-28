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
    private final String INSERT = "INSERT INTO tbLancDesp(tbcondominio_idCondominio, tbDespesas_idDespesas, Referencia, Serie,"
            + "Valor, TipoRateio)"
            + "VALUES (?,?,?,?,?)";
    private final String UPDATE = "UPDATE tbLancDesp SET "
            + "Referencia = ?,"
            + "Serie = ?,"
            + "Valor = ?,"
            + "TipoRateio = ?"
            + "WHERE (tbcondominio_idCondominio = ?) and (tbDespesas_idDespesas = ?) and (Referencia = ?) and (Serie = ?)";
    private final String LIST = "SELECT * FROM tbLancDesp where (tbcondominio_idCondominio = ?)";
    private final String DELETE = "DELETE FROM tbLancDesp WHERE (tbcondominio_idCondominio = ?) and (tbDespesas_idDespesas = ?) and (Referencia = ?) and (Serie = ?)";
    private final String FindKey = "SELECT * from tbLancDesp WHERE (tbcondominio_idCondominio = ?) and (tbDespesas_idDespesas = ?) and (Referencia = ?) and (Serie = ?)";

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
                pstm.setString(4, lanc.getSerie());
                pstm.setFloat(5, lanc.getValor());
                pstm.setInt(6, lanc.getTipoRateio());
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
                
                pstm.execute();
                JOptionPane.showMessageDialog(null, "Lançamento alterado com sucesso");
                Conectar.fechaConexao(conn);

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao atualizar o " + lanc.getIdDesp()+ e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "O lançamento enviado por parâmetro está vazio");
        }
    }

    public void remover(int codCond, int codBloco, int codApart) {

        Connection conn;
        conn = null;
        try {
            conn = Conectar.getConexao();
            PreparedStatement pstm;
            pstm = conn.prepareStatement(DELETE);

            pstm.setInt(1, codCond);
            pstm.setInt(2, codBloco);
            pstm.setInt(2, codApart);

            pstm.execute();
            Conectar.fechaConexao(conn, pstm);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir apartamento do banco de dados " + e.getMessage());
        }
    }

    public List<Apartamento> listar() {
        Connection conn;
        conn = null;
        PreparedStatement pstm;
        pstm = null;
        ResultSet rs;
        rs = null;
        ArrayList<Apartamento> apartamentos = new ArrayList<>();
        try {
            conn = Conectar.getConexao();
            pstm = conn.prepareStatement(LIST);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Apartamento a = new Apartamento();
                a.setIdCond(rs.getInt("tbBloco_tbCondominio_idCondominio"));
                a.setIdBloco(rs.getInt("tbBloco_idBloco"));
                a.setIdApart(rs.getInt("idApart"));
                apartamentos.add(a);
            }
            Conectar.fechaConexao(conn, pstm, rs);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar clientes" + e.getMessage());
        }
        return apartamentos;
    }
    public boolean FindKey(int codCond, int codBlo, int codApart){
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
            pstm.setInt(2, codBlo);   
            pstm.setInt(3, codApart);   
            rs = pstm.executeQuery();
            
            while (rs.next()){
                achouChave = true;
                break;
            }
            Conectar.fechaConexao(conn, pstm, rs);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar apartamentos" + e.getMessage());
        }
        return achouChave;
    }
}
