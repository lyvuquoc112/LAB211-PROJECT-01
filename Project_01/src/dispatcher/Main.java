/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dispatcher;

import business.Customers;
import business.SetMenus;
import java.util.HashSet;
import java.util.Scanner;
import model.Customer;
import tool.Inputter;

/**
 *
 * @author hanly
 */
public class Main {
    public static void main(String[] args) {
        Inputter inputter = new Inputter();
        Customers customers = new Customers();
        SetMenus setMenus = new SetMenus("D:\\FPT\\Ky 3\\LAB211\\SE1806-LAB211-main\\Set14_SU25\\De_LAB211\\01_J1.L.P0028.TraditionalFeastOrderManagement_300LOC\\FeastMenu.csv");

        Scanner sc = new Scanner(System.in);
        int choice = 0, option = 0;
        do {
            System.out.println("\n----------MAIN MENU------------");
            System.out.println("1. Register customers");
            System.out.println("2. Update customer information");
            System.out.println("3. Seach for customer information by name");
            System.out.println("4. Display feast menu");
            System.out.println("5. Place a feast order");
            System.out.println("6. Update a feast order");
            System.out.println("7. --------------------");
            System.out.println("8. Display customers or order list");
            System.out.println("9. Exit");
            System.out.print("Enter Test Case No. : ");
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    do {                        
                        customers.addNew(inputter.inputCustomer(null,false));
                        System.out.println("1. Continue entering new customers");
                        System.out.println("2. Return to the main menu");
                        option = Integer.parseInt(inputter.input("Chose your option", "Option must be 1 or 2", "^[12]$"));
                    } while (option == 1);
                    break;
                case 2:
                    do {                        
                        System.out.println("Enter customer code");
                        String customerCode = sc.nextLine();
                        Customer customer = customers.searchById(customerCode);
                        if(customer == null){
                            System.out.println("This customer does not exist");
                        }else{
                            Customer c = inputter.inputCustomer(customer, true);
                            customers.upDate(c);
                        }
                        System.out.println("1. Continue update customers");
                        System.out.println("2. Return to the main menu");
                        option = Integer.parseInt(inputter.input("Chose your option", "Option must be 1 or 2", "^[12]$"));

                    } while (option == 1);
                    break;
                case 3:
                                          
                        System.out.println("Enter name: ");
                        String name = sc.nextLine();
                        HashSet<Customer> result = customers.filteredByName(name);
                        if(result.isEmpty()){
                            System.out.println("No one matches the search criteria!");
                        }else{
                            customers.showFilteredByName(result);
                        }
                    break;
                case 4:
                    try {
                    setMenus.readFormFile();
                } catch (Exception e) {
                }
                    setMenus.showAll();
                    break;
                case 9:
                    System.out.println("Thank you for using our company service");
                    break;
                default:
                    throw new AssertionError();
            }
        }while(choice!=9);
    }
}
