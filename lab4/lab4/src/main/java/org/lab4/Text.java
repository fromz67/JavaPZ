package org.lab4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A text composed of sentences.
 */
public final class Text {
    private final List<Sentence> sentences;

    public Text(List<Sentence> sentences) {
        if (sentences == null || sentences.isEmpty()) {
            throw new IllegalArgumentException("Text must contain at least one sentence.");
        }
        this.sentences = Collections.unmodifiableList(new ArrayList<>(sentences));
    }

    public List<Sentence> sentences() {
        return sentences;
    }

    public List<Sentence> questionSentences() {
        return sentences.stream()
                .filter(Sentence::isQuestion)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public String toString() {
        return sentences.stream().map(Sentence::toString).collect(Collectors.joining(" "));
    }
}
