/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

/**
 *
 * @author Bruno
 */
public class Relatorio {


    public Relatorio() {
    }

    public void Criar(ArrayList<Rateio> rateios, int cond) {

        
        int mes = Integer.parseInt(rateios.get(0).getReferencia().substring(0, 2));
        int ano = Integer.parseInt(rateios.get(0).getReferencia().substring(3, 7));
        
        try {
            // local do arquivo
            String filename = "C:/Relatorio/" + "Relatorio_Condominio_" + rateios.get(0).getIdCond() + "_" + mes + "_" + ano + ".xls";
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("FirstSheet");
            HSSFCellStyle estiloCor = workbook.createCellStyle();
            estiloCor.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
            HSSFRow rowhead = sheet.createRow((short) 0);
            estiloCor.setFillBackgroundColor(HSSFColor.GREY_40_PERCENT.index);

            // criando as linhas
            
            rowhead.createCell(0).setCellValue("APARTAMENTO");
            rowhead.createCell(1).setCellValue("BLOCO");
            rowhead.createCell(2).setCellValue("VALOR");
            
            rowhead.createCell(5).setCellValue("CONDOMÍNIO: " + rateios.get(0).getIdCond());
            rowhead.createCell(7).setCellValue("REFERÊNCIA: " + rateios.get(0).getReferencia());
           
            

            sheet = workbook.getSheetAt(0);
            // definindo seus valores
            // por exemplo protocolo.getProtocolo();

            int j = 0;
            
            for (int i = 0; i < rateios.size(); i++) {
                
                HSSFRow row = sheet.createRow((short) i + 1);
                
                
                row.createCell(0).setCellValue(rateios.get(i).getIdApart());
                row.createCell(1).setCellValue(rateios.get(i).getIdBloco());
                row.createCell(2).setCellValue(rateios.get(i).getValor());

            }

            FileOutputStream fileOut = new FileOutputStream(filename, false);

            workbook.write(fileOut);

            fileOut.close();
            JOptionPane.showMessageDialog(null, "Relatório gerado com sucesso.");

        } catch (Exception ex) {
            System.out.println(ex);

        }
    }
}
