package utils;

import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ExcelUtils {
	
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;
	
	public ExcelUtils(String excelPath, String sheetName) {
		try{
		
		workbook = new XSSFWorkbook(excelPath);
		sheet = workbook.getSheet(sheetName);
		
	
		}catch(Exception exp) {
			 System.out.println(exp.getCause());
			 System.out.println(exp.getMessage());
			 exp.printStackTrace();
		 }
		
	}
	
	private static final String fileName = "./data/TestSheet.xlsx";

	public static void main(String[] args) throws IOException {

        List<aluno> listaAlunos = new ArrayList<aluno>();
        

        try {
               FileInputStream testsheet = new FileInputStream(new File(ExcelUtils.fileName));

               XSSFWorkbook workbook = new XSSFWorkbook(testsheet);

               XSSFSheet sheetAlunos = workbook.getSheetAt(0);

               Iterator<Row> rowIterator = sheetAlunos.iterator();
               rowIterator.next();
               
               while (rowIterator.hasNext()) {
                      Row row = rowIterator.next();
                      Iterator<Cell> cellIterator = row.cellIterator();

                      aluno aluno = new aluno();
                      listaAlunos.add(aluno);
                      
                      while (cellIterator.hasNext()) {
                             Cell cell = cellIterator.next();
                             switch (cell.getColumnIndex()) {
                             case 0:
                                   aluno.setNome(cell.getStringCellValue());
                                   break;
                             case 1:
                                   aluno.setRa(String.valueOf(cell.getNumericCellValue()));
                                   break;
                             case 2:
                                   aluno.setNota1(cell.getNumericCellValue());
                                   break;
                             case 3:
                                   aluno.setNota2(cell.getNumericCellValue());
                                   break;
                             case 4:
                                    aluno.setMedia(cell.getNumericCellValue());
                                   break;
                             case 5:
                                   aluno.setAprovado(cell.getBooleanCellValue());
                                   break;
                             }
                      }
               }
               testsheet.close();

        } catch (FileNotFoundException e) {
               e.printStackTrace();
               System.out.println("Arquivo Excel não encontrado!");
        }

        if (listaAlunos.size() == 0) {
               System.out.println("Nenhum aluno encontrado!");
        } else {
               double soma = 0;
               double maior = 0;
               double menor = listaAlunos.get(0).getMedia();
               int aprovados = 0;
               int reprovados = 0;
               for (aluno aluno : listaAlunos) {
                      System.out.println("Aluno: " + aluno.getNome() + " Média: "
                                   + aluno.getMedia());
                      soma = soma + aluno.getMedia();
                      if (aluno.getMedia() > maior) {
                             maior = aluno.getMedia();
                      }
                      if (aluno.getMedia() < menor) {
                             menor = aluno.getMedia();
                      }
                      if (aluno.getMedia() >= 6) {
                             aprovados++;
                      }
                      if (aluno.getMedia() < 6) {
                             reprovados++;
                      }
               }
               double media = soma / listaAlunos.size();
               System.out.println("A media de notas e: " + media);
               System.out.println("A maior nota e: " + maior);
               System.out.println("A menor nota e: " + menor);
               System.out.println("O numero de alunos aprovados e: " + aprovados);
               System.out
                             .println("O numero de alunos reprovados e: " + reprovados);
               System.out.println("Número total de alunos: " + listaAlunos.size());
        }

  }

	public static String getFilename() {
		return fileName;
	}
}