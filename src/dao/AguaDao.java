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
import javax.swing.JOptionPane;
import model.Agua;

/**
 *
 * @author Lucas_Reinert
 */
public class AguaDao {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private final String INSERT = "INSERT INTO tbAgua (tbapartamento_tbBloco_tbCondominio_idCondominio, tbapartamento_tbBloco_idBloco,"
            + "tbapartamento_idApart, Ano, Lei01, Lei02, Lei03, Lei04, Lei05, Lei06, Lei07, Lei08, Lei09, Lei10, Lei11, Lei12,"
            + "Data01, Data02, Data03, Data04, Data05, Data06, Data07, Data08, Data09, Data10, Data11, Data12)"
            + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private final String UPDATE = "UPDATE tbAgua SET "
            + "Lei01 = ?, Lei02 = ?, Lei03 = ?, Lei04 = ?, Lei05 = ?, Lei06 = ?, Lei07 = ?, Lei08 = ?, Lei09 = ?, Lei10 = ?, Lei11 = ?, Lei12 = ?, "
            + "Data01 = ?, Data02 = ?, Data03 = ?, Data04 = ?, Data05 = ?, Data06 = ?, Data07 = ?, Data08 = ?, Data09 = ?, Data10 = ?, Data11 = ?, Data12 = ? "
            + "where (tbapartamento_tbBloco_tbCondominio_idCondominio = ?) and (tbapartamento_tbBloco_idBloco = ?) and (tbapartamento_idApart = ?) and (Ano = ?)";

    private final String LIST = "SELECT * FROM tbAgua where (tbapartamento_tbBloco_tbCondominio_idCondominio = ?) and (tbapartamento_tbBloco_idBloco = ?) and (tbapartamento_idApart = ?) and (Ano = ?)";

    public void adicionar(Agua agu) {

        if (agu != null) {
            Connection conn;
            conn = null;
            try {
                conn = Conectar.getConexao();
                PreparedStatement pstm;
                pstm = conn.prepareStatement(INSERT);
                pstm.setInt(1, agu.getIdCond());
                pstm.setInt(2, agu.getIdBloco());
                pstm.setInt(3, agu.getIdApart());
                pstm.setInt(4, agu.getAno());

                pstm.setFloat(5, agu.getLei01());
                pstm.setFloat(6, agu.getLei02());
                pstm.setFloat(7, agu.getLei03());
                pstm.setFloat(8, agu.getLei04());
                pstm.setFloat(9, agu.getLei05());
                pstm.setFloat(10, agu.getLei06());
                pstm.setFloat(11, agu.getLei07());
                pstm.setFloat(12, agu.getLei08());
                pstm.setFloat(13, agu.getLei09());
                pstm.setFloat(14, agu.getLei10());
                pstm.setFloat(15, agu.getLei11());
                pstm.setFloat(16, agu.getLei12());

                pstm.setDate(17, agu.getData01());
                pstm.setDate(18, agu.getData02());
                pstm.setDate(19, agu.getData03());
                pstm.setDate(20, agu.getData04());
                pstm.setDate(21, agu.getData05());
                pstm.setDate(22, agu.getData06());
                pstm.setDate(23, agu.getData07());
                pstm.setDate(24, agu.getData08());
                pstm.setDate(25, agu.getData09());
                pstm.setDate(26, agu.getData10());
                pstm.setDate(27, agu.getData11());
                pstm.setDate(28, agu.getData12());
                pstm.execute();
                JOptionPane.showMessageDialog(null, "Consumo de agu cadastrado com sucesso");
                Conectar.fechaConexao(conn, pstm);

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao inserir consumo de agu no banco de"
                        + "dados " + e.getMessage());
            }
        } else {
            System.out.println("O água enviado por parâmetro está vazio");
        }
    }

    public void atualizar(Agua agu) {
        if (agu != null) {
            Connection conn;
            conn = null;
            try {
                conn = Conectar.getConexao();
                PreparedStatement pstm;
                pstm = conn.prepareStatement(UPDATE);
                pstm.setFloat(1, agu.getLei01());
                pstm.setFloat(2, agu.getLei02());
                pstm.setFloat(3, agu.getLei03());
                pstm.setFloat(4, agu.getLei04());
                pstm.setFloat(5, agu.getLei05());
                pstm.setFloat(6, agu.getLei06());
                pstm.setFloat(7, agu.getLei07());
                pstm.setFloat(8, agu.getLei08());
                pstm.setFloat(9, agu.getLei09());
                pstm.setFloat(10, agu.getLei10());
                pstm.setFloat(11, agu.getLei11());
                pstm.setFloat(12, agu.getLei12());
                pstm.setDate(13, agu.getData01());
                pstm.setDate(14, agu.getData02());
                pstm.setDate(15, agu.getData03());
                pstm.setDate(16, agu.getData04());
                pstm.setDate(17, agu.getData05());
                pstm.setDate(18, agu.getData06());
                pstm.setDate(19, agu.getData07());
                pstm.setDate(20, agu.getData08());
                pstm.setDate(21, agu.getData09());
                pstm.setDate(22, agu.getData10());
                pstm.setDate(23, agu.getData11());
                pstm.setDate(24, agu.getData12());
                pstm.setInt(25, agu.getIdCond());
                pstm.setInt(26, agu.getIdBloco());
                pstm.setInt(27, agu.getIdApart());
                pstm.setInt(28, agu.getAno());
                pstm.execute();
                JOptionPane.showMessageDialog(null, "Água alterado com sucesso");
                Conectar.fechaConexao(conn);

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao atualizar Água " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "O Água enviado por parâmetro está vazio");
        }
    }

    public Agua listar(Agua agua) {
        Connection conn;
        conn = null;
        PreparedStatement pstm;
        pstm = null;
        ResultSet rs;
        rs = null;
        boolean entrou = false;
        Agua agu = new Agua();
        try {
            conn = Conectar.getConexao();
            pstm = conn.prepareStatement(LIST);
            pstm.setInt(1, agua.getIdCond());
            pstm.setInt(2, agua.getIdBloco());
            pstm.setInt(3, agua.getIdApart());
            pstm.setInt(4, agua.getAno());
            rs = pstm.executeQuery();
            while (rs.next()) {
                agu.setLei01(rs.getFloat("Lei01"));
                agu.setLei02(rs.getFloat("Lei02"));
                agu.setLei03(rs.getFloat("Lei03"));
                agu.setLei04(rs.getFloat("Lei04"));
                agu.setLei05(rs.getFloat("Lei05"));
                agu.setLei06(rs.getFloat("Lei06"));
                agu.setLei07(rs.getFloat("Lei07"));
                agu.setLei08(rs.getFloat("Lei08"));
                agu.setLei09(rs.getFloat("Lei09"));
                agu.setLei10(rs.getFloat("Lei10"));
                agu.setLei11(rs.getFloat("Lei11"));
                agu.setLei12(rs.getFloat("Lei12"));
                entrou = true;
            }
            Conectar.fechaConexao(conn, pstm, rs);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar água" + e.getMessage());
        }
        if (entrou) {
            return agu;
        }else{
            return null;
        }
    }
}
