package pl.put.poznan.transformer.logic;

/**
 * Klasa reprezentująca pojedynczy punkt (linię, wiersz) scenariusza.
 */
public class Point {
    /**
     * Poziom zagłębienia punktu względem pierwszej linii.
     */
    private int depth;
    /**
     * Numer danego wiersza na danej głębokości (w scenariuszu głównym lub w danym podscenariuszu)
     */
    private int number;
    /**
     * Treść punktu.
     */
    private String text;
    /**
     * Opcjonalny podscenariusz dla danego punktu.
     */
    private SubStory subStory;

    /**
     * Konstruktor klasy.
     *
     * @param text  treść podpunktu
     * @param depth głębokość względem pierwszego punktu
     */
    public Point(String text, int depth) {
        this.setText(text.trim());
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

    /**
     * Metoda służąca do możliwego wypisania treści scenariusza.
     * @return Łańcuch z treścią i poziomem głębokości
     */
    @Override
    public String toString() {
        if(this.subStory==null){
            return this.depth+":"+this.text;
        } else{
            return this.depth+":"+this.text+" "+this.subStory;
        }
    }
}
