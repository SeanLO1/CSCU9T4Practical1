// GUI and main program for the Training Record
package com.stir.cscu9t4practical1;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.lang.Number;

public class TrainingRecordGUI extends JFrame implements ActionListener {

    private JTextField name = new JTextField(30);
    private JTextField day = new JTextField(2);
    private JTextField month = new JTextField(2);
    private JTextField year = new JTextField(4);
    private JTextField hours = new JTextField(2);
    private JTextField mins = new JTextField(2);
    private JTextField secs = new JTextField(2);
    private JTextField dist = new JTextField(4);
    private JTextField sport=new JTextField(5);
    private JTextField sport1=new JTextField(5);
    private JLabel labn = new JLabel(" Name:");
    private JLabel labd = new JLabel(" Day:");
    private JLabel labm = new JLabel(" Month:");
    private JLabel laby = new JLabel(" Year:");
    private JLabel labh = new JLabel(" Hours:");
    private JLabel labmm = new JLabel(" Mins:");
    private JLabel labs = new JLabel(" Secs:");
    private JLabel labdist = new JLabel(" Distance (km):");
    private JButton addR = new JButton("Add");
    private JButton remove=new JButton("Remove");
    private JButton lookUpByDate = new JButton("Look Up");
    private JButton findAllByDate = new JButton("Find All");
    private JPanel sportChoice1 = new JPanel();
    private JPanel sportChoice2 = new JPanel();
    String[] SportType ={"Swimming", "Sprint", "Cycling"};
    private JComboBox sportSelection = new JComboBox(SportType);
    private JLabel labSport1 = new JLabel("Pool or Outdoor");
    private JLabel labSport2 = new JLabel("Style");
    private TrainingRecord myAthletes = new TrainingRecord();

    private JTextArea outputArea = new JTextArea(5, 50);

    public static void main(String[] args) {
        TrainingRecordGUI applic = new TrainingRecordGUI();
    } // main

    // set up the GUI
    public TrainingRecordGUI() {
        super("Training Record");
        setLayout(new FlowLayout());
        add(labn);
        add(name);
        name.setEditable(true);
        add(labd);
        add(day);
        day.setEditable(true);
        add(labm);
        add(month);
        month.setEditable(true);
        add(laby);
        add(year);
        year.setEditable(true);
        add(labh);
        add(hours);
        hours.setEditable(true);
        add(labmm);
        add(mins);
        mins.setEditable(true);
        add(labs);
        add(secs);
        secs.setEditable(true);
        add(sportSelection);
        sportSelection.addActionListener(this);
        sportChoice1.add(labSport1);
        sportChoice1.add(sport);
        add(sportChoice1);
        sportChoice2.add(labSport2);
        sportChoice2.add(sport1);
        add(sportChoice2);
        add(labdist);
        add(dist);
        dist.setEditable(true);
        add(addR);
        addR.addActionListener(this);
        add(lookUpByDate);
        lookUpByDate.addActionListener(this);
        add(findAllByDate);
        findAllByDate.addActionListener(this);
        add(remove);
        remove.addActionListener(this);


        add(outputArea);
        outputArea.setEditable(false);
        setSize(720, 200);
        setVisible(true);
        blankDisplay();

        // To save typing in new entries while testing, uncomment
        // the following lines (or add your own test cases)

    } // constructor

    // listen for and respond to GUI events
    public void actionPerformed(ActionEvent event) {
        String message = "";
        if (event.getSource() == addR) {
            message = addEntry("generic");
        }
        if (event.getSource() == lookUpByDate) {
            message = lookupEntry();
        }
        if(event.getSource() == findAllByDate){
            message = FindAllByDate();
        }
        if(event.getSource() == remove){
            message = remove();
        }
        if(event.getSource() == sportSelection){
            typeOfSport(sportSelection.getSelectedItem());
            return;
        }
        outputArea.setText(message);
        blankDisplay();
    } // actionPerformed

    public String addEntry(String what) {
        String message = "Record added\n";
        System.out.println("Adding "+what+" entry to the records");
        String n = name.getText();
        int m = Integer.parseInt(month.getText());
        int d = Integer.parseInt(day.getText());
        int y = Integer.parseInt(year.getText());
        float km = java.lang.Float.parseFloat(dist.getText());
        int h = Integer.parseInt(hours.getText());
        int mm = Integer.parseInt(mins.getText());
        int s = Integer.parseInt(secs.getText());
        String selectedSport = (String) sportSelection.getSelectedItem();
        Entry e = new Entry(n, d, m, y, h, mm, s, km);
        if(selectedSport.equals("Swimming")){
            String where = sport.getText();
            String style = sport1.getText();
            e = new SwimEntry(n, d, m, y, h, mm, s, km,where);
        }else if(selectedSport.equals("Cycling")){
            String terrain = sport.getText();
            String tempo = sport1.getText();
            e = new CycleEntry(n, d, m, y, h, mm, s, km,terrain,tempo);
        }else if(selectedSport.equals("Sprint")){
            int repetition = Integer.parseInt(sport.getText());
            int recovery =  Integer.parseInt(sport1.getText());
            e = new SprintEntry(n, d, m, y, h, mm, s, km,repetition,recovery);
        }

        myAthletes.addEntry(e);
        return message;
    }

    public String remove(){
        int m = Integer.parseInt(month.getText());
        int d = Integer.parseInt(day.getText());
        int y = Integer.parseInt(year.getText());
        String n = name.getText();
        outputArea.setText("looking up record ...");
        String message=myAthletes.delete(n,d,m,y);
        return message;
    }
    public String lookupEntry() {
        int m = Integer.parseInt(month.getText());
        int d = Integer.parseInt(day.getText());
        int y = Integer.parseInt(year.getText());
        outputArea.setText("looking up record ...");
        String message = myAthletes.lookupEntry(d, m, y);
        return message;
    }
    public String FindAllByDate(){
        int m = Integer.parseInt(month.getText());
        int d = Integer.parseInt(day.getText());
        int y = Integer.parseInt(year.getText());
        outputArea.setText("looking up record ...");
        String message = myAthletes.FindAllByDate(d, m, y);
        return message;
    }
    public void typeOfSport(Object sport){
        if(sport.equals("Swimming")){
            labSport1.setText("Pool or Outdoor");
            labSport2.setText("Style");
        }else if(sport.equals("Cycling")){
            labSport1.setText("Terrain");
            labSport2.setText("Tempo");
        }else if(sport.equals("Sprint")){
            labSport1.setText("Repetitions");
            labSport2.setText("Recovery");
        }
    }

    public void blankDisplay() {
        name.setText("");
        day.setText("");
        month.setText("");
        year.setText("");
        hours.setText("");
        mins.setText("");
        secs.setText("");
        dist.setText("");

    }// blankDisplay
    // Fills the input fields on the display for testing purposes only
    public void fillDisplay(Entry ent) {
        name.setText(ent.getName());
        day.setText(String.valueOf(ent.getDay()));
        month.setText(String.valueOf(ent.getMonth()));
        year.setText(String.valueOf(ent.getYear()));
        hours.setText(String.valueOf(ent.getHour()));
        mins.setText(String.valueOf(ent.getMin()));
        secs.setText(String.valueOf(ent.getSec()));
        dist.setText(String.valueOf(ent.getDistance()));
    }

} // TrainingRecordGUI

