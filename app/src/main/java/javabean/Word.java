package javabean;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

public class Word implements Serializable {
    private String word;
    private String wordTranslation;
    private String chapter;
    private String chapterName;
    private String example;
    private String exampleTranslation;

    public Word() {}


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

    public String getExample() {
        return example;
    }

    public String getExampleTranslation() {
        return exampleTranslation;
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

    public void setExample(String example) {
        this.example = example;
    }

    public void setExampleTranslation(String exampleTranslation) {
        this.exampleTranslation = exampleTranslation;
    }
}
