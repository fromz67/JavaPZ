package org.lab4;

import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;
public final class QuestionWordFinder {

    private QuestionWordFinder() {}
    public static Set<String> findUniqueWordsInQuestions(Text text, int targetLength) {
        if (text == null) {
            throw new IllegalArgumentException("Text must not be null.");
        }
        if (targetLength <= 0) {
            throw new IllegalArgumentException("targetLength must be positive.");
        }

        Set<String> result = new LinkedHashSet<>();
        for (Sentence s : text.questionSentences()) {
            for (Word w : s.words()) {
                if (w.length() == targetLength) {
                    String normalized = w.content().toLowerCase(Locale.ROOT);
                    result.add(normalized);
                }
            }
        }
        return result;
    }

    /**
     * Build the model, run the search, print result
     */
    public static void main(String[] args) {
        // Input sample
        String inputText =
                "Коли починається тренування? Хто буде тренер? "
                        + "\tЯка тривалість   занять?   Скільки народу прийде?";
        int targetLength = 6; // required word length

        try {
            Text text = TextParser.parse(inputText);
            Set<String> unique = findUniqueWordsInQuestions(text, targetLength);

            if (unique.isEmpty()) {
                System.out.printf(
                        "Знайдено 0 слів довжини %d у питальних реченнях.%n", targetLength);
            } else {
                System.out.printf(
                        "Слова довжини %d у питальних реченнях:%n", targetLength);
                for (String w : unique) {
                    System.out.println(w);
                }
            }

        } catch (IllegalArgumentException ex) {
            System.err.println("Некоректні аргументи: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.printf("Виникла помилка: %s - %s%n",
                    ex.getClass().getSimpleName(), ex.getMessage());
        }
    }
}
