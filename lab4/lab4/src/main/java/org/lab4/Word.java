package org.lab4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A word consisting solely of letters.
 */
public final class Word implements SentenceElement {
    private final List<Letter> letters;

    public Word(List<Letter> letters) {
        if (letters == null || letters.isEmpty()) {
            throw new IllegalArgumentException("Word must contain at least one letter.");
        }
        this.letters = Collections.unmodifiableList(new ArrayList<>(letters));
    }

    public List<Letter> letters() {
        return letters;
    }

    public int length() {
        return letters.size();
    }

    @Override
    public String content() {
        StringBuilder sb = new StringBuilder(letters.size());
        for (Letter l : letters) {
            sb.append(l.value());
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return content();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Word)) {
            return false;
        }
        Word other = (Word) obj;
        return this.content().equalsIgnoreCase(other.content());
    }

    @Override
    public int hashCode() {
        return content().toLowerCase().hashCode();
    }
}
