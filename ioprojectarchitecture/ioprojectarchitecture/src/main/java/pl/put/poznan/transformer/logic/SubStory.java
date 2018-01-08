package pl.put.poznan.transformer.logic;

/**
 * Klasa reprezentująca podscenariusz.
 */
public class SubStory extends Story {

    /**
     * Konstruktor klasy.
     *
     * @see Story
     */
    public SubStory() {
        super();
    }

    /**
     * @param v
     */
    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
