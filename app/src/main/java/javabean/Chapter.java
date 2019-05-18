package javabean;

public class Chapter {
    private String chapter;
    private String chapterName;
    private String part;

    public Chapter(String chapter, String chapterName, String part) {
        this.chapter = chapter;
        this.chapterName = chapterName;
        this.part = part;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public String getChapter() {
        return chapter;
    }

    public String getChapterName() {
        return chapterName;
    }

    public String getPart() {
        return part;
    }
}
