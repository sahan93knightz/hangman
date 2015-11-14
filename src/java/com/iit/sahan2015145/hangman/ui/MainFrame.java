package com.iit.sahan2015145.hangman.ui;

import com.iit.sahan2015145.hangman.actions.TryButtonAction;
import com.iit.sahan2015145.hangman.actions.Viewable;
import com.iit.sahan2015145.hangman.core.WordProcessor;

import javax.swing.*;
import java.awt.*;

/**
 * Created by sahan on 11/14/15.
 */
public class MainFrame extends JFrame implements Viewable {

    private static final WordProcessor WORD_PROCESSOR = WordProcessor.getInstance(2);
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
        newWord();
    }

    private void newWord() {
        WORD_PROCESSOR.newWord(word);
        lblWord.setText(WORD_PROCESSOR.getTextToDisplay());
    }

    private void initUI() {
        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        JPanel leftPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        JPanel rightPanel = new JPanel(new GridLayout(1, 1, 5, 5));

        lblImage = new JLabel("IMAGE");
        lblWord = new JLabel("", JLabel.CENTER);
        lblTries = new JLabel("0");
        btnTry = new JButton("Try Letter");
        txtWord = new JTextField();
        txtWord.setHorizontalAlignment(JTextField.CENTER);

        TryButtonAction tryButtonAction = new TryButtonAction(this);

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

    @Override
    public String getGuessedText() {
        return txtWord.getText();
    }

    @Override
    public void showResult(String word, String message, String tries, String imgUrl) {
        lblWord.setText(word);
        lblTries.setText(tries);
        txtWord.setText(null);
    }

    @Override
    public void end(int result) {
        if (result == WORD_PROCESSOR.WON) {
            JOptionPane.showMessageDialog(this, "You Won!");
        } else {
            JOptionPane.showMessageDialog(this, "You Loose!");

        }
    }
}
