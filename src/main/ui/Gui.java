package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.*;
import model.exceptions.ExistingMemberException;
import model.exceptions.NotADayException;
import model.exceptions.NotAMemberException;
import persistance.Reader;
import persistance.Writer;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;

//https://stackoverflow.com/questions/5911565/how-to-add-multiple-actionlisteners-for-multiple-buttons-in-java-swing
// help from https://gist.github.com/jimmykurian/2026977
// implements jframe and it subsideries
public class Gui extends DefaultTableModel implements ActionListener {
    JButton save;
    JButton load;
    JButton add;
    JButton remove;
    JButton attend;
    JButton liststu;
    private Scanner input;
    Members academy = new Members();
    Writer saver;
    Reader read;
    JFrame frame;
    JPanel panel = new JPanel();
    Student newstu;

    // Modifies: this
    // Effects: sets up initial button window
    public void thing() {
        frame = new JFrame();
        frame.setSize(700, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        save = new JButton("Save");
        panel.add(save);
        load = new JButton("Load");
        panel.add(load);
        add = new JButton("add student");
        panel.add(add);
        remove = new JButton("remove student");
        panel.add(remove);
        attend = new JButton("attend class");
        panel.add(attend);
        liststu = new JButton("List Students");
        panel.add(liststu);
        frame.add(panel);
        add.addActionListener(this);
        save.addActionListener(this);
        load.addActionListener(this);
        attend.addActionListener(this);
        remove.addActionListener(this);
        liststu.addActionListener(this);

    }

    // effects: performs action on every button press
    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == save) {
            saver = new Writer();
            saver.attendWriter(academy);
            playSound("pluck.wav");
        } else if (evt.getSource() == load) {
            read = new Reader();
            academy = read.attendReader();
            playSound("ominous.wav");
        } else if (evt.getSource() == add) {
            JFrame addframe = new JFrame("add student");
            makeaddframe(addframe);
        } else if (evt.getSource() == remove) {
            JFrame removeframe = new JFrame("remove student");
            makeremoveframe(removeframe);
        } else if (evt.getSource() == liststu) {
            JFrame listframe = new JFrame("list student");
            makelistframe(listframe);
        } else if (evt.getSource() == attend) {
            JFrame attendframe = new JFrame("attend student");
            makeattendframe(attendframe);
        }

    }
    // modifies: this
    // effects: makes window for add button and adds student

    public void makeaddframe(JFrame aframe) throws  NumberFormatException {
        playSound("yay_z.wav");
        makeFrame(aframe);
        JTextField addfield = new JTextField("student name");
        JTextField agefield = new JTextField("student age");
        JButton submit = new JButton("submit");
        JPanel apanel = new JPanel();
        apanel.add(addfield);
        apanel.add(agefield);
        apanel.add(submit);
        aframe.add(apanel);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    removeFrame(aframe);
                    newstu = new Student(addfield.getText(), Integer.parseInt(agefield.getText()), "white");
                    academy.addNewMember(newstu);
                } catch (ExistingMemberException | NumberFormatException m) {
                    JOptionPane.showMessageDialog(frame, "Try Again"
                            + ", be sure to not make an existing member and to input a number as age");
                }



            }
        });
    }
    // modifies: this
    // effects: makes window for remove button and removes student

    public void makeremoveframe(JFrame rframe) {
        playSound("whah_whah.wav");
        makeFrame(rframe);
        JTextField removedfield = new JTextField("student name");
        JPanel rpanel = new JPanel();
        JButton submit = new JButton("submit");
        rpanel.add(removedfield);
        rpanel.add(submit);
        rframe.add(rpanel);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = removedfield.getText();
                try {
                    academy.removeMember(name);
                    removeFrame(rframe);
                } catch (NotAMemberException m) {
                    JOptionPane.showMessageDialog(frame, "There does not exist a member with the given specifications");

                }


            }
        });


    }
    // modifies: this
    // effects: makes window for attend button and adds attendance

    public void makeattendframe(JFrame atdframe) {
        playSound("boom_x.wav");
        makeFrame(atdframe);
        JTextField namefield = new JTextField("student name");
        JTextField dayfield = new JTextField("enter day, mon is 1, sun is 7 ");
        JButton submit = new JButton("submit");
        JPanel atdpanel = new JPanel();
        atdpanel.add(namefield);
        atdpanel.add(dayfield);
        atdpanel.add(submit);
        atdframe.add(atdpanel);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    removeFrame(atdframe);
                    academy.findMember(namefield.getText()).attend(Integer.parseInt(dayfield.getText()));
                } catch (NotADayException | IndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(frame, "please try again and pick a number between 1 and 7");
                } catch (NotAMemberException m) {
                    JOptionPane.showMessageDialog(frame, "There does not exist a member with the given specifications");
                }
            }
        });


    }
// https://www.geeksforgeeks.org/java-swing-jtable/
    // modifies: this
    // effects: makes list button window and table

    public void makelistframe(JFrame lframe) {
        playSound("laugh_track_x.wav");
        makeFrame(lframe);
        String[] columnNames = {"Name", "Age", "Belt", "Attendance"};
        String[][] data;
        DefaultTableModel table = new DefaultTableModel();
        JPanel lpanel = new JPanel(new BorderLayout());
        table.addColumn("Name");
        table.addColumn("Age");
        table.addColumn("Belt");
        table.addColumn("Attendance");

        for (int i = 0; i < academy.memberSize(); i++) {
            String name = academy.getMember(i).getName();
            String age = Integer.toString((academy.getMember(i).getAge()));
            String belt = academy.getMember(i).getBelt();
            String attendance = academy.getMember(i).getAttendanceSheet();
            String[] toadd = {name, age, belt, attendance};
            table.addRow(toadd);

        }
        JTable finaltable = new JTable(table);
        lpanel.add(finaltable);
        lframe.add(lpanel);


    }
    // function copied from http://suavesnippets.blogspot.com/2011/06/add-sound-on-jbutton-click-in-java.html
    //effects: plays sound

    public void playSound(String soundName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }
    // effects: sets up frame

    public void makeFrame(JFrame frame) {
        frame.setSize(500, 400);
        frame.setVisible(true);

    }
    // effects: removes frame

    public void removeFrame(JFrame frame) {
        frame.setVisible(false);
        frame.dispose();
    }



}


