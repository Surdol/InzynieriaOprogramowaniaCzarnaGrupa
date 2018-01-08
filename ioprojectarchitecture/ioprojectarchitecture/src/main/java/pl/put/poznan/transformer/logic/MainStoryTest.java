package pl.put.poznan.transformer.logic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MainStoryTest {
    MainStory ms = null;
    @Before
    public void setUp() throws Exception {
        ms = new MainStory();
        ms.readTitle("Kupa Aktorzy: gunwiany, test");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCountSpaces() {
        assertEquals("Git", 2, ms.countSpaces("  X"));
        assertEquals("Git", 4, ms.countSpaces("    X"));
    }

    @Test
    public void testBaseReadTitle() {
        assertEquals("Kupa", ms.getTitle());
    }

    @Test (expected = IOException.class)
    public void testReadFile() throws IOException {
        ms.readFile("randomname.xls");
    }

    @Test
    public void testTrimmingReadTitle() {
        ms.readTitle("Kupa dupa kupa dupa     Aktorzy: gunwiany, test");
        assertEquals("Kupa dupa kupa dupa", ms.getTitle());
    }

    @Test
    public void testActors() {
        ms.setActors(new ArrayList<String>());
        ms.readTitle("Co za chujowy test Aktorzy: gunwo, io smierdzi    ");
        List<String> testList = new ArrayList<>();
        testList.add("gunwo");
        testList.add("io smierdzi");
        assertEquals(testList, ms.getActors());
    }




}

