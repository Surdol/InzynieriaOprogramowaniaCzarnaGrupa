package pl.put.poznan.transformer.logic;

import java.util.ArrayList;
import java.util.List;

public class Story {
    private List<Point> pointList;

    public Story() {
        pointList = new ArrayList<>();
    }

    public void addToList(Point e) {
        pointList.add(e);
    }

    public List<Point> getPointList() {
        return pointList;
    }

    public void setPointList(List<Point> pointList) {
        this.pointList = pointList;
    }

    @Override
    public String toString() {
        return pointList.toString();
    }
}
