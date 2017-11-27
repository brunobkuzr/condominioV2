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
import model.Bloco;

/**
 *
 * @author Lucas_Reinert
 */
public class BlocoDao {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private final String INSERT = "INSERT INTO tbBloco(tbCondominio_idCondominio, idBloco,"
            + "nomeBloco)"
            + "VALUES (?,?,?)";
    private final String UPDATE = "UPDATE tbBloco SET "
            + "nomeBloco = ? "
            + "WHERE (tbCondominio_idCondominio = ?) and (idBloco = ?) ";
    private final String LIST = "SELECT * FROM tbBloco";
    private final String DELETE = "DELETE FROM tbBloco WHERE (tbCondominio_idCondominio = ?) and (idBloco = ?)";
    private final String FindKey = "SELECT * from tbBloco WHERE (tbCondominio_idCondominio = ?) and (idBloco = ?) ";

    public void adicionar(Bloco blo) {

        if (blo != null) {
            Connection conn;
            conn = null;
            try {
                conn = Conectar.getConexao();
                PreparedStatement pstm;
                pstm = conn.prepareStatement(INSERT);
                pstm.setInt(1, blo.getIdCondominio());
                pstm.setInt(2, blo.getIdBloco());
                pstm.setString(3, blo.getNome());
                pstm.execute();
                JOptionPane.showMessageDialog(null, "Bloco cadastrado com sucesso");
                Conectar.fechaConexao(conn, pstm);

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao inserir bloco no banco de"
                        + "dados " + e.getMessage());
            }
        } else {
            System.out.println("O bloco enviado por parâmetro está vazio");
        }
    }

    public void atualizar(Bloco blo) {
        if (blo != null) {
            Connection conn;
            conn = null;
            try {
                conn = Conectar.getConexao();
                PreparedStatement pstm;
                pstm = conn.prepareStatement(UPDATE);
                
                pstm.setString(1, blo.getNome());
                pstm.setInt(2, blo.getIdCondominio());
                pstm.setInt(3, blo.getIdBloco());
                pstm.execute();
                JOptionPane.showMessageDialog(null, "Bloco alterado com sucesso");
                Conectar.fechaConexao(conn);

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao atualizar o " + blo.getIdBloco() + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "O bloco enviado por parâmetro está vazio");
        }
    }

    public void remover(int codCond, int codBloco) {

        Connection conn;
        conn = null;
        try {
            conn = Conectar.getConexao();
            PreparedStatement pstm;
            pstm = conn.prepareStatement(DELETE);

            pstm.setInt(1, codCond);
            pstm.setInt(2, codBloco);

            pstm.execute();
            Conectar.fechaConexao(conn, pstm);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir bloco do banco de dados " + e.getMessage());
        }
    }

    public ArrayList<Bloco> listar() {
        Connection conn;
        conn = null;
        PreparedStatement pstm;
        pstm = null;
        ResultSet rs;
        rs = null;
        ArrayList<Bloco> blocos = new ArrayList<Bloco>();
        try {
            conn = Conectar.getConexao();
            pstm = conn.prepareStatement(LIST);            
            rs = pstm.executeQuery();
            while (rs.next()) {
                Bloco b = new Bloco();
                b.setIdCondominio(rs.getInt("tbCondominio_idCondominio"));
                b.setIdBloco(rs.getInt("idBloco"));
                b.setNome(rs.getString("nomeBloco"));
                blocos.add(b);
            }
            Conectar.fechaConexao(conn, pstm, rs);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar blocos " + e.getMessage());
        }
        return blocos;
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
            pstm.setInt(2, codBlo);   
            rs = pstm.executeQuery();
            Bloco c = new Bloco();
            
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
