package org.lab4;

import java.util.ArrayList;
import java.util.List;
public final class TextParser {

    private TextParser() {}

    public static String normalizeWhitespace(String raw) {
        if (raw == null) {
            throw new IllegalArgumentException("Input text must not be null.");
        }
        return raw.replaceAll("[ \\t]+", " ").trim();
    }
    public static Text parse(String raw) {
        String input = normalizeWhitespace(raw);
        if (input.isEmpty()) {
            throw new IllegalArgumentException("Input text must not be empty.");
        }

        List<Sentence> sentences = new ArrayList<>();
        List<SentenceElement> current = new ArrayList<>();

        List<Letter> currentLetters = new ArrayList<>();

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            if (Character.isLetter(ch)) {
                currentLetters.add(new Letter(ch));
            } else {
                flushWordIfAny(current, currentLetters);

                if (ch == ' ') {
                } else {
                    current.add(new PunctuationMark(ch));

                    if (ch == '.' || ch == '!' || ch == '?') {
                        if (!current.isEmpty()) {
                            sentences.add(new Sentence(current));
                            current = new ArrayList<>();
                        }
                    }
                }
            }
        }

        flushWordIfAny(current, currentLetters);
        if (!current.isEmpty()) {
            sentences.add(new Sentence(current));
        }

        if (sentences.isEmpty()) {
            throw new IllegalArgumentException("No sentences found in input.");
        }
        return new Text(sentences);
    }

    private static void flushWordIfAny(
            List<SentenceElement> elements, List<Letter> currentLetters) {
        if (!currentLetters.isEmpty()) {
            elements.add(new Word(currentLetters));
            currentLetters.clear();
        }
    }
}
