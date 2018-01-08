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

    //needRepair
    public List<String> extractToLevel(int level, String prevNumber) {
        Integer i = 1;
        List<String> returnString = null;
        if (level == 0) {
            level = Integer.MAX_VALUE;
        }
        for (Point point : this.getPointList()) {
            if (point.getDepth() <= level) {
                returnString.add(prevNumber + "." + i.toString() + point.getText());
                if (point.getSubStory() != null) {
                    for (String str : extractToLevel(level, i.toString())) {
                        returnString.add(str);
                    }
                }
                ++i;
            }

        }

        return returnString;
    }

}
