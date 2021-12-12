/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package question.pkg10;
import java.util.Scanner;
import javax.swing.JOptionPane;
/**
 *
 * @author Zack
 */
public class CollegeList {
    public static void main(String[] args)
    {
        Scanner keyboard = new Scanner(System.in);
        CollegeEmployee[] arr1 = new CollegeEmployee[4];
        for(int i = 0; i < 4; i++)
        {
            arr1[i] = new CollegeEmployee();
        }
        
        Faculty[] arr2 = new Faculty[3];
        for(int k = 0; k < 3; k++)
        {
            arr2[k] = new Faculty();
        }
        
        Student[] arr3 = new Student[7];
        for(int j = 0; j < 7; j++)
        {
            arr3[j] = new Student();
        }
        
        boolean quit = false;
        int countC = 0;
        int countS = 0;
        int countF = 0;
        int count = 0;
        
        while(quit != true)
        {
            System.out.println("C for Collehe Employees");
            System.out.println("F for Faculty");
            System.out.println("S for Student");
            System.out.println("Q to Quit");
            String item = keyboard.nextLine();
            
            if(item.equalsIgnoreCase("C"))
            {
                
                if(countC > 4)
                {
                    JOptionPane.showMessageDialog(null,"Error Container is full");
                   
                }
                else
                {
                    arr1[countC].setfName();
                    arr1[countC].setlName();
                    arr1[countC].setAdd();
                    arr1[countC].setSsNum();
                    arr1[countC].setAnnual();
                    arr1[countC].setDep();
                    ++countC;
                }
                
            }
            else if(item.equalsIgnoreCase("F"))
            {
                
                if(countF > 3)
                {
                    JOptionPane.showMessageDialog(null,"Error Container is full");
                   
                }
                else
                {
                    arr2[countF].setfName();
                    arr2[countF].setlName();
                    arr2[countF].setAdd();
                    arr2[countF].setSsNum();
                    arr2[countF].setAnnual();
                    arr2[countF].setDep();
                    arr2[countF].setTenured();
                    ++countF;
                }
                
            }
            else if(item.equalsIgnoreCase("S"))
            {
                
                if(countS > 7)
                {
                    JOptionPane.showMessageDialog(null,"Error Container is full");
                    
                }
                else
                {
                    arr3[countS].setfName();
                    arr3[countS].setlName();
                    arr3[countS].setAdd();
                    arr3[countS].setMajor();
                    arr3[countS].setGpa();
                    ++countS;
                }
                
            }
            else if(item.equalsIgnoreCase("Q"))
            {
                while(count != countC)
                {
                    arr1[count].display();
                    System.out.println();
                    ++count;
                }
                count = 0;
                while(count != countF)
                {
                    arr2[count].display();
                    System.out.println();
                    ++count;
                }
                
                count = 0;
                while(count != countS)
                {
                    arr3[count].display();
                    System.out.println();
                    ++count;
                }
                return;
            }
            else
            {
                JOptionPane.showInputDialog("Error Wrong Code Entry");
            }
            
        }
        
    }
}
