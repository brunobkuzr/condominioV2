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
import model.Gas;

/**
 *
 * @author Lucas_Reinert
 */
public class GasDao {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private final String INSERT = "INSERT INTO tbGas (tbapartamento_tbBloco_tbCondominio_idCondominio, tbapartamento_tbBloco_idBloco,"
            + "tbapartamento_idApart, Ano, Lei01, Lei02, Lei03, Lei04, Lei05, Lei06, Lei07, Lei08, Lei09, Lei10, Lei11, Lei12,"
            + "Data01, Data02, Data03, Data04, Data05, Data06, Data07, Data08, Data09, Data10, Data11, Data12)"
            + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private final String UPDATE = "UPDATE tbGas SET "
            + "Lei01 = ?, Lei02 = ?, Lei03 = ?, Lei04 = ?, Lei05 = ?, Lei06 = ?, Lei07 = ?, Lei08 = ?, Lei09 = ?, Lei10 = ?, Lei11 = ?, Lei12 = ?, "
            + "Data01 = ?, Data02 = ?, Data03 = ?, Data04 = ?, Data05 = ?, Data06 = ?, Data07 = ?, Data08 = ?, Data09 = ?, Data10 = ?, Data11 = ?, Data12 = ? "
            + "where (tbapartamento_tbBloco_tbCondominio_idCondominio = ?) and (tbapartamento_tbBloco_idBloco = ?) and (tbapartamento_idApart = ?) and (Ano = ?)";

    private final String LIST = "SELECT * FROM tbGas where (tbapartamento_tbBloco_tbCondominio_idCondominio = ?) and (tbapartamento_tbBloco_idBloco = ?) and (tbapartamento_idApart = ?) and (Ano = ?)";

    public GasDao() {
    }

    public void adicionar(Gas g) {

        if (g != null) {
            Connection conn;
            conn = null;
            try {
                conn = Conectar.getConexao();
                PreparedStatement pstm;
                pstm = conn.prepareStatement(INSERT);
                pstm.setInt(1, g.getIdCond());
                pstm.setInt(2, g.getIdBloco());
                pstm.setInt(3, g.getIdApart());
                pstm.setInt(4, g.getAno());

                pstm.setFloat(5, g.getLei01());
                pstm.setFloat(6, g.getLei02());
                pstm.setFloat(7, g.getLei03());
                pstm.setFloat(8, g.getLei04());
                pstm.setFloat(9, g.getLei05());
                pstm.setFloat(10, g.getLei06());
                pstm.setFloat(11, g.getLei07());
                pstm.setFloat(12, g.getLei08());
                pstm.setFloat(13, g.getLei09());
                pstm.setFloat(14, g.getLei10());
                pstm.setFloat(15, g.getLei11());
                pstm.setFloat(16, g.getLei12());

                pstm.setDate(17, g.getData01());
                pstm.setDate(18, g.getData02());
                pstm.setDate(19, g.getData03());
                pstm.setDate(20, g.getData04());
                pstm.setDate(21, g.getData05());
                pstm.setDate(22, g.getData06());
                pstm.setDate(23, g.getData07());
                pstm.setDate(24, g.getData08());
                pstm.setDate(25, g.getData09());
                pstm.setDate(26, g.getData10());
                pstm.setDate(27, g.getData11());
                pstm.setDate(28, g.getData12());
                pstm.execute();
                JOptionPane.showMessageDialog(null, "Consumo de gas cadastrado com sucesso");
                Conectar.fechaConexao(conn, pstm);

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao inserir consumo de gas no banco de"
                        + "dados " + e.getMessage());
            }
        } else {
            System.out.println("O gás enviado por parâmetro está vazio");
        }
    }

    public void atualizar(Gas g) {
        if (g != null) {
            Connection conn;
            conn = null;
            try {
                conn = Conectar.getConexao();
                PreparedStatement pstm;
                pstm = conn.prepareStatement(UPDATE);
                pstm.setFloat(1, g.getLei01());
                pstm.setFloat(2, g.getLei02());
                pstm.setFloat(3, g.getLei03());
                pstm.setFloat(4, g.getLei04());
                pstm.setFloat(5, g.getLei05());
                pstm.setFloat(6, g.getLei06());
                pstm.setFloat(7, g.getLei07());
                pstm.setFloat(8, g.getLei08());
                pstm.setFloat(9, g.getLei09());
                pstm.setFloat(10, g.getLei10());
                pstm.setFloat(11, g.getLei11());
                pstm.setFloat(12, g.getLei12());
                pstm.setDate(13, g.getData01());
                pstm.setDate(14, g.getData02());
                pstm.setDate(15, g.getData03());
                pstm.setDate(16, g.getData04());
                pstm.setDate(17, g.getData05());
                pstm.setDate(18, g.getData06());
                pstm.setDate(19, g.getData07());
                pstm.setDate(20, g.getData08());
                pstm.setDate(21, g.getData09());
                pstm.setDate(22, g.getData10());
                pstm.setDate(23, g.getData11());
                pstm.setDate(24, g.getData12());
                pstm.setInt(25, g.getIdCond());
                pstm.setInt(26, g.getIdBloco());
                pstm.setInt(27, g.getIdApart());
                pstm.setInt(28, g.getAno());
                pstm.execute();
                JOptionPane.showMessageDialog(null, "gas alterado com sucesso");
                Conectar.fechaConexao(conn);

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao atualizar gas");
            }
        } else {
            JOptionPane.showMessageDialog(null, "O gas enviado por parâmetro está vazio");
        }
    }

    public Gas listar(Gas gas) {
        Connection conn;
        conn = null;
        PreparedStatement pstm;
        pstm = null;
        ResultSet rs;
        rs = null;
        Gas g = new Gas();
        boolean entrou = false;
        try {
            conn = Conectar.getConexao();
            pstm = conn.prepareStatement(LIST);
            pstm.setInt(1, gas.getIdCond());
            pstm.setInt(2, gas.getIdBloco());
            pstm.setInt(3, gas.getIdApart());
            pstm.setInt(4, gas.getAno());
            rs = pstm.executeQuery();
            while (rs.next()) {
                g.setLei01(rs.getFloat("Lei01"));
                g.setLei02(rs.getFloat("Lei02"));
                g.setLei03(rs.getFloat("Lei03"));
                g.setLei04(rs.getFloat("Lei04"));
                g.setLei05(rs.getFloat("Lei05"));
                g.setLei06(rs.getFloat("Lei06"));
                g.setLei07(rs.getFloat("Lei07"));
                g.setLei08(rs.getFloat("Lei08"));
                g.setLei09(rs.getFloat("Lei09"));
                g.setLei10(rs.getFloat("Lei10"));
                g.setLei11(rs.getFloat("Lei11"));
                g.setLei12(rs.getFloat("Lei12"));
                entrou = true;
            }
            Conectar.fechaConexao(conn, pstm, rs);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar gas" + e.getMessage());
        }
        if (entrou) {
            return g;
        } else {
            return null;
    
        }
    }
}
