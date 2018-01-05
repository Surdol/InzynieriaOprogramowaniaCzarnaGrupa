package pl.put.poznan.transformer.logic;

public class Point {
    private int depth;
    private int number;
    private String text;
    private SubStory subStory;

    public Point(String text, int depth) {
        this.setText(text);
        this.setDepth(depth);
        this.setSubStory(null);
    }


    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public SubStory getSubStory() {
        return subStory;
    }

    public void setSubStory(SubStory subStory) {
        this.subStory = subStory;
    }

    @Override
    public String toString() {
        return this.subStory+" "+this.depth+":"+this.text;
    }
}
