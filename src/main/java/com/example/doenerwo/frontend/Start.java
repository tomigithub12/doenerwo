package com.example.doenerwo.frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import com.example.doenerwo.domain.Doenerstand;
import com.example.doenerwo.repository.DoenerstandRepository;
import com.example.doenerwo.service.DoenerstandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.stereotype.Service;

@Service
public class Start {

    @Autowired
    private DoenerstandRepository doenerRepo;

    @Autowired
    DoenerstandService DS;


    public Start() {
        super();
    }

    public void init() {
        //Start window for username registration
        JFrame frame = new JFrame("Dönerwo");
        frame.setSize(390, 300);
        frame.setLocation(100, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setDefaultLookAndFeelDecorated(true);
        frame.setResizable(false);


        JLabel label1 = new JLabel("Wo verhungerst du: ");
        label1.setBounds(50, 50, 200, 30);
        label1.setLocation(95, 55);
        JTextField t1 = new JTextField();
        t1.setBounds(50, 80, 200, 30);
        t1.setLocation(95,80);
        frame.add(label1);
        frame.add(t1);

        JButton startBtn = new JButton("Wo Döner");
        startBtn.setBounds(120, 110, 150, 30);
        frame.add(startBtn);

        displayDoenerstaende();

        startBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                JFrame nframe = new JFrame("HierDöner");
                nframe.setSize(600, 300);
                nframe.setLocation(100, 150);
                nframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                nframe.setDefaultLookAndFeelDecorated(true);
                nframe.setResizable(false);

                String myLocation = t1.getText();
                String naechsterDeoner = DS.calculateFindings(myLocation);

                //displayDoenerstaende();
                //List<Doenerstand> staendeListe = doenerRepo.findAll();
                //System.out.print(staendeListe);

                List<String> standlListe = new ArrayList<String>();
                standlListe.add(naechsterDeoner);
                JList booklist = new JList(standlListe.toArray());
                booklist.setVisibleRowCount(4);
                JScrollPane pane = new JScrollPane(booklist);
                pane.setSize(600,300);
                pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                nframe.add(pane, BorderLayout.CENTER);

                JPanel panel = new JPanel();
                panel.setBackground(Color.WHITE);
                nframe.add(panel);
                nframe.setVisible(true);

            }
        });


        JPanel panel = new JPanel();
        panel.setBackground(Color.CYAN);
        frame.add(panel);

        //frame.setLayout(null);
        frame.setVisible(true);
    }

    private void displayDoenerstaende() {
        List<Doenerstand> staendeListe = doenerRepo.findAll();
        System.out.print(staendeListe);

        JList<Doenerstand> booklist = new JList();
        booklist.setVisibleRowCount(1);
        JScrollPane pane = new JScrollPane(booklist);
        //frame.add(pane, BorderLayout.NORTH);


    }
}
