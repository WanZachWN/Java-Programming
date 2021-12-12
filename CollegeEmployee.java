/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package question.pkg10;

import javax.swing.JOptionPane;

/**
 *
 * @author Zack
 */
public class CollegeEmployee extends Person {

    protected String ssNum;
    protected double annual;
    protected String dep;

    public void setSsNum() {
        this.ssNum = JOptionPane.showInputDialog("Enter Social Security Number");
    }

    public void setAnnual() {
        String getAnnual = JOptionPane.showInputDialog("Enter Annual Salary");
        this.annual = Double.parseDouble(getAnnual);
    }

    public void setDep() {
        this.dep = JOptionPane.showInputDialog("Enter Department Name");
    }
    
    
    
    public void display(){
        super.display(); 
        System.out.println("Social security Number: " + ssNum);
        System.out.println("Annual Salary: " + annual);
        System.out.println("Department: " + dep);        
    }
}
