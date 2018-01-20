package pl.put.poznan.transformer.logic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SubStoryTest {
    SubStory s;
    @Before
    public void setUp() throws Exception {
        s = new SubStory();

    }

    @Test
    public void testAddToList() throws Exception {
        Point p = new Point("Test", 2);
        s.addToList(p);
        assertTrue(s.getPointList().contains(p));
    }

    @Test
    public void testEmptyPoint() throws Exception {

    }

}