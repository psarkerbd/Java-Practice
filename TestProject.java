/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testproject;
import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.io.*;



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
        //b.setBackground(Color.blue);
        b.setBounds(x, y, 150, 50);
    }
}

class arraylists{
    static ArrayList<String> nameList = new ArrayList<>();
    static ArrayList<String> phoneList = new ArrayList<>();
}

class vectors{
    static Vector<Vector> rowData = new Vector<Vector>();
}


class submitButton implements ActionListener{
    JTextField tf1, tf2;
    
    submitButton(JTextField tf1, JTextField tf2, JButton b){
        this.tf1 = tf1;
        this.tf2 = tf2;
        b.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e){
        String name = tf1.getText();
        String phone = tf2.getText();
        
   
        JFrame j = new JFrame();
        
        if(name.length() == 0 || phone.length() == 0){
            JOptionPane.showMessageDialog(j, "Missing field(s)", "Alert", JOptionPane.WARNING_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(j, "Thank you " + name + ". You are Successfully addedd");
            
            //arraylists.nameList.add(name); // add name into the arraylist nameList
            //arraylists.phoneList.add(phone); // add phone into the arrayList phoneList
            
            //for(int i=0; i<arraylists.phoneList.size(); i++){
            
            Vector<String> rowOne = new Vector<String>(); // the registered data directly inserted into rowData vector
            //rowOne.clear();
            rowOne.addElement(name);
            rowOne.addElement(phone);
            vectors.rowData.addElement(rowOne);

        //}
            
            
            tf1.setText("");
            tf2.setText("");
        }
    }
}

class backButton implements ActionListener{
    JFrame f1, f2;
    backButton(JFrame f1, JFrame f2, JButton b){
        this.f1 = f1;
        this.f2 = f2;
        b.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e){
        f1.setVisible(true);
        f2.setVisible(false);
    }
}

class registrationButton implements ActionListener{
    JFrame f;
   registrationButton(JFrame f, JButton b){
        this.f = f;
        b.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e){
        f.setVisible(false);
        window registrationPage = new window();
        window bButton = new window();
        
        
        registrationPage.makeFrame("Registration Page", true, false);
        bButton.makeButton("<<", 10, 10);
        registrationPage.frame.add(bButton.b);
        backButton bb = new backButton(f, registrationPage.frame, bButton.b);
        
        JLabel nameLabel = new JLabel("Name: ");
        nameLabel.setBounds(100, 100, 100, 50);
        
        JLabel phoneLabel = new JLabel("Phone no: ");
        phoneLabel.setBounds(80, 150, 100, 50);
        
        
        JTextField nameText = new JTextField();
        nameText.setBounds(150, 110, 180, 30);
        
        JTextField phoneText = new JTextField();
        phoneText.setBounds(150, 160, 180, 30);
        
        
        window sButton = new window();
        sButton.makeButton("Submit", 150, 250);
        submitButton sb = new submitButton(nameText, phoneText, sButton.b);
        
        registrationPage.frame.add(nameLabel);
        registrationPage.frame.add(nameText);
        registrationPage.frame.add(phoneLabel);
        registrationPage.frame.add(phoneText);
        registrationPage.frame.add(sButton.b);
        registrationPage.frame.setDefaultCloseOperation(registrationPage.frame.EXIT_ON_CLOSE);
    }
}



class listButtonPerform implements ActionListener{
    public listButtonPerform(JButton b) {
        b.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e){
        JFrame frame = new JFrame();
    
        //Vector<String> rowOne = new Vector<String>();
        Vector<String> columnNames = new Vector<String>();
        columnNames.addElement("Name");
        columnNames.addElement("Cell");
    
        if(vectors.rowData.size() == 0){
            JLabel l = new JLabel();
            l.setText("     No Records Found!"   );
            //l.setBounds(200, 100, 100, 100);
            frame.add(l, BorderLayout.CENTER);
        }
    
        else {
            //for(int i=0; i<arraylists.phoneList.size(); i++){
            //Vector<String> rowOne = new Vector<String>();
            //rowOne.clear();
            //rowOne.addElement(arraylists.nameList.get(i));
            //rowOne.addElement(arraylists.phoneList.get(i));
            //vectors.rowData.addElement(rowOne);
            //JTable table = new JTable(vectors.rowData, columnNames);
            //rowOne.clear();
            //JScrollPane scrollPane = new JScrollPane(table);
            //frame.add(scrollPane, BorderLayout.CENTER);
            
            for(int i=0; i<vectors.rowData.size(); i++){
                JTable table = new JTable(vectors.rowData, columnNames);
                //rowOne.clear();
                JScrollPane scrollPane = new JScrollPane(table);
                frame.add(scrollPane, BorderLayout.CENTER);
            }

        //}
    }
        frame.setSize(300, 300);
        frame.setVisible(true);
    }
}

class participantList implements ActionListener{
    JFrame f;
    participantList(JFrame f, JButton b) {
        this.f = f;
        b.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e){
        f.setVisible(false);
        
        window listPage = new window();
        listPage.makeFrame("Participants List", true, false);
       
        window bButton = new window();
        bButton.makeButton("<<", 10, 10);
        listPage.frame.add(bButton.b);
        backButton bb = new backButton(f, listPage.frame, bButton.b);
        
        window listButton = new window();
        listButton.makeButton("List", 100, 150);
        
        listButtonPerform lb = new listButtonPerform(listButton.b);
        
        listPage.frame.add(listButton.b);
        
        listPage.frame.setDefaultCloseOperation(listPage.frame.EXIT_ON_CLOSE);
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
            JLabel l = new JLabel("No records");
            l.setBackground(Color.red);
            l.setBounds(150, 80, 200, 100);
            machineFrame.frame.add(l);
        }
        
        else {
            JLabel winner = new JLabel("The Lucky Member is");
            winner.setBounds(120, 80, 200, 100);
            Random rand = new Random();
            int randnum = rand.nextInt((max - min) + 1) + min;
            //System.out.println(vectors.rowData.get(randnum));
            String member_name = (String)vectors.rowData.get(randnum).get(0);
            String member_phone = (String)vectors.rowData.get(randnum).get(1);
            
            JLabel memberName = new JLabel(member_name);
            memberName.setBounds(120, 120, 200, 100);
            
            JLabel memberPhone = new JLabel(member_phone);
            memberPhone.setBounds(200, 120, 200, 100);
            
            
            vectors.rowData.remove(randnum);
        
            machineFrame.frame.add(winner);
            machineFrame.frame.add(memberName);
            machineFrame.frame.add(memberPhone);
        }
        
        
        //machineFrame.frame.setDefaultCloseOperation(machineFrame.frame.EXIT_ON_CLOSE);
    }
}


class draw implements ActionListener{
    JFrame f;
    draw(JFrame f, JButton b){
        this.f = f;
        b.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e){
        f.setVisible(false);
        
        window drawPage = new window();
        
        drawPage.makeFrame("The Machine", true, false);
        
        window bButton = new window();
        bButton.makeButton("<<", 10, 10);
        drawPage.frame.add(bButton.b);
        backButton bb = new backButton(f, drawPage.frame, bButton.b);
        
        window machineButton = new window();
        
        machineButton.makeButton("Click the Machine", 100, 150);
        machineButtonPerform mbp = new machineButtonPerform(machineButton.b);
        
        drawPage.frame.add(machineButton.b);
        
        drawPage.frame.setDefaultCloseOperation(drawPage.frame.EXIT_ON_CLOSE);
    }
}

public class TestProject{
    public static void main(String [] args){
        window firstPage = new window();
        firstPage.makeFrame("First frame", true, false);
        firstPage.makeButton("Registration", 120, 30);
        firstPage.frame.add(firstPage.b);
        firstPage.frame.setDefaultCloseOperation(firstPage.frame.EXIT_ON_CLOSE);
        registrationButton rb = new registrationButton(firstPage.frame, firstPage.b);
        
        
        firstPage.makeButton("Draw", 120, 150);
        firstPage.frame.add(firstPage.b);
        draw d = new draw(firstPage.frame, firstPage.b);
        
        firstPage.makeButton("Participants List", 120, 270);
        firstPage.frame.add(firstPage.b);
        participantList plist = new participantList(firstPage.frame, firstPage.b);
    }
}