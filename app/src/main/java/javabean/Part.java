package javabean;

public class Part {
    private String part;
    private String partName;

    public Part() {}

    public Part(String part, String partName) {
        this.part = part;
        this.partName = partName;
    }

    public String getPart() {
        return part;
    }

    public String getPartName() {
        return partName;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }
}
