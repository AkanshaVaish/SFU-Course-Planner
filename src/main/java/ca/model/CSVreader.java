/**
 * CSVreader class reads the csv file whose address is passed in while instantiating object of this class.
 * Whole system of course information is generated with the help of departmentBlock class.
 * getResult functions returns the string which is meant to be the whole output.
 * @author Raghav Mittal & Akansha Vaish
 */

package ca.model;

import ca.model.blocks.DepartmentBlock;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVreader {
    public final String separatedBy = ",";

    private String filePath;
    private BufferedReader readerFromCSV = null;

    //HashMap struct to store information
    private DepartmentBlock departmentBlock = new DepartmentBlock();

    public CSVreader (String filePath) {
        this.filePath = filePath;
        readLineByLine (filePath);
    }

    private void readLineByLine(String filePath) {
        String line;
        try {
            readerFromCSV = new BufferedReader (new FileReader(filePath));

            readerFromCSV.readLine();
            while ((line = readerFromCSV.readLine()) != null) {
                String[] csvLine = line.split (separatedBy);
                FancyTrimObject (csvLine);

                departmentBlock.add (csvLine);
            }

            readerFromCSV.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    //Meaning of word Fancy Trim is that it trims all the strings and also does additional work.
    //That is, it removes all the unwanted characters from the strings read such as ".
    private void FancyTrimObject(String[] object) {
        for (int i = 0; i < object.length; i++) {
            object[i] = object[i].replace("\"", "").trim();
        }
    }

    public String getResult() {
        return "" + departmentBlock;
    }

    public DepartmentBlock getDepartmentBlock() {
        return departmentBlock;
    }

    public void addDynamically (String csvLine) {
        String[] thisCSVLine = csvLine.split (separatedBy);

        FancyTrimObject (thisCSVLine);

//        for (String s : thisCSVLine) {
//            System.out.println(s);
//        }

        departmentBlock.add (thisCSVLine);
    }
}
