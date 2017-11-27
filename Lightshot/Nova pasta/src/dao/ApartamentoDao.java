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
import model.Bloco;

/**
 *
 * @author Lucas_Reinert
 */
public class ApartamentoDao {
     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private final String INSERT = "INSERT INTO tbapartamento(tbBloco_tbCondominio_idCondominio, tbBloco_idBloco, idApart, "
            + "nomeProprietario, telefoneProprietario, emailProprietario, logradouroProprietario, enderecoProprietario, bairroProprietario,"
            + "numeroProprietario, complementoProprietario, nomeMorador, telefoneMorador, emailMorador, coeficienteApartamento)"
            + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private final String UPDATE = "UPDATE tbApartamento SET "
            + "nomeProprietario = ?,"
            + "telefoneProprietario = ?,"
            + "emailProprietario = ?,"
            + "logradouroProprietario = ?,"
            + "enderecoProprietario = ?,"
            + "bairroProprietario = ? ,"            
            + "numeroProprietario = ?,"
            + "complementoProprietario = ?,"
            + "nomeMorador = ?,"
            + "telefoneMorador = ?,"
            + "emailMorador = ?,"
            + "coeficienteApartamento = ? "
            + "WHERE (tbBloco_tbCondominio_idCondominio = ?) and (tbBloco_idBloco = ?) and (idApart = ?) ";
    private final String LIST = "SELECT * FROM tbApartamento where (tbBloco_tbCondominio_idCondominio = ?)";
    private final String LISTCAD = "SELECT * FROM tbApartamento";
    private final String DELETE = "DELETE FROM tbApartamento WHERE (tbBloco_tbCondominio_idCondominio = ?) and (tbBloco_idBloco = ?) and (idApart = ?)";
    private final String FindKey = "SELECT * from tbApartamento WHERE (tbBloco_tbCondominio_idCondominio = ?) and (tbBloco_idBloco = ?) and (idApart = ?) ";

    public void adicionar(Apartamento apa) {

        if (apa != null) {
            Connection conn;
            conn = null;
            try {
                conn = Conectar.getConexao();
                PreparedStatement pstm;
                pstm = conn.prepareStatement(INSERT);
                pstm.setInt(1, apa.getIdCond());
                pstm.setInt(2, apa.getIdBloco());
                pstm.setInt(3, apa.getIdApart());
                pstm.setString(4, apa.getNomeProprietario());
                pstm.setString( 5, apa.getTelefoneProprietario());
                pstm.setString(6, apa.getEmailProprietario());
                pstm.setString(7, apa.getLogradouroProprietario());
                pstm.setString(8, apa.getEnderecoProprietario());                
                pstm.setString(9, apa.getBairroProprietario());
                pstm.setString(10, apa.getNumeroProprietario());
                pstm.setString(11, apa.getComplementoProprietario());
                pstm.setString(12, apa.getNomeMorador());
                pstm.setString(13, apa.getTelefoneMorador());
                pstm.setString(14, apa.getEmailMorador());
                pstm.setFloat(15, apa.getCoeficienteApartamento());
                pstm.execute();
                
                JOptionPane.showMessageDialog(null, "Apartamento cadastrado com sucesso");
                Conectar.fechaConexao(conn, pstm);

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao inserir apartamento no banco de"
                        + "dados " + e.getMessage());
            }
        } else {
            System.out.println("O apartamento enviado por parâmetro está vazio");
        }
    }

    public void atualizar(Apartamento apa) {
        if (apa != null) {
            Connection conn;
            conn = null;
            try {
                conn = Conectar.getConexao();
                PreparedStatement pstm;
                pstm = conn.prepareStatement(UPDATE);
                
                pstm.setString(1, apa.getNomeProprietario());
                pstm.setString( 2, apa.getTelefoneProprietario());
                pstm.setString(3, apa.getEmailProprietario());
                pstm.setString(4, apa.getLogradouroProprietario());
                pstm.setString(5, apa.getEnderecoProprietario());                
                pstm.setString(6, apa.getBairroProprietario());
                pstm.setString(7, apa.getNumeroProprietario());
                pstm.setString(8, apa.getComplementoProprietario());
                pstm.setString(9, apa.getNomeMorador());
                pstm.setString(10, apa.getTelefoneMorador());
                pstm.setString(11, apa.getEmailMorador());
                pstm.setFloat(12, apa.getCoeficienteApartamento());
                pstm.setInt(13, apa.getIdCond());
                pstm.setInt(14, apa.getIdBloco());
                pstm.setInt(15, apa.getIdApart());                
                pstm.execute();
                JOptionPane.showMessageDialog(null, "Apartamento alterado com sucesso");
                Conectar.fechaConexao(conn);

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao atualizar o " + apa.getIdApart()+ e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "O apartamento enviado por parâmetro está vazio");
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

    public List<Apartamento> listar(int codCond) {
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
            pstm.setInt(1, codCond);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Apartamento a = new Apartamento();
                a.setIdCond(rs.getInt("tbBloco_tbCondominio_idCondominio"));
                a.setIdBloco(rs.getInt("tbBloco_idBloco"));
                a.setIdApart(rs.getInt("idApart"));
                a.setCoeficienteApartamento(rs.getFloat(("coeficienteApartamento")));
                apartamentos.add(a);
            }
            Conectar.fechaConexao(conn, pstm, rs);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar clientes" + e.getMessage());
        }
        return apartamentos;
    }
    public ArrayList<Apartamento> listarCad() {
        Connection conn;
        conn = null;
        PreparedStatement pstm;
        pstm = null;
        ResultSet rs;
        rs = null;
        ArrayList<Apartamento> apartamentos = new ArrayList<>();
        try {
            conn = Conectar.getConexao();
            pstm = conn.prepareStatement(LISTCAD);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Apartamento a = new Apartamento();
                a.setIdCond(rs.getInt("tbBloco_tbCondominio_idCondominio"));
                a.setIdBloco(rs.getInt("tbBloco_idBloco"));
                a.setIdApart(rs.getInt("idApart"));
                a.setCoeficienteApartamento(rs.getInt("coeficienteApartamento"));
                apartamentos.add(a);
            }
            Conectar.fechaConexao(conn, pstm, rs);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar apartamento" + e.getMessage());
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
