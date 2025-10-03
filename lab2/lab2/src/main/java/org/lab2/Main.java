package org.lab2;

import java.util.ArrayList;
import java.util.List;

/**
 * Тип: StringBuffer
 *
 * Дія: В усіх питальних реченнях заданого тексту знайти та надрукувати без повторень слова заданої довжини
 */

public class Main {

    private static boolean equalsContent(StringBuffer a, StringBuffer b) {
        if (a == null || b == null) {
            return false;
        }
        if (a.length() != b.length()) {
            return false;
        }
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    private static StringBuffer copyBuffer(StringBuffer src) {
        StringBuffer dest = new StringBuffer();
        for (int i = 0; i < src.length(); i++) {
            dest.append(src.charAt(i));
        }
        return dest;
    }

    public static void main(String[] args) {
        // Вхідні дані
        String inputText = "Коли починається тренування? Хто буде тренер?" +
                "Яка тривалість занять? Скільки народу прийде?"; // приклад
        // Потрібна довжина слів
        int targetLength = 6;

        StringBuffer textBuffer = new StringBuffer(inputText);

        List<StringBuffer> uniqueWords = new ArrayList<>();

        StringBuffer sentenceBuffer = new StringBuffer();
        StringBuffer wordBuffer = new StringBuffer();

        // Валідація
        if (targetLength <= 0) {
            System.err.println("Помилка! targetLength має бути додатнім цілим числом.");
            return;
        }

        try {
            for (int i = 0; i < textBuffer.length(); i++) {
                char ch = textBuffer.charAt(i);
                sentenceBuffer.append(ch);

                if (ch == '?') {

                    for (int j = 0; j < sentenceBuffer.length(); j++) {
                        char sc = sentenceBuffer.charAt(j);

                        if (Character.isLetter(sc)) {
                            wordBuffer.append(sc);
                        } else {

                            if (wordBuffer.length() > 0) {
                                if (wordBuffer.length() == targetLength) {

                                    boolean found = false;
                                    for (StringBuffer existing : uniqueWords) {
                                        if (equalsContent(existing, wordBuffer)) {
                                            found = true;
                                            break;
                                        }
                                    }
                                    if (!found) {
                                        uniqueWords.add(copyBuffer(wordBuffer));
                                    }
                                }
                                wordBuffer.setLength(0);
                            }
                        }
                    }

                    sentenceBuffer.setLength(0);
                    wordBuffer.setLength(0);
                }
            }
            // Вивід
            if (uniqueWords.isEmpty()) {
                System.out.println("Знайдено 0 слів довжини " + targetLength + " в питальних реченнях.");
            } else {
                System.out.println("Слова довжини " + targetLength + " у питальних реченнях:");
                for (StringBuffer buf : uniqueWords) {
                    System.out.println(buf.toString());
                }
            }

        } catch (NullPointerException npe) {
            System.err.println("NullPointerException: Перевірте вхідні дані. Повідомлення: " + npe.getMessage());
        } catch (IndexOutOfBoundsException iobe) {
            System.err.println("IndexOutOfBoundsException: Помилка індексації символів. Повідомлення: " + iobe.getMessage());
        } catch (IllegalArgumentException iae) {
            System.err.println("IllegalArgumentException: Некоректні аргументи. Повідомлення: " + iae.getMessage());
        } catch (Exception ex) {
            System.err.println("Виникла помилка: " + ex.getClass().getSimpleName() + " - " + ex.getMessage());
        }
    }
}
