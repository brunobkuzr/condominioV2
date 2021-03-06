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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Escritorio;

/**
 *
 * @author Lucas_Reinert
 */
public class EscritorioDao {
        
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private final String INSERT = "INSERT INTO tbEscritorio (nome, telefone,"
            + "email, logradouro, endereco, bairro,"
            + "numero, complemento)"
            + "VALUES (?,?,?,?,?,?,?,?)";
    private final String UPDATE = "UPDATE tbEscritorio SET "
            + "nome = ?,"
            + "telefone = ?,"
            + "email = ?,"
            + "logradouro = ?,"
            + "endereco = ?,"
            + "bairro = ?,"
            + "numero = ?, "
            + "complemento";
          //  + "WHERE Codigo_Cliente = ?";
    private final String LIST = "SELECT *FROM tbEscritorio";
    private final String EXISTS = "SELECT Count (*) from tbEscritorio";
//    private final String DELETE = "DELETE FROM tbEscritorio WHERE idCondominio =?";

    public void adicionar(Escritorio esc) {

        if (esc != null) {
            Connection conn;
            conn = null;
            try {
                conn = Conectar.getConexao();
                PreparedStatement pstm;
                pstm = conn.prepareStatement(INSERT);
                pstm.setString(1, esc.getNome());
                pstm.setString(2, esc.getTelefone());
                pstm.setString(3, esc.getEmail());
                pstm.setInt(4, esc.getLogradouro());
                pstm.setString(5, esc.getEndereco());
                pstm.setString(6, esc.getBairro());
                pstm.setString(7, esc.getNumero());
                pstm.setString(8, esc.getComplemento());

                pstm.execute();
                JOptionPane.showMessageDialog(null, "Escritório cadastrado com sucesso");
                Conectar.fechaConexao(conn, pstm);

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao inserir escritório no banco de"
                        + "dados " + e.getMessage());
            }
        } else {
            System.out.println("O escritório enviado por parâmetro está vazio");
        }
    }

    public void atualizar(Escritorio esc) {
        if (esc != null) {
            Connection conn;
            conn = null;
            try {
                conn = Conectar.getConexao();
                PreparedStatement pstm;
                pstm = conn.prepareStatement(UPDATE);
                pstm.setString(1, esc.getNome());
                pstm.setString(2, esc.getTelefone());
                pstm.setString(3, esc.getEmail());
                pstm.setInt(4, esc.getLogradouro());
                pstm.setString(5, esc.getEndereco());
                pstm.setString(6, esc.getBairro());
                pstm.setString(7, esc.getNumero());
                pstm.setString(8, esc.getComplemento());

                pstm.execute();
                JOptionPane.showMessageDialog(null, "escritório alterado com sucesso");
                Conectar.fechaConexao(conn);

            } catch (SQLException e) {
            }
        } else {
            JOptionPane.showMessageDialog(null, "O escritório enviado por parâmetro está vazio");
        }
    }
    
    public boolean existeCadastro(){
        boolean existeCadastro = true;
        Connection conn;
        conn = null;
        conn = Conectar.getConexao();
        PreparedStatement pstm;
        ResultSet rs = null;
        try {
            pstm = conn.prepareStatement(EXISTS);
            pstm.execute();
            rs = pstm.executeQuery();
            if (rs.wasNull()) {
                existeCadastro = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EscritorioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return existeCadastro;
    } 

//    public void remover(int cod) {
//
//        Connection conn;
//        conn = null;
//        try {
//            conn = Conectar.getConexao();
//            PreparedStatement pstm;
//            pstm = conn.prepareStatement(DELETE);
//
//            pstm.setInt(1, cod);
//
//            pstm.execute();
//            Conectar.fechaConexao(conn, pstm);
//
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, "Erro ao excluir cliente do banco de dados " + e.getMessage());
//        }
//    }

//    public List<Condominio> listar() {
//        Connection conn;
//        conn = null;
//        PreparedStatement pstm;
//        pstm = null;
//        ResultSet rs;
//        rs = null;
//        ArrayList<Condominio> condominios = new ArrayList<Condominio>();
//        try {
//            conn = Conectar.getConexao();
//            pstm = conn.prepareStatement(LIST);
//            rs = pstm.executeQuery();
//            while (rs.next()) {
//                Condominio c = new Condominio();
//                c.setId(rs.getInt("idCondominio"));
//                c.setNome(rs.getString("nomeCondominio"));
//                c.setCoeficiente(rs.getFloat("coeficiente"));
//                condominios.add(c);
//            }
//            Conectar.fechaConexao(conn, pstm, rs);
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, "Erro ao listar clientes" + e.getMessage());
//        }
//        return condominios;
//    }
}
