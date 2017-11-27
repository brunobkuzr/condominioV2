/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Lucas_Reinert
 */
public class Conectar {
    private static final String URL = "jdbc:mysql://localhost/";
    private static final String BASE = "condominio";
    private static final String USUARIO = "root";
    private static final String SENHA = "";
    public Connection con;
    public Statement stmt;
    public ResultSet rs;

    public static Connection getConexao() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(URL + BASE, USUARIO, SENHA);
    }catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e1) {
            System.out.println(e1.getMessage());
        }
        return conn;
    }

    
    public void conexao(){
        try{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(URL + BASE, USUARIO, SENHA);
        stmt = con.createStatement();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao conectar Banco de Dados");
        }
    }

    public static void fechaConexao(Connection conn) {

        try {
            if (conn != null) {
                conn.close();
                System.out.println("Fechada a conexão com o banco de dados");
            }

        } catch (Exception e) {
            System.out.println("Não foi possível fechar a conexão com o banco de dados " + e.getMessage());
        }
    }

    public static void fechaConexao(Connection conn, PreparedStatement stmt) {

        try {
            if (conn != null) {
                fechaConexao(conn);
            }
            if (stmt != null) {
                stmt.close();
                System.out.println("Statement fechado com sucesso");
            }

        } catch (Exception e) {
            System.out.println("Não foi possível fechar o statement " + e.getMessage());
        }
    }

    public static void fechaConexao(Connection conn, PreparedStatement stmt, ResultSet rs) {

        try {
            if (conn != null || stmt != null) {
                fechaConexao(conn, stmt);
            }
            if (rs != null) {
                rs.close();
                System.out.println("ResultSet fechado com sucesso");
            }

        } catch (Exception e) {
            System.out.println("Não foi possível fechar o ResultSet " + e.getMessage());
        }
    }
}
