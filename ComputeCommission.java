/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computecommission;

/**
 *
 * @author User
 */
public class ComputeCommission {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        char vType = 'S';
        int value = 23000;
        double comRate = 0.08;
        computeCommission(value, comRate, vType);
        computeCommission(40000, 0.10, 'L');
    }
    public static void computeCommission(int value, double rate, char vehicle)
    {
        double commission;
        commission = value*rate;
        System.out.println("\nThe " + vehicle + " type vehicle is worth $" + value);
        System.out.println("With "+ (rate*100) + "% commission rate, the commission is $" + commission);
    }
    
}
