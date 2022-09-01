/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// useage of this keyword
// calling parameterized consructor from a default constructor


package practicepurpose;

class student
{
    int id; String name, course; float fee;
    
    student()
    {
        this(111, "pranta", 5000f); // calling parameterized constructor from a default constructor
        System.out.println("student constructor");
    }
    
    student(int id, String name, float fee)
    {
        //this(); // it would be recursive if you execute the statement
        this.id = id; this.name = name; this.fee = fee;
    }
    
    void display()
    {
        System.out.println(id + " " + name + " " + fee);
    }
}

/**
 *
 * @author FC
 */
public class PracticePurpose {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        student ob = new student();
        ob.display();
    }
    
}
