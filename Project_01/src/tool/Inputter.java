/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tool;

import java.util.Scanner;
import model.Customer;

/**
 *
 * @author hanly
 */
public class Inputter {
    private Scanner sc;
    public Inputter(){
        sc = new Scanner(System.in);
    }
    
    public String getString(String mess){
        System.out.println(mess);
        return sc.nextLine();
    }
    public int getInt(String mess){
        String input = getString(mess);
        int result = 0;
        try {
            result = Integer.parseInt(input);
        } catch (Exception e) {
        }
        return result;
    }
    public double getDouble(String mess){
        String input = getString(mess);
        double result = 0;
        try {
            result = Double.parseDouble(input);
        } catch (Exception e) {
        }
        return result;
    }
    public String input(String mess, String errorMess, String regex){
        String input;
        boolean more = false;
        do {            
            input = getString(mess);
            more = Validator.isValid(input, regex);
            if(!more){
                System.out.println(errorMess+ " Please Re-enter.");
            }
        } while (!more);
        return input;
    }
    
    public Customer inputCustomer(Customer oldCustomer, boolean isUpdate) {
        Customer customer = new Customer();

        // Customer code
        
            String mess = "Input Customer Code (the first character is [C,G,K], followed by 4 digits): ";
            String errorMess = "Customer code cannot be empty! Customer code must start with C, G, K, followed by 4 digits!";
            String regex = Validator.CUSTOMER_CODE_REGEX;
        if (!isUpdate) {
            customer.setCustomerCode(input(mess, errorMess, regex).toUpperCase());
        }

        // Name
        mess = isUpdate ? "Input name [" + oldCustomer.getName() + "]: " : "Input name: ";
        String name = getString(mess);
        if (isUpdate && name.isEmpty()) {
            customer.setName(oldCustomer.getName());
        } else if (Validator.isValid(name, Validator.NAME_REGEX)) {
            customer.setName(name);
        } else {
            System.out.println("Name must be between 2 and 25 characters. Please re-enter.");
            return inputCustomer(oldCustomer, isUpdate);
        }

        // Phone
        mess = isUpdate ? "Input phone [" + oldCustomer.getPhoneNumber() + "]: " : "Input phone: ";
        String phone = getString(mess);
        if (isUpdate && phone.isEmpty()) {
            customer.setPhoneNumber(oldCustomer.getPhoneNumber());
        } else if (Validator.isValid(phone, Validator.PHONE_NUMBER_REGEX)) {
            customer.setPhoneNumber(phone);
        } else {
            System.out.println("Invalid phone format! Please re-enter.");
            return inputCustomer(oldCustomer, isUpdate);
        }

        // Email
        mess = isUpdate ? "Input email [" + oldCustomer.getEmail() + "]: " : "Input email: ";
        String email = getString(mess);
        if (isUpdate && email.isEmpty()) {
            customer.setEmail(oldCustomer.getEmail());
        } else if (Validator.isValid(email, Validator.EMAIL_REGEX)) {
            customer.setEmail(email);
        } else {
            System.out.println("Invalid email format! Please re-enter.");
            return inputCustomer(oldCustomer, isUpdate);
        }

        return customer;
    }
}
