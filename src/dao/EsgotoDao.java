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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Escritorio;
import model.Esgoto;

/**
 *
 * @author Lucas_Reinert
 */
public class EsgotoDao {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private final String INSERT = "INSERT INTO tbEsgoto (tbapartamento_tbBloco_tbCondominio_idCondominio, tbapartamento_tbBloco_idBloco,"
            + "tbapartamento_idApart, Ano, Lei01, Lei02, Lei03, Lei04, Lei05, Lei06, Lei07, Lei08, Lei09, Lei10, Lei11, Lei12"
            + "Data01, Data02, Data03, Data04, Data05, Data06, Data07, Data08, Data09, Data10, Data11, Data12)"
            + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private final String UPDATE = "UPDATE tbEsgoto SET "
            + "Lei01 = ?, Lei02 = ?, Lei03 = ?, Lei04 = ?, Lei05 = ?, Lei06 = ?, Lei07 = ?, Lei08 = ?, Lei09 = ?, Lei10 = ?, Lei11 = ?, Lei12 = ?"
            + "Data01 = ?, Data02 = ?, Data03 = ?, Data04 = ?, Data05 = ?, Data06 = ?, Data07 = ?, Data08 = ?, Data09 = ?, Data10 = ?, Data11 = ?, Data12 = ? "
            + "where (tbapartamento_tbBloco_tbCondominio_idCondominio = ?) and (tbapartamento_tbBloco_idBloco = ?) and (tbapartamento_idApart = ?) and (Ano = ?)";
    
    private final String LIST = "SELECT * FROM tbEsgoto where (tbapartamento_tbBloco_tbCondominio_idCondominio = ?) and (tbapartamento_tbBloco_idBloco = ?) and (tbapartamento_idApart = ?) and (Ano = ?)";

    public void adicionar(Esgoto esg) {

        if (esg != null) {
            Connection conn;
            conn = null;
            try {
                conn = Conectar.getConexao();
                PreparedStatement pstm;
                pstm = conn.prepareStatement(INSERT);
                pstm.setInt(1, esg.getIdCond());
                pstm.setInt(2, esg.getIdBloco());
                pstm.setInt(3, esg.getIdApart());
                pstm.setInt(4, esg.getAno());
                
                pstm.setFloat(5, esg.getLei01());
                pstm.setFloat(6, esg.getLei02());
                pstm.setFloat(7, esg.getLei03());
                pstm.setFloat(8, esg.getLei04());
                pstm.setFloat(9, esg.getLei05());
                pstm.setFloat(10, esg.getLei06());
                pstm.setFloat(11, esg.getLei07());
                pstm.setFloat(12, esg.getLei08());
                pstm.setFloat(13, esg.getLei09());
                pstm.setFloat(14, esg.getLei10());
                pstm.setFloat(15, esg.getLei11());
                pstm.setFloat(16, esg.getLei12());
                
                pstm.setDate(17, esg.getData01());
                pstm.setDate(18, esg.getData02());
                pstm.setDate(19, esg.getData03());
                pstm.setDate(20, esg.getData04());
                pstm.setDate(21, esg.getData05());
                pstm.setDate(22, esg.getData06());
                pstm.setDate(23, esg.getData07());
                pstm.setDate(24, esg.getData08());
                pstm.setDate(25, esg.getData09());
                pstm.setDate(26, esg.getData10());
                pstm.setDate(27, esg.getData11());
                pstm.setDate(28, esg.getData12());
                pstm.execute();
                JOptionPane.showMessageDialog(null, "Consumo de esgoto cadastrado com sucesso");
                Conectar.fechaConexao(conn, pstm);

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao inserir consumo de esgoto no banco de"
                        + "dados " + e.getMessage());
            }
        } else {
            System.out.println("O esgoto enviado por parâmetro está vazio");
        }
    }

    public void atualizar(Esgoto esg) {
        if (esg != null) {
            Connection conn;
            conn = null;
            try {
                conn = Conectar.getConexao();
                PreparedStatement pstm;
                pstm = conn.prepareStatement(UPDATE);                
                pstm.setFloat(5, esg.getLei01());
                pstm.setFloat(6, esg.getLei02());
                pstm.setFloat(7, esg.getLei03());
                pstm.setFloat(8, esg.getLei04());
                pstm.setFloat(9, esg.getLei05());
                pstm.setFloat(10, esg.getLei06());
                pstm.setFloat(11, esg.getLei07());
                pstm.setFloat(12, esg.getLei08());
                pstm.setFloat(13, esg.getLei09());
                pstm.setFloat(13, esg.getLei10());
                pstm.setFloat(14, esg.getLei11());
                pstm.setFloat(15, esg.getLei12());
                pstm.setDate(16, esg.getData01());
                pstm.setDate(17, esg.getData02());
                pstm.setDate(18, esg.getData03());
                pstm.setDate(19, esg.getData04());
                pstm.setDate(20, esg.getData05());
                pstm.setDate(21, esg.getData06());
                pstm.setDate(22, esg.getData07());
                pstm.setDate(23, esg.getData08());
                pstm.setDate(24, esg.getData09());
                pstm.setDate(25, esg.getData10());
                pstm.setDate(26, esg.getData11());
                pstm.setDate(27, esg.getData12());
                pstm.setInt(1, esg.getIdCond());
                pstm.setInt(2, esg.getIdBloco());
                pstm.setInt(3, esg.getIdApart());
                pstm.setInt(4, esg.getAno());
                pstm.execute();
                JOptionPane.showMessageDialog(null, "esgoto alterado com sucesso");
                Conectar.fechaConexao(conn);

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao atualizar esgoto");
            }
        } else {
            JOptionPane.showMessageDialog(null, "O esgoto enviado por parâmetro está vazio");
        }
    }


    public Esgoto listar(Esgoto esgoto) {
        Connection conn;
        conn = null;
        PreparedStatement pstm;
        pstm = null;
        ResultSet rs;
        rs = null;
        Esgoto esg = new Esgoto();
        try {
            conn = Conectar.getConexao();
            pstm = conn.prepareStatement(LIST);
            pstm.setInt(1, esgoto.getIdCond());
            pstm.setInt(2, esgoto.getIdBloco());
            pstm.setInt(3, esgoto.getIdApart());
            pstm.setInt(4, esgoto.getAno());
            rs = pstm.executeQuery();
            while (rs.next()) {
                esg.setLei01(rs.getFloat("Lei01"));
                esg.setLei02(rs.getFloat("Lei02"));
                esg.setLei03(rs.getFloat("Lei03"));
                esg.setLei04(rs.getFloat("Lei04"));
                esg.setLei05(rs.getFloat("Lei05"));
                esg.setLei06(rs.getFloat("Lei06"));
                esg.setLei07(rs.getFloat("Lei07"));
                esg.setLei08(rs.getFloat("Lei08"));
                esg.setLei09(rs.getFloat("Lei09"));
                esg.setLei10(rs.getFloat("Lei10"));
                esg.setLei11(rs.getFloat("Lei11"));
                esg.setLei12(rs.getFloat("Lei12"));
            }
            Conectar.fechaConexao(conn, pstm, rs);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar clientes" + e.getMessage());
        }
        return esg;
    }
}
