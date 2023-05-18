/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacy;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;

import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author doha
 */
public class linkedList {

    node head = null;

    // Method 1 Load Path
    public void loadPath(String path) throws IOException, ParseException, java.text.ParseException {
        JSONParser jsonParser = new JSONParser();
        Object Obj = jsonParser.parse(new FileReader(path)); // ".\\.vscode\\medicine.json"
        JSONObject jsonObj = (JSONObject) Obj;

        JSONArray med = (JSONArray) jsonObj.get("med");
        int size = med.size();

        node temp = head;
        boolean flag = false;
        for (int j = 0; j < size; j++) {
            Medicine drug = new Medicine();

            JSONObject address = (JSONObject) med.get(j);
            drug.name = (String) address.get("name");
            drug.id = (Long) address.get("id");
            drug.price = (Long) address.get("price");
            drug.quantity = (Long) address.get("quantity");
            drug.type = (String) address.get("type");
            drug.Manufacture = (String) address.get("Manufacture");
            String date = (String) address.get("expDate");
            drug.expDate = new SimpleDateFormat("d/MM/yyyy").parse(date);
            for (int i = 0; i < date.length(); i++) {
                if (i == 2 || i == 3) {
                    drug.month += (date.charAt(i) - '0');
                } else if (i > 4) {
                    drug.year += (date.charAt(i) - '0');
                }
            }

            node n = new node(drug);
            if (flag == false) {
                head = n;
                temp = head;
                flag = true;
            } else if (flag == true) {
                temp.next = n;
                temp = temp.next;
            }
        }
    }
    // Method 2 Print All

    public void printAll() {
        node temp = head;
        while (temp != null) {
            System.out.println("Medicine name: " + temp.drug.name);
            System.out.println("Medicine id: " + temp.drug.id);
            System.out.println("Medicine quantity: " + temp.drug.quantity);
            System.out.println("Medicine price: " + temp.drug.price);
            System.out.println("Medicine type: " + temp.drug.type);
            System.out.println("Medicine manufacture date: " + temp.drug.Manufacture);
            System.out.println("Medicine expire date: " + temp.drug.expDate + "\n");
            System.out.println();
            temp = temp.next;
        }
    }

    // Method 3 Print Medicine Data
    public void printMedicineData(String mydrug) {
        node temp = head;
        while (temp != null && !mydrug.equals(temp.drug.name)) {
            temp = temp.next;
        }
        if (temp != null) {
            System.out.println("Medicine name: " + temp.drug.name);
            System.out.println("Medicine id: " + temp.drug.id);
            System.out.println("Medicine quantity: " + temp.drug.quantity);
            System.out.println("Medicine price: " + temp.drug.price);
            System.out.println("Medicine type: " + temp.drug.type);
            System.out.println("Medicine manufacture date: " + temp.drug.Manufacture);
            System.out.println("Medicine expire date: " + temp.drug.expDate + "\n");
        } else {
            System.out.println("This Medicine is not avaliable");
        }
    }

    //Method 4 Update
    public boolean update(Medicine drug) {
        if (head == null) {
            System.out.println("Nothing to update");
        } else if (head != null) {
            int count = 0;
            node temp = head;
            System.out.println("which is medicin you want to update? (Enter the no of medicine)");
            Scanner myObj = new Scanner(System.in);
            int loc = myObj.nextInt();
            while (loc != count) {
                temp = temp.next;
                count++;
            }
            if (loc == count) {
                System.out.println("Enter the new id");
                long id = myObj.nextLong();
                temp.drug.id = id;
                System.out.println("update id of medicine  " + (loc + 1) + " to " + id);
                System.out.println("Enter the new price");
                long price = myObj.nextLong();
                temp.drug.price = price;
                System.out.println("update price of medicine  " + (loc + 1) + " to " + price);
                System.out.println("Enter the new quantity");
                long quantity = myObj.nextLong();
                temp.drug.quantity = quantity;
                System.out.println("update quantity of medicine  " + (loc + 1) + " to " + quantity);
            }
            return true;
        } else {
            return false;
        }
        return false;

    }
    //Method 5 Delete

    public boolean DeleteMedicine(int loc) {
        if (head != null) {
            if (loc == 0) {
                head = head.next;
                return true;
            } else {
                node temp = head;
                for (int i = 0; i < loc - 1 && temp.next != null; i++) {
                    temp = temp.next;
                }
                if (temp.next != null) {
                    temp.next = temp.next.next;
                    return true;
                }
                return false;
            }
        }
        return false;

    }

    //Method 6 Add
    public boolean addNewMedicine() throws java.text.ParseException {
        Scanner myObj = new Scanner(System.in);
        Medicine Mydrug = new Medicine();
        System.out.println("Enter your new medicine name: ");
        Mydrug.name = myObj.nextLine();
        System.out.println("Enter your new medicine type: ");
        Mydrug.type = myObj.nextLine();
        System.out.println("Enter your new medicine manufacture: ");
        Mydrug.Manufacture = myObj.nextLine();
        System.out.println("Enter your new medicine expire date: ");
        String date = myObj.nextLine();
        Mydrug.expDate = new SimpleDateFormat("d/M/yyyy").parse(date);
        System.out.println("Enter your new medicine id: ");
        Mydrug.id = myObj.nextLong();
        System.out.println("Enter your new medicine price: ");
        Mydrug.price = myObj.nextLong();
        System.out.println("Enter your new medicine quantity: ");
        Mydrug.quantity = myObj.nextLong();

        boolean retval = false;
        node Node = new node(Mydrug);
        if (Node != null) {
            if (head == null) { //No List
                head = Node;
            } else { //there is a list
                node temp = head;
                while (temp.next != null) {
                    temp = temp.next;
                }
                temp.next = Node;
            }//end else 
            retval = true;
        }
        return retval;
    }

