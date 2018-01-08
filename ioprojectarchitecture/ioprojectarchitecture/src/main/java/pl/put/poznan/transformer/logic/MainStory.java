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

public class MainStory extends Story {
    private String title;
    private String[] actors;
    private int length;
    private int keyWorldsCount;

    public MainStory() {
        super();
        this.setKeyWorldsCount(0);
    }

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

    public ArrayList<Integer> findDepths(List<String> list) {
        ArrayList<Integer> depths = new ArrayList<>();

        for (String s : list) {
            Integer d = countSpaces(s);
            if (!depths.contains(d)) depths.add(d);
        }
        Collections.sort(depths);
        return depths;
    }

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

    public String transformToPoints(String filename) throws IOException {
        List<String> transform = readFile(filename);
        int newPointDepth, countKeyWorlds = 0;
        Point point, mainPoint = null, curPoint;

        //set tittle, wyodrębnij z nagłówka aktorów czy coś.   /////////////////////////////////////////////////////////////////////////
        setTitle(transform.get(0));
        transform.remove(0);

        //System.out.println(this.getTitle());    /////////////////////////////////////////////////////////////////////////

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
    //needRepair
    public List<String> extractFullStory(){
        return extractToLevel(0, "");
    }

    public List<String> extractToDepth(int depth){
        return extractToLevel(depth,"");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getActors() {
        return actors;
    }

    public void setActors(String[] actors) {
        this.actors = actors;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }


    public int getKeyWorldsCount() {
        return keyWorldsCount;
    }

    public void setKeyWorldsCount(int keyWorldsCount) {
        this.keyWorldsCount = keyWorldsCount;
    }
}
