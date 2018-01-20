package pl.put.poznan.transformer.logic;

/**
 * Klasa do obsługi dodatkowych funkcjonalności z wykorzystaniem wzorca projektowego
 */
public interface Visitor {
    /**
     * Metoda odwiedzająca główny scenariusz
     * @param st główny scenariusz
     */
    public void visit(MainStory st);

    /**
     * Metoda odwiedzająca podscenariusz
     * @param sb podscenariusz
     */
    public void visit(SubStory sb);

}
