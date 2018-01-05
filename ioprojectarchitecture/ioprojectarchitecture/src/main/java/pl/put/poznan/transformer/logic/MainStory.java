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

    public MainStory() {
        super();
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

        //this.setPointList(new ArrayList<>());

        int spaces, actualDepth = 0, actualSpaces,newPointDepth;
        Story prevStory;
        Point point, mainPoint,curPoint;
        //set tittle, wyodrębnij z nagłówka aktorów czy coś.
        setTitle(transform.get(0));
        transform.remove(0);
        System.out.println(this.getTitle());    /////////////////////////////////////////////////////////////////////////


        prevStory=null;
        mainPoint = null;
        for (String s: transform){
            newPointDepth = countSpaces(s)/4;
            if(newPointDepth==1){
                mainPoint = new Point( s,newPointDepth);
                addToList(mainPoint);
            }
            else{
                curPoint=mainPoint;
                point = new Point( s, newPointDepth);
                while(newPointDepth!=curPoint.getDepth()+1){
                    curPoint=curPoint.getSubStory().getPointList().get(curPoint.getSubStory().getPointList().size()-1);
                }
                if(curPoint.getSubStory()==null){
                    curPoint.setSubStory(new SubStory());
                }
                curPoint.getSubStory().addToList(point);
            }
            System.out.println(newPointDepth+": "+s.substring(newPointDepth*4));   /////////////////////////////////////////////////////////////////////////////////
        }

        System.out.println(this);


        return "Sukces";
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

}
