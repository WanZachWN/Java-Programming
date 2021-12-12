/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.pkg1;
import javax.swing.JOptionPane;
/**
 *
 * @author User
 */
public class Assignment1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        double payrate, hoursworked;
        final double WEEKS_WORKED = 8;
        String payrateinput = JOptionPane.showInputDialog("Enter the pay rate for an hour:");
        payrate = Double.parseDouble(payrateinput);
        String hoursworkedinput = JOptionPane.showInputDialog("Enter the numbers of hours you worked each week:");
        hoursworked = Double.parseDouble(hoursworkedinput);
        hoursworked = hoursworked*WEEKS_WORKED;
        
        displaySavings(hoursworked);
        
    }
    public static void displaySavings(double hoursWorked)
    {

        final double TAX = 0.06;
        final double TEXT_BOOK = 0.1;
        final double STATIONARIES = 0.03;
        final double SAVINGS_IN_BANK = 0.2;
        
        System.out.println("Income before taxes: " + hoursWorked);
        hoursWorked = hoursWorked-(hoursWorked * TAX);
        
        System.out.println("Income after taxes: " + hoursWorked);
        System.out.println("Money Spend on Text Book: " + hoursWorked * TEXT_BOOK);
        System.out.println("Money spend on Stationaries: " + hoursWorked * STATIONARIES);
        hoursWorked = hoursWorked-(hoursWorked*(TEXT_BOOK+STATIONARIES));
        
        double bankSavings = hoursWorked * SAVINGS_IN_BANK;
        System.out.println("Money saved in Bank: " + bankSavings);
        Integer bankSavingsInt = (int) bankSavings;        
        System.out.println("Parents spend to add in bank: " + bankSavingsInt);
    }
    
}
