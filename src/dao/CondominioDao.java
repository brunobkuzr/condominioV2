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
import model.Condominio;
import java.util.List;

/**
 *
 * @author Lucas_Reinert
 */
public class CondominioDao {
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private final String INSERT = "INSERT INTO tbCondominio (idCondominio, nomeCondominio,"
            + "telefone, email, coeficiente, logradouro,"
            + "endereco, bairro, numero, complemento)"
            + "VALUES (?,?,?,?,?,?,?,?,?,?)";
    private final String UPDATE = "UPDATE tbCondominio SET "
            + "nomeCondominio = ?,"
            + "telefone = ?,"
            + "email = ?,"
            + "coeficiente = ?,"
            + "logradouro = ?,"
            + "endereco = ?,"
            + "bairro = ?,"
            + "numero = ?, "
            + "complemento = ? "
            + "WHERE idCondominio = ?";
    private final String LIST = "SELECT *FROM tbCondominio";
    private final String DELETE = "DELETE FROM tbCondominio WHERE idCondominio =?";
    private final String FindKey = "SELECT * from tbCondominio WHERE idCondominio = ? ";
        private final String LISTA = "SELECT *FROM tbCondominio WHERE idCondominio = ?";


    public void adicionar(Condominio con) {

        if (con != null) {
            Connection conn;
            conn = null;
            try {
                conn = Conectar.getConexao();
                PreparedStatement pstm;
                pstm = conn.prepareStatement(INSERT);
                pstm.setInt(1, con.getId());
                pstm.setString(2, con.getNome());
                pstm.setString(3, con.getTelefone());
                pstm.setString(4, con.getEmail());
                pstm.setFloat( 5, con.getCoeficiente());
                pstm.setInt(6, con.getLogradouro());
                pstm.setString(7, con.getEndereco());
                pstm.setString(8, con.getBairro());
                pstm.setString(9, con.getNumero());
                pstm.setString(10, con.getComplemento());

                pstm.execute();
                JOptionPane.showMessageDialog(null, "Condomínio cadastrado com sucesso");
                Conectar.fechaConexao(conn, pstm);

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao inserir condomínio no banco de"
                        + "dados " + e.getMessage());
            }
        } else {
            System.out.println("O condomínio enviado por parâmetro está vazio");
        }
    }

    public void atualizar(Condominio con) {
        if (con != null) {
            Connection conn;
            conn = null;
            try {
                conn = Conectar.getConexao();
                PreparedStatement pstm;
                pstm = conn.prepareStatement(UPDATE);
                
                pstm.setString(1, con.getNome());
                pstm.setString(2, con.getTelefone());
                pstm.setString(3, con.getEmail());
                pstm.setFloat( 4, con.getCoeficiente());
                pstm.setInt(5, con.getLogradouro());
                pstm.setString(6, con.getEndereco());
                pstm.setString(7, con.getBairro());
                pstm.setString(8, con.getNumero());
                pstm.setString(9, con.getComplemento());
                pstm.setInt(10, con.getId());
                pstm.execute();
                JOptionPane.showMessageDialog(null, "Condomínio alterado com sucesso");
                Conectar.fechaConexao(conn);

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao atualizar o usuário " + con.getId() + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "O Condomínio enviado por parâmetro está vazio");
        }
    }

    public void remover(int cod) {

        Connection conn;
        conn = null;
        try {
            conn = Conectar.getConexao();
            PreparedStatement pstm;
            pstm = conn.prepareStatement(DELETE);

            pstm.setInt(1, cod);

            pstm.execute();
            Conectar.fechaConexao(conn, pstm);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir Condomínio do banco de dados " + e.getMessage());
        }
    }

    public ArrayList<Condominio> listar() {
        Connection conn;
        conn = null;
        PreparedStatement pstm;
        pstm = null;
        ResultSet rs;
        rs = null;
        ArrayList<Condominio> condominios = new ArrayList<Condominio>();
        try {
            conn = Conectar.getConexao();
            pstm = conn.prepareStatement(LIST);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Condominio c = new Condominio();
                c.setId(rs.getInt("idCondominio"));
                c.setNome(rs.getString("nomeCondominio"));
                c.setTelefone(rs.getString("telefone"));
                c.setEmail(rs.getString("email"));
                c.setCoeficiente(rs.getFloat("coeficiente"));
                c.setLogradouro(rs.getInt("logradouro"));
                c.setEndereco(rs.getString("endereco"));
                c.setBairro(rs.getString("bairro"));
                c.setNumero(rs.getString("numero"));
                c.setComplemento(rs.getString("complemento"));
                condominios.add(c);
            }
            Conectar.fechaConexao(conn, pstm, rs);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar Condomínios" + e.getMessage());
        }
        return condominios;
    }
    
    public Condominio buscarCondominio(int idCond) {
        Connection conn;
        conn = null;
        PreparedStatement pstm;
        pstm = null;
        ResultSet rs;
        Condominio cond = new Condominio();
        rs = null;
        ArrayList<Condominio> condominios = new ArrayList<Condominio>();
        try {
            conn = Conectar.getConexao();
            pstm = conn.prepareStatement(LISTA);
            pstm.setInt(1, idCond);
            rs = pstm.executeQuery();
            while (rs.next()) {
                if(rs.getInt("idCondominio") == idCond){
                    
                cond.setNome(rs.getString("nomeCondominio"));
                cond.setCoeficiente(rs.getFloat("coeficiente"));
                
                       
                break;
                }
            }
            Conectar.fechaConexao(conn, pstm, rs);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar Condomínios" + e.getMessage());
        }
        return cond;
    }
    public boolean FindKey(int cod){
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
            pstm.setInt(1, cod);            
            rs = pstm.executeQuery();
            Condominio c = new Condominio();
            
            while (rs.next()){
                achouChave = true;
            }
            Conectar.fechaConexao(conn, pstm, rs);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar Condomínios" + e.getMessage());
        }
        return achouChave;
    }
}
