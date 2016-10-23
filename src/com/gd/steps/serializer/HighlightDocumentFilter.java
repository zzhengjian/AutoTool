package com.gd.steps.serializer;

import java.awt.Color;

import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter.DefaultHighlightPainter;
import javax.swing.text.DocumentFilter;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class HighlightDocumentFilter extends DocumentFilter {

    private DefaultHighlightPainter highlightPainter = new DefaultHighlightPainter(Color.YELLOW);
    private JTextPane textPane;
    private SimpleAttributeSet background;

    public HighlightDocumentFilter(JTextPane textPane) {
        this.textPane = textPane;
        background = new SimpleAttributeSet();
        StyleConstants.setBackground(background, Color.RED);
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
        System.out.println("insert");
        super.insertString(fb, offset, text, attr);
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        System.out.println("remove");
        super.remove(fb, offset, length);
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {

        String match = "test";

        super.replace(fb, offset, length, text, attrs);

        int startIndex = offset - match.length();
        if (startIndex >= 0) {

            String last = fb.getDocument().getText(startIndex, match.length()).trim();
            System.out.println(last);
            if (last.equalsIgnoreCase(match)) {

                textPane.getHighlighter().addHighlight(startIndex, startIndex + match.length(), highlightPainter);

            }

        }
    }

}