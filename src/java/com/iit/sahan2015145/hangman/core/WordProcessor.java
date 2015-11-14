package com.iit.sahan2015145.hangman.core;

/**
 * Created by sahan on 11/14/15.
 */
public class WordProcessor {

    public static final int DUPLICATE_GUESS = -1;
    public static final int MATCH_FOUND = 1;
    public static final int MATCH_NOT_FOUND = 0;
    public static final int WON = 2;
    public static final int LOOSE = -2;

    private static WordProcessor instance;

    public static final int TRIES = 10;

    private String padding;
    private String word;
    private int correctGuessCount = 0;
    private char[] correctGuesses;
    private char[] letters;
    private int tries = 0;

    private WordProcessor(int padding) {
        this.padding = "";
        for (int i = 0; i < padding; i++) {
            this.padding += " ";
        }
    }

    public static WordProcessor getInstance(int padding) {
        if (instance == null)
            instance = new WordProcessor(padding);
        return instance;
    }

    public String getTextToDisplay() {
        String text = "";
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            boolean letterFound = false;
            for (int j = 0; j < correctGuesses.length; j++) {
                if (c == correctGuesses[j]) {
                    text += c;
                    letterFound = true;
                    break;
                }
            }
            if (!letterFound) {
                text += "X";
            }
            text += padding;
        }
        return text;
    }

    public void newWord(String newWord) {
        this.word = newWord.toUpperCase();
        String letters = "";
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!letters.contains(c + "")) {
                letters += c;
            }
        }
        this.letters = new char[letters.length()];
        this.correctGuesses = new char[letters.length()];
        for (int i = 0; i < letters.length(); i++) {
            this.letters[i] = letters.charAt(i);
        }
    }

    public int tryLetter(char c) {
        if (tries <= TRIES) {
            tries++;
            boolean isGuessedBefore = false;
            c = Character.toUpperCase(c);
            for (int j = 0; j < correctGuesses.length; j++) {
                if (correctGuesses[j] == c) {
                    isGuessedBefore = true;
                    break;
                }
            }
            if (!isGuessedBefore) {
                boolean isMatchFound = false;
                for (int i = 0; i < letters.length; i++) {
                    if (letters[i] == c) {
                        isMatchFound = true;
                        correctGuesses[correctGuessCount++] = c;
                        break;
                    }
                }
                if (isMatchFound) {
                    if (correctGuessCount == correctGuesses.length)
                        return WON;
                    return MATCH_FOUND;
                } else {
                    if (tries == TRIES) {
                        return LOOSE;
                    }
                    return MATCH_NOT_FOUND;
                }
            } else {
                return DUPLICATE_GUESS;
            }
        }
        return LOOSE;
    }

    public String getTries() {
        return tries + "/" + TRIES;
    }

    public String getWord() {
        return word;
    }
}
