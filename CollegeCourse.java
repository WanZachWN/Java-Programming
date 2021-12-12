/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package question.pkg8;

/**
 *
 * @author Zack
 */
public class CollegeCourse {
    protected String department;
    protected int courseNumber;
    protected int credits;
    protected double fee;
    private final double PRICE_PER_CREDIT_HOUR = 120;

    public CollegeCourse(String department, int courseNumber, int credits) {
        this.department = department;
        this.courseNumber = courseNumber;
        this.credits = credits;
        this.fee = credits*PRICE_PER_CREDIT_HOUR;
    }
    
    
    public void display()
    {
        System.out.println("Department:     " + department);
        System.out.println("Course Number:  " + courseNumber);
        System.out.println("Credits:        " + credits);
        System.out.println("Course Fee:     " + fee);
    }
}
