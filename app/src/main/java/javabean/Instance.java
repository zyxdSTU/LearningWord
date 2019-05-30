package javabean;

import java.io.Serializable;

public class Instance implements Serializable {
    private static final long serialVersionUID = 2L;
    private String word;
    private String wordTranslation;
    private String chapter;
    private String chapterName;
    private String example;
    private String exampleTranslation;

    public Instance(String word, String wordTranslation, String chapter, String chapterName, String example, String exampleTranslation) {
        this.word = word;
        this.wordTranslation = wordTranslation;
        this.chapter = chapter;
        this.chapterName = chapterName;
        this.example = example;
        this.exampleTranslation = exampleTranslation;
    }

    public Instance() {

    }

    public Instance(Word word) {
        this.word = word.getWord();
        this.wordTranslation = word.getWordTranslation();
        this.chapter = word.getChapter();
        this.chapterName = word.getChapterName();
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
