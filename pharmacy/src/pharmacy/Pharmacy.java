/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacy;

import java.util.*;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

/**
 *
 * @author doha
 */
public class Pharmacy {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ParseException, java.text.ParseException {

        linkedList l = new linkedList();
        l.loadPath("C:\\Users\\Sha\\Desktop\\second year\\drugs.json");
        l.printAll();
        l.printMedicineData("panadol");
       
        //l.addNewMedicine();
        Scanner sc=new Scanner(System.in);
       //System.out.println("Enter the loc of the medicine you want to delete:");
       // int n = sc.nextInt();
       // System.out.println(l.DeleteMedicine(n));
//        l.sortListPrice("ascending");
//        l.sortListQuantity("descending");
//        l.sortListExpDate("ascending");
//        Medicine drug = new Medicine();
        //l.update(drug);
        l.expireAtDate("01","2023");
        //l.printAll();
//        l.SellMedicine("panadol", 5);
//        l.SaveData("C:\\Users\\Sha\\Desktop\\second year\\drugs.json");
//        l.exit();
        
       
        

    }
}
        
