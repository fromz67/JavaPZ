package org.lab4;

/**
 * A punctuation mark within a sentence (e.g., ',', '.', '?', '!' etc.).
 * Whitespace is NOT modeled as punctuation here.
 */
public final class PunctuationMark implements SentenceElement {
    private final char mark;
    public PunctuationMark(char mark) {
        this.mark = mark;
    }


    public char mark() {
        return mark;
    }

    @Override
    public String content() {
        return String.valueOf(mark);
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
        if (!(obj instanceof PunctuationMark)) {
            return false;
        }
        PunctuationMark other = (PunctuationMark) obj;
        return mark == other.mark;
    }

    @Override
    public int hashCode() {
        return Character.hashCode(mark);
    }
}
