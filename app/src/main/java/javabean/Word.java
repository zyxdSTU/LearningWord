package javabean;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

public class Word implements Serializable {
    private static final long serialVersionUID = 1L;
    private String word;
    private String wordTranslation;
    private String chapter;
    private String chapterName;

    public Word() {

    }

    public Word(String word, String wordTranslation, String chapter, String chapterName) {
        this.word = word;
        this.wordTranslation = wordTranslation;
        this.chapter = chapter;
        this.chapterName = chapterName;
    }

    public String getWord() {
        return word;
    }

    public String getWordTranslation() {
        return wordTranslation;
    }

    public String getChapter() {
        return chapter;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setWordTranslation(String wordTranslation) {
        this.wordTranslation = wordTranslation;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }
}
