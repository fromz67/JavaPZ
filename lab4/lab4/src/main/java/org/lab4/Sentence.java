package org.lab4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A sentence consisting of a sequence of elements: words and punctuation marks.
 */
public final class Sentence {
    private final List<SentenceElement> elements;

    public Sentence(List<SentenceElement> elements) {
        if (elements == null || elements.isEmpty()) {
            throw new IllegalArgumentException("Sentence must contain at least one element.");
        }
        this.elements = Collections.unmodifiableList(new ArrayList<>(elements));
    }

    public List<SentenceElement> elements() {
        return elements;
    }

    public boolean isQuestion() {
        SentenceElement last = elements.get(elements.size() - 1);
        return (last instanceof PunctuationMark)
                && ((PunctuationMark) last).mark() == '?';
    }

    public List<Word> words() {
        return elements.stream()
                .filter(e -> e instanceof Word)
                .map(e -> (Word) e)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String lastType = "PUNC";
        for (SentenceElement el : elements) {
            if (el instanceof Word) {
                if (!"WORD".equals(lastType) && sb.length() > 0) {
                    sb.append(' ');
                }
                sb.append(el.content());
                lastType = "WORD";
            } else {
                sb.append(el.content());
                lastType = "PUNC";
            }
        }
        return sb.toString();
    }
}
