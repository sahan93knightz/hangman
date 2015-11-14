package com.iit.sahan2015145.hangman.actions;

/**
 * Created by sahan on 11/14/15.
 */
public interface Viewable {

    String getGuessedText();

    void showResult(String word, String message, String tries, String imgUrl);

    void end(int result);
}
