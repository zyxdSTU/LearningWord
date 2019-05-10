package javabean;

import java.util.List;

public class Word {
    private String wordName;
    private String sceneName;
    private List<String> phaseName;
    private List<String> phaseChName;
    private List<String> sameWordName;
    private List<String> sentenceName;
    private List<String> sentenceChName;

    public Word() {}

    public Word(String wordName, String sceneName, List<String> phaseName, List<String> phaseChName,
                List<String> sameWordName, List<String>sentenceName, List<String>sentenceChName) {
        this.wordName = wordName;
        this.sceneName = sceneName;
        this.phaseName = phaseName;
        this.phaseChName = phaseChName;
        this.sameWordName = sameWordName;
        this.sentenceName = sentenceName;
        this.sentenceChName = sentenceChName;
    }

    public String getWordName() {
        return wordName;
    }

    public String getSceneName() {
        return sceneName;
    }

    public List<String> getPhaseName() {
        return phaseName;
    }

    public List<String> getPhaseChName() {
        return phaseChName;
    }

    public List<String> getSameWordName() {
        return sameWordName;
    }

    public List<String> getSentenceName() {
        return sentenceName;
    }

    public List<String> getSentenceChName() {
        return sentenceChName;
    }

    public void setWordName(String wordName) {
        this.wordName = wordName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }

    public void setPhaseName(List<String> phaseName) {
        this.phaseName = phaseName;
    }

    public void setPhaseChName(List<String> phaseChName) {
        this.phaseChName = phaseChName;
    }

    public void setSameWordName(List<String> sameWordName) {
        this.sameWordName = sameWordName;
    }

    public void setSentenceName(List<String> sentenceName) {
        this.sentenceName = sentenceName;
    }

    public void setSentenceChName(List<String> sentenceChName) {
        this.sentenceChName = sentenceChName;
    }
}
