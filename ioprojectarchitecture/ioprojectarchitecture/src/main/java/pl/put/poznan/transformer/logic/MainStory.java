package pl.put.poznan.transformer.logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Klasa reprezentująca scenariusz główny (pierwszy poziom zagłębienia)
 *
 * @see SubStory
 */
public class MainStory extends Story {
    /**
     * Nagłówek scenariusza
     */
    private String title;


    /**
     * Lista aktorów z nagłówka
     */
    private List<String> actors;
    /**
     * Długość wszystkich kroków (także z podscenariuszami)
     */
    private int length;

    private int keyWorldsCount;
    /**
     * Konstruktor klasy
     * @see Story
     */
    public MainStory() {
        super();
        this.setActors(new ArrayList<>());
        this.setKeyWorldsCount(0);
    }

    /**
     * Klasa do akceptacji wizytatora
     * @param v wizytator danej funkcjonalności
     */
    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    /**
     * Metoda wczytująca scenariusz z pliku tekstowego jako listę punktów
     * @param filename Ścieżka do pliku scenariusza
     * @return Przy powodzeniu - listę punktów scenariusza, w przeciwnym razie null
     * @throws IOException Krzaczy się przy braku pliku
     */
    public List readFile(String filename) throws IOException {
        String filePath = new File("").getAbsolutePath();
        BufferedReader in = new BufferedReader(new FileReader(filePath + '/' + filename));

        List<String> list = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(filename))) {
            list = br.lines().collect(Collectors.toList());
            this.setLength(list.size());
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Funkcja obliczająca poziom zagłębienia danej linijki
     * @param s linijka
     * @return liczbę spacji / tabulacji
     */
    public int countSpaces(String s) {
        int spaces = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ' || s.charAt(i) == '\t') {
                spaces++;
            } else
                break;
        }
        return spaces;
    }

    /**
     * Funkcja wypisująca poziomy zagłębień
     *
     * @param list lista linijek wczytanego pliku
     * @return lista numerów
     */
    public ArrayList<Integer> findDepths(List<String> list) {
        ArrayList<Integer> depths = new ArrayList<>();

        for (String s : list) {
            Integer d = countSpaces(s);
            if (!depths.contains(d)) depths.add(d);
        }
        Collections.sort(depths);
        return depths;
    }

    /**
     * Funkcja przetwarzająca tytuł na nagłówek i aktorów
     *
     * @param s nagłówek scenariusza
     */
    //@TODO dorzucić wyjątki
    public void readTitle(String s) {
        String actor = "Aktorzy: ";
        String[] afterSplit;
        actors = new ArrayList<>();
        afterSplit = s.split(actor, -2);

        this.setTitle(afterSplit[0].trim());
        try {
            String[] split2 = afterSplit[1].split(",", -2);

            for (String s2 : split2) {
                if (!s2.equalsIgnoreCase(",")) actors.add(s2.trim());
            }
        } catch (Exception e) {
        }
        ;


    }

    /**
     * Metoda zmieniająca wczytaną listę punktów na poprawne struktury
     * @param filename Ścieżka do pliku ze scenariuszem
     * TODO wywalić, bo to gupie i przekazywać samą listę
     * @return TODO wywalić, bo to gupie
     * @throws IOException wyjatek
     */
    public String transformToPoints(String filename) throws IOException {
        List<String> transform = readFile(filename);

        int newPointDepth, countKeyWorlds = 0;
        ;
        Point point, mainPoint = null, curPoint;
        readTitle(transform.get(0));
        transform.remove(0);
        System.out.println(this.getTitle());    /////////////////////////////////////////////////////////////////////////

        try {


            int prevdepth = 0;
            for (String s : transform) {
                newPointDepth = countSpaces(s) / countSpaces(transform.get(0));
                //System.out.println(newPointDepth);    //////////////////////////////////////////////////////////////////////////
                if (newPointDepth - 1 > prevdepth) {
                    throw new BadFormatException();
                }
                prevdepth = newPointDepth;
                point = new Point(s.trim(), newPointDepth);
                if (point.getText().contains("FOR EACH") || point.getText().contains("ELSE") || point.getText().contains("IF")) {
                    ++countKeyWorlds;
                }

                if (newPointDepth == 1) {
                    mainPoint = point;
                    addToList(mainPoint);
                } else {
                    curPoint = mainPoint;
                    while (newPointDepth != curPoint.getDepth() + 1) {
                        curPoint = curPoint.getSubStory().getPointList().get(curPoint.getSubStory().getPointList().size() - 1);
                    }
                    if (curPoint.getSubStory() == null) {
                        curPoint.setSubStory(new SubStory());
                    }
                    curPoint.getSubStory().addToList(point);
                }
                //System.out.println(newPointDepth+": "+point.getText());   /////////////////////////////////////////////////////////////////////////////////
            }
            this.setKeyWorldsCount(countKeyWorlds);
            System.out.println(this.getKeyWorldsCount());           /////////////////////////////////////////////////////////////////////////
            //System.out.println(this);               /////////////////////////////////////////////////////////////////////////
        } catch (BadFormatException e) {
            System.out.println("Nieprawidłowy format pliku.");
            //e.printStackTrace();
        }
        //System.out.println(this.extractFullStory());    ///////////////////////////////////////////////////////////////////////

        return "Sukces";
    }

    /**
     * Wyodrębnia cały scenariusz
     *
     * @return rekurencyjne wywołanie dla danego poziomu
     */
    public List<String> extractFullStory() {
        return extractToLevel(0, "");
    }


    /**
     * Wyodrębnia scenariusz do danego poziomu
     *
     * @param depth poziom
     * @return rekurencyjne wywołanie dla poziomów
     */
    public List<String> extractToDepth(int depth) {
        return extractToLevel(depth, "");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public List<String> getActors() {
        return actors;
    }

    public int getKeyWorldsCount() {
        return keyWorldsCount;
    }

    public void setKeyWorldsCount(int keyWorldsCount) {
        this.keyWorldsCount = keyWorldsCount;
    }
}
