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
import model.Rateio;

/**
 *
 * @author Lucas_Reinert
 */
public class RateioDao {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private final String INSERT = "INSERT INTO tbRateio(tbapartamento_tbBloco_tbCondominio_idCondominio, tbapartamento_tbBloco_idBloco,"
            + "tbapartamento_idApart, Referencia, Valor)"
            + "VALUES (?,?,?,?,?)";
    private final String UPDATE = "UPDATE tbRateio SET "
            + "Valor = ?"
            + "WHERE (tbapartamento_tbBloco_tbCondominio_idCondominio = ?) and (tbapartamento_tbBloco_idBloco = ?) and (tbapartamento_idApart = ?) and (Referencia = ?) ";
    private final String LIST = "SELECT * FROM tbRateio where (tbapartamento_tbBloco_tbCondominio_idCondominio = ?) and (tbapartamento_tbBloco_idBloco = ?) and"
            + "(tbapartamento_idApart = ?) and  (Referencia = ?)";
    private final String DELETE = "DELETE FROM tbRateio WHERE (tbapartamento_tbBloco_tbCondominio_idCondominio = ?) and (tbapartamento_tbBloco_idBloco = ?) and"
            + "(tbapartamento_idApart = ?) and  (Referencia = ?)";
    private final String FindKey = "SELECT * from tbRateio WHERE (tbapartamento_tbBloco_tbCondominio_idCondominio = ?) and (tbapartamento_tbBloco_idBloco = ?) and"
            + "(tbapartamento_idApart = ?) and  (Referencia = ?) ";

    public void adicionar(Rateio rat) {

        if (rat != null) {
            Connection conn;
            conn = null;
            try {
                conn = Conectar.getConexao();
                PreparedStatement pstm;
                pstm = conn.prepareStatement(INSERT);
                pstm.setInt(1, rat.getIdCond());
                pstm.setInt(2, rat.getIdBloco());
                pstm.setInt(3, rat.getIdApart());
                pstm.setString(4, rat.getReferencia());
                pstm.setFloat( 5, rat.getValor());

                pstm.execute();
                JOptionPane.showMessageDialog(null, "Rateio cadastrado com sucesso");
                Conectar.fechaConexao(conn, pstm);

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao inserir bloco no banco de"
                        + "dados " + e.getMessage());
            }
        } else {
            System.out.println("O bloco enviado por parâmetro está vazio");
        }
    }

    public void atualizar(Rateio rat) {
        if (rat != null) {
            Connection conn;
            conn = null;
            try {
                conn = Conectar.getConexao();
                PreparedStatement pstm;
                pstm = conn.prepareStatement(UPDATE);
                
                pstm.setFloat( 1, rat.getValor());
                pstm.setInt(2, rat.getIdCond());
                pstm.setInt(3, rat.getIdBloco());
                pstm.setInt(4, rat.getIdApart());
                pstm.setString(5, rat.getReferencia());
                pstm.execute();
                JOptionPane.showMessageDialog(null, "Rateio alterado com sucesso");
                Conectar.fechaConexao(conn);

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao atualizar o rateio bloco: " + rat.getIdBloco() + " apartamento: " +rat.getIdApart() + " "+ e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "O rateio enviado por parâmetro está vazio");
        }
    };

    public void remover(int codCond, int codBlo, int codApa, String referencia) {

        Connection conn;
        try {
            conn = Conectar.getConexao();
            PreparedStatement pstm;
            pstm = conn.prepareStatement(DELETE);

            pstm.setInt(1, codCond);
            pstm.setInt(2, codBlo);
            pstm.setInt(3, codApa);
            pstm.setString(4, referencia);
            
            pstm.execute();
            Conectar.fechaConexao(conn, pstm);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir bloco do banco de dados " + e.getMessage());
        }
    }

    public List<Rateio> listar() {
        Connection conn;
        conn = null;
        PreparedStatement pstm;
        pstm = null;
        ResultSet rs;
        rs = null;
        ArrayList<Rateio> rateios = new ArrayList<Rateio>();
        try {
            conn = Conectar.getConexao();
            pstm = conn.prepareStatement(LIST);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Rateio r = new Rateio();
                r.setIdCond(rs.getInt("tbapartamento_tbBloco_tbCondominio_idCondominio"));
                r.setIdBloco(rs.getInt("tbapartamento_tbBloco_idBloco"));
                r.setIdApart(rs.getInt("tbapartamento_idApart"));
                r.setReferencia(rs.getString("Referencia"));
                r.setValor(rs.getFloat("Valor"));
                rateios.add(r);
            }
            Conectar.fechaConexao(conn, pstm, rs);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar clientes" + e.getMessage());
        }
        return rateios;
    }
    public boolean FindKey(int codCond, int codBlo){
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
            pstm.setInt(1, codBlo);   
            rs = pstm.executeQuery();
            Rateio c = new Rateio();
            
            while (rs.next()){
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
