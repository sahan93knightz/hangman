package com.iit.sahan2015145.hangman.actions;

import com.iit.sahan2015145.hangman.core.WordProcessor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by sahan on 11/14/15.
 */
public class TryButtonAction implements ActionListener {

    private static final WordProcessor WORD_PROCESSOR = WordProcessor.getInstance(2);
    private final Viewable view;

    public TryButtonAction(Viewable viewable) {
        this.view = viewable;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String text = view.getGuessedText();
        if (text != null && !text.isEmpty()) {
            int result = WORD_PROCESSOR.tryLetter(text.charAt(0));
            switch (result) {
                case WordProcessor.MATCH_FOUND:
                    view.showResult(WORD_PROCESSOR.getTextToDisplay(), "Correct Letter", WORD_PROCESSOR.getTries(), "");
                    break;
                case WordProcessor.MATCH_NOT_FOUND:
                    view.showResult(WORD_PROCESSOR.getTextToDisplay(), "Incorrect Letter", WORD_PROCESSOR.getTries(), "");
                    break;
                case WordProcessor.DUPLICATE_GUESS:
                    view.showResult(WORD_PROCESSOR.getTextToDisplay(), "Duplicate Letter", WORD_PROCESSOR.getTries(), "");
                    break;
                case WordProcessor.WON:
                    view.showResult(WORD_PROCESSOR.getTextToDisplay(), "You Won!", WORD_PROCESSOR.getTries(), "");
                    view.end(result);
                    break;
                case WordProcessor.LOOSE:
                    view.showResult(WORD_PROCESSOR.getWord(), "You Loose!", WORD_PROCESSOR.getTries(), "");
                    view.end(result);
                    break;
            }
        }
    }
}
