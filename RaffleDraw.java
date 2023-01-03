/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raffledraw;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import java.util.concurrent.ThreadLocalRandom;

class window{
    JFrame frame;
    JButton b;
    void makeFrame(String frameName, boolean vis, boolean resize){
        frame = new JFrame(frameName);
        frame.setSize(400, 400);
        frame.setVisible(vis);
        frame.setResizable(resize);
        frame.setLayout(null);
    }
    void makeButton(String buttonName, int x, int y){
        b = new JButton(buttonName);
        b.setBounds(x, y, 150, 50);
    }
}

class backButton implements ActionListener{
    JFrame f1, f2;
    backButton(JFrame f1, JFrame f2, JButton b) {
        this.f1 = f1; // current page's frame object
        this.f2 = f2; // previous page's frame object
        b.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e){
        f1.setVisible(false); // current frame is invisible
        f2.setVisible(true); // last frame is visible
    }
}

class vectors{
    static Vector<Vector> rowData = new Vector<Vector>();
}

class submitButtonPerform implements ActionListener{
    JTextField nameText, phoneText;
    submitButtonPerform(JTextField nameText, JTextField phoneText, JButton b){
        this.nameText = nameText;
        this.phoneText = phoneText;
        b.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e){
        String name = nameText.getText();
        String phone = phoneText.getText();
        
        JFrame msg = new JFrame();
        
        if(name.length() == 0 || phone.length() == 0){
            JOptionPane.showMessageDialog(msg, "Missing Field(s)", "Alert", JOptionPane.WARNING_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(msg, "Thank you " + name + ". You are successfully addedd.");
            
            Vector<String> rowOne = new Vector<String>(); // declared 1D Vector
            
            rowOne.addElement(name); // inserting the row information of the table, i.e. the name
            rowOne.addElement(phone); // inserting the row information of the table, i.e. the phone number
            vectors.rowData.addElement(rowOne); // finally, inserting the row information into a 2D static/global vector
            
            System.out.println(vectors.rowData); // printing the 2D vectors information

            nameText.setText("");
            phoneText.setText("");
        }
    }
}

class registrationButtonPerform implements ActionListener{
    JFrame f;
    registrationButtonPerform(JFrame f, JButton b){
        this.f = f; // assigned the frame's object of "Landing Page" in the current frame object
        b.addActionListener(this); // passed object of "Registration" button
    }
    public void actionPerformed(ActionEvent e){
        f.setVisible(false); // "Landing Page" is disappeared
        window registrationPage = new window();
        registrationPage.makeFrame("Registration Page", true, false); // made the frame of Registration Page
        registrationPage.makeButton("<<", 10, 10); // back button created
        backButton bb = new backButton(registrationPage.frame, f, registrationPage.b); // back button is performing
        registrationPage.frame.add(registrationPage.b);
        
        JLabel nameLabel = new JLabel("Username: ");
        nameLabel.setBounds(100, 100, 100, 50);
        
        JLabel phoneLabel = new JLabel("Phone Number: ");
        phoneLabel.setBounds(80, 150, 100, 50);
        
        JTextField nameText = new JTextField();
        nameText.setBounds(170, 110, 180, 30);
        
        JTextField phoneText = new JTextField();
        phoneText.setBounds(172, 160, 180, 30);
        
        window sButton = new window();
        sButton.makeButton("Submit", 172, 250); // created the Submit button with different window object
        submitButtonPerform sbp = new submitButtonPerform(nameText, phoneText, sButton.b);
        
        registrationPage.frame.add(sButton.b);
        
        registrationPage.frame.add(nameLabel);
        registrationPage.frame.add(phoneLabel);
        registrationPage.frame.add(nameText);
        registrationPage.frame.add(phoneText);
        registrationPage.frame.setDefaultCloseOperation(registrationPage.frame.EXIT_ON_CLOSE);
    }
}

class machineButtonPerform implements ActionListener{
    machineButtonPerform(JButton b){
        b.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e){
        window machineFrame = new window();
        machineFrame.makeFrame("The Machine", true, false);
        
        int min = 0, max = vectors.rowData.size();
        
        if(max == 0){
            JLabel l = new JLabel("No Records");
            l.setBounds(150, 80, 200, 100);
            machineFrame.frame.add(l);
        }
        else{
            ThreadLocalRandom random = ThreadLocalRandom.current();
            
            int luckyNumber = random.nextInt(min, max);
            
            JLabel winningText = new JLabel("The Winner is");
            winningText.setBounds(120, 80, 200, 100);
            
            String member_name = (String)vectors.rowData.get(luckyNumber).get(0); //  rowData[luckyNumber][0] = name of the winner
            String member_phone = (String) vectors.rowData.get(luckyNumber).get(1); // rowData[luckyNumber][1] = phone number of the winner
            
            vectors.rowData.remove(luckyNumber); // removing the lucky member's index value from the 2D vector, rowData
            
            JLabel winnerName = new JLabel(member_name);
            winnerName.setForeground(Color.red);
            winnerName.setBounds(120, 120, 200, 100);
            
            JLabel winnerPhone = new JLabel(member_phone);
            winnerPhone.setForeground(Color.red);
            winnerPhone.setBounds(120, 160, 200, 100);
            
            JLabel congrats = new JLabel("Congratulations!!!");
            congrats.setBounds(120, 230, 200, 100);
            congrats.setForeground(Color.blue);
            
            machineFrame.frame.add(winningText);
            machineFrame.frame.add(winnerName);
            machineFrame.frame.add(winnerPhone);
            machineFrame.frame.add(congrats);
        }
    }
}

class drawButtonPerform implements ActionListener{
    JFrame f;
    drawButtonPerform(JFrame f, JButton b){
        this.f = f;
        b.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e){
        f.setVisible(false);
        window drawPage = new window();
        drawPage.makeFrame("Draw Page", true, false);
        drawPage.makeButton("<<", 10, 10); // back button created
        backButton bb = new backButton(drawPage.frame, f, drawPage.b);
        drawPage.frame.add(drawPage.b);
        
        window machineButton = new window();
        machineButton.makeButton("Click in the Machine", 125, 150);
        machineButtonPerform mbp = new machineButtonPerform(machineButton.b);
        
        drawPage.frame.add(machineButton.b);
        drawPage.frame.setDefaultCloseOperation(drawPage.frame.EXIT_ON_CLOSE);
    }
}

class listButtonPerform implements ActionListener{
    listButtonPerform(JButton b){
        b.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e){
        JFrame frame = new JFrame("The List");
        
        frame.setSize(400, 400);
        frame.setVisible(true);
        frame.setResizable(false);
        
        
        Vector<String> columnNames = new Vector<>();
        columnNames.addElement("Name");
        columnNames.addElement("Phone");
        
        if(vectors.rowData.size() == 0){
            JLabel l = new JLabel("No Records");
            l.setBounds(150, 100, 150, 50);
            l.setForeground(Color.red); // font color is red
            frame.add(l);
            frame.setLayout(null); 
        }
        else{
            for(int i=0; i<vectors.rowData.size(); i++){ //loop is assigned to fatch the row data
                JTable table = new JTable(vectors.rowData, columnNames);
                JScrollPane scroll = new JScrollPane(table);
                frame.add(scroll);
                // setLayout(null) will not work for the JTable
            }
        }
    }
}


class perticipantListPerform implements ActionListener{
    JFrame f;
    perticipantListPerform(JFrame f, JButton b){
        this.f = f;
        b.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e){
        f.setVisible(false);
        window listPage = new window();
        listPage.makeFrame("Participants List", true, false);
        
        listPage.makeButton("<<", 10, 10); // back button created
        backButton bb = new backButton(listPage.frame, f, listPage.b);
        listPage.frame.add(listPage.b);
        
        window listButton = new window();
        listButton.makeButton("List", 100, 150);
        listButtonPerform lbp = new listButtonPerform(listButton.b);
        listPage.frame.add(listButton.b);
        
        listPage.frame.setDefaultCloseOperation(listPage.frame.EXIT_ON_CLOSE);
    }
}

public class RaffleDraw {

    public static void main(String[] args) {
        // TODO code application logic here
        window firstPage = new window();
        firstPage.makeFrame("Landing Page", true, false);
        firstPage.makeButton("Registration", 120, 30);
        
        firstPage.frame.add(firstPage.b);
       
        registrationButtonPerform rbp = new registrationButtonPerform(firstPage.frame, firstPage.b);
        
        firstPage.makeButton("Draw", 120, 150);
        firstPage.frame.add(firstPage.b);
        drawButtonPerform dbp = new drawButtonPerform(firstPage.frame, firstPage.b);
        
        firstPage.makeButton("Participant List", 120, 270);
        firstPage.frame.add(firstPage.b);
        perticipantListPerform plp = new perticipantListPerform(firstPage.frame, firstPage.b);
        
        
        firstPage.frame.setDefaultCloseOperation(firstPage.frame.EXIT_ON_CLOSE);
    }
}
