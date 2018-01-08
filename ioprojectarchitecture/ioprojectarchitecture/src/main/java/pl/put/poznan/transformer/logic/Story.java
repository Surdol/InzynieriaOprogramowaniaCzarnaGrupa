package pl.put.poznan.transformer.logic;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa reprezentująca scenariusz.
 */
public abstract class Story {
    /**
     * Lista podpunktów - kolejnych kroków scenariusza.
     *
     * @see Point
     */
    private List<Point> pointList;

    /**
     * Konstruktor klasy tworzący pusty scenariusz
     */
    public Story() {
        pointList = new ArrayList<>();
    }

    /**
     * Metoda dodająca krok na koniec scenariusza
     * @param e krok scenariusza
     */
    public void addToList(Point e) {
        pointList.add(e);
    }


    public List<Point> getPointList() {
        return pointList;
    }


    public void setPointList(List<Point> pointList) {
        this.pointList = pointList;
    }

    /**
     * Metoda służąca do zapisywania tekstu do łańcucha.
     * @return łańcuch z treścią listy
     */
    @Override
    public String toString() {
        return pointList.toString();
    }

    /**
     * Klasa do obsługi wizytatora
     * @param v wizytator dla funkcjonalności
     */
    public abstract void accept(Visitor v);
}
