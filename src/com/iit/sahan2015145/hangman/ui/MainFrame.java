package com.iit.sahan2015145.hangman.ui;

import com.iit.sahan2015145.hangman.actions.TryButtonAction;

import javax.swing.*;
import java.awt.*;

/**
 * Created by sahan on 11/14/15.
 */
public class MainFrame extends JFrame {

    private final String word;
    private JLabel lblImage;
    private JLabel lblWord;
    private JLabel lblTries;
    private JButton btnTry;
    private JTextField txtWord;

    public MainFrame(String word) {
        super("Hangman - Guess the word");
        this.word = word;
        initUI();

    }

    private void initUI() {
        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        JPanel leftPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        JPanel rightPanel = new JPanel(new GridLayout(1, 1, 5, 5));

        lblImage = new JLabel("IMAGE");
        lblWord = new JLabel("XXXXXXX", JLabel.CENTER);
        lblTries = new JLabel("0");
        btnTry = new JButton("Try Letter");
        txtWord = new JTextField();
        txtWord.setHorizontalAlignment(JTextField.CENTER);

        TryButtonAction tryButtonAction = new TryButtonAction();

        btnTry.addActionListener(tryButtonAction);
        txtWord.addActionListener(tryButtonAction);

        leftPanel.add(lblWord);
        leftPanel.add(lblTries);
        leftPanel.add(txtWord);
        leftPanel.add(btnTry);

        rightPanel.add(lblImage);

        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);

        setLayout(new BorderLayout(10, 10));
        add(mainPanel, BorderLayout.CENTER);

        setSize(400, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
