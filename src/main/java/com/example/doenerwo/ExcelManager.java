package com.example.doenerwo;

import com.example.doenerwo.repository.DoenerstandRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;


@Service
public class ExcelManager {
    @Autowired
    DoenerstandRepository repo;

//    public ExcelManager(){
//        super();
//    }

    private static List<DoenerBude> ReadExcel(){
        List<DoenerBude> standortliste = new ArrayList<DoenerBude>();

        try
        {
            FileInputStream file = new FileInputStream(new File("/Users/mihi/Downloads/DoenerStandorte2.xlsx"));

            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext())
            {


                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                if(row.getCell(0)!=null) {
                    DoenerBude temp = new DoenerBude(row.getCell(0).getStringCellValue(), row.getCell(1).getStringCellValue(), row.getCell(2).getStringCellValue(), row.getCell(3).getStringCellValue());
                    standortliste.add(temp);
                }
            }
            file.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return standortliste;
    }

    public List<DoenerBude> SendToMongo(){
        try {
            List<DoenerBude> standortliste = ReadExcel();
            //DoenerBude doenerBude = new DoenerBude("TestName", "testLong", "testLat");
            //String s = repo.findAll().toString();
            //repo.save(doenerBude);
            repo.saveAll(standortliste);
            List<DoenerBude> listOfInsertedAndFoundAll = repo.findAll();
            System.out.println("read");
            return listOfInsertedAndFoundAll;
        } catch (Exception e) {
            return null;
        }

    }


}
