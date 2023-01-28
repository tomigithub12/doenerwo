package com.example.doenerwo;

import com.example.doenerwo.repository.DoenerstandRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;


@Service
public class ExcelManager {
    @Autowired
    DoenerstandRepository repo;

//    public ExcelManager(){
//        super();
//    }

    private static List<DönerBude> ReadExcel(){
        List<DönerBude> standortliste = new ArrayList<DönerBude>();

        try
        {
            FileInputStream file = new FileInputStream(new File("C:\\Users\\leona\\Downloads\\DönerStandorte.xlsx"));

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
                    DönerBude temp = new DönerBude(row.getCell(0).getStringCellValue(), row.getCell(1).getStringCellValue(), row.getCell(2).getStringCellValue());
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

    public void SendToMongo(){
        List<DönerBude> standortliste = ReadExcel();
        repo.saveAll(standortliste);
        List<DönerBude> x = repo.findAll();
        System.out.println("read");
    }


}