    //Method 7 Sort by Price
    public void sortListPrice(String order) {
        node current = head, index = null;
        Medicine temp;
        switch (order) {
            case "ascending":
            case "Ascending":

                if (head == null) {
                    return;
                } else {
                    while (current != null) {

                        index = current.next;

                        while (index != null) {

                            if (current.drug.price > index.drug.price) {
                                temp = current.drug;
                                current.drug = index.drug;
                                index.drug = temp;
                            }

                            index = index.next;
                        }
                        current = current.next;
                    }
                }
                break;

            case "descending":
            case "Descending":
                if (head == null) {
                    return;
                } else {
                    while (current != null) {

                        index = current.next;
                        while (index != null) {
                            if (current.drug.price < index.drug.price) {
                                temp = current.drug;
                                current.drug = index.drug;
                                index.drug = temp;
                            }
                            index = index.next;
                        }
                        current = current.next;
                    }
                }
                break;

            default:
                System.out.println("You entered wrog order! Kindly choose (ascending - descending)");
        }
    }

    //Method 8 Sort by Quantity
    public void sortListQuantity(String order) {
        node current = head, index = null;
        Medicine temp;
        switch (order) {
            case "ascending":
            case "Ascending":

                if (head == null) {
                    return;
                } else {
                    while (current != null) {

                        index = current.next;

                        while (index != null) {

                            if (current.drug.quantity > index.drug.quantity) {
                                temp = current.drug;
                                current.drug = index.drug;
                                index.drug = temp;
                            }

                            index = index.next;
                        }
                        current = current.next;
                    }
                }
                break;

            case "descending":
            case "Descending":
                if (head == null) {
                    return;
                } else {
                    while (current != null) {

                        index = current.next;
                        while (index != null) {
                            if (current.drug.quantity < index.drug.quantity) {
                                temp = current.drug;
                                current.drug = index.drug;
                                index.drug = temp;
                            }
                            index = index.next;
                        }
                        current = current.next;
                    }
                }
                break;

            default:
                System.out.println("You entered wrong order! Kindly choose (ascending - descending)");
        }
    }

    //Method 9 Sort by Expire date
    public void sortListExpDate(String order) {
        node current = head, index = null;
        Medicine temp;
        switch (order) {
            case "ascending":
            case "Ascending":

                if (head == null) {
                    return;
                } else {
                    while (current != null) {

                        index = current.next;

                        while (index != null) {

                            if (current.drug.expDate.after(index.drug.expDate)) {
                                temp = current.drug;
                                current.drug = index.drug;
                                index.drug = temp;
                            }

                            index = index.next;
                        }
                        current = current.next;
                    }
                }
                break;

            case "descending":
            case "Descending":
                if (head == null) {
                    return;
                } else {
                    while (current != null) {

                        index = current.next;
                        while (index != null) {
                            if (current.drug.expDate.before(index.drug.expDate)) {
                                temp = current.drug;
                                current.drug = index.drug;
                                index.drug = temp;
                            }
                            index = index.next;
                        }
                        current = current.next;
                    }
                }
                break;

            default:
                System.out.println("You entered wrog order! Kindly choose (ascending - descending)");
        }
    }

    // Method 10 Expire At Date
    public void expireAtDate(String m, String y) {
        node temp = head;
        while (temp != null) {
            if ((temp.drug.year.equals(y)) && (temp.drug.month.equals(m))) {
                System.out.println("Medicin Name: " + temp.drug.name);
            }

            temp = temp.next;
        }
    }

    // Method 11 Save Data
    public void SaveData(String path) throws IOException {
        FileWriter write = new FileWriter("C:\\Users\\Sha\\Desktop\\second year\\drugs.json");
        PrintWriter print_line = new PrintWriter(write);
        print_line.println("{ \n \"med\": \n[");
        node temp = head;
        while (temp != null) {
            Medicine m1 = temp.drug;
            String date1 = new SimpleDateFormat("d/MM/yyyy").format(m1.expDate);
            if (temp.next == null) {
                String line = "{ \"name\": " + "\"" + m1.name + "\"" + ", " + "\"id\": " + m1.id + ", " + "\"quantity\": " + m1.quantity + ", " + "\"price\": " + m1.price + ", " + "\"type\": " + "\"" + m1.type + "\"" + ", " + "\"Manufacture\": " + "\"" + m1.Manufacture + "\"" + ", " + "\"expDate\": " + "\"" + date1 + "\"}";
                print_line.println(line);
                break;
            }
            String line = "{ \"name\": " + "\"" + m1.name + "\"" + ", " + "\"id\": " + m1.id + ", " + "\"quantity\": " + m1.quantity + ", " + "\"price\": " + m1.price + ", " + "\"type\": " + "\"" + m1.type + "\"" + ", " + "\"Manufacture\": " + "\"" + m1.Manufacture + "\"" + ", " + "\"expDate\": " + "\"" + date1 + "\"},";
            print_line.println(line);
            temp = temp.next;
        }
        print_line.println("]\n}");
        print_line.close();
    }

    // Method 12 Sell Medicin
    public void SellMedicine(String medName, int quantity) {
        node temp = head;
        while ((!temp.drug.name.equals(medName)) && (temp != null)) {
            temp = temp.next;
        }
        if (temp != null) {
            temp.drug.quantity -= quantity;
            Long price = temp.drug.price * quantity;
            System.out.println("the Medicine price is " + price);

        } else {
            System.out.println("Medicin not found!");
        }
    }

    // Method 13 Exit
    public void exit() throws IOException {

        System.out.println("All of things are Done ");
        SaveData("C:\\Users\\Sha\\Desktop\\second year\\drugs.json");
        System.exit(0);
    }
}
