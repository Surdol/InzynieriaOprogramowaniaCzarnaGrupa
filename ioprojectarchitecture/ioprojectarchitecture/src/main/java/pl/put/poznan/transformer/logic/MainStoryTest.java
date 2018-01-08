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
        ms.readTitle("Bibliotekarz dodaje nową pozycję Aktorzy: Bibliotekarz, System");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCountSpaces() {
        assertEquals(2, ms.countSpaces("  Bibliotekarz"));
        assertEquals(4, ms.countSpaces("    Bibliotekarz"));
    }

    @Test
    public void testBaseReadTitle() {
        assertEquals("Bibliotekarz dodaje nową pozycję", ms.getTitle());
    }

    @Test (expected = IOException.class)
    public void testReadFile() throws IOException {
        ms.readFile("randomname.xls");
    }

    @Test
    public void testTrimmingReadTitle() {
        ms.readTitle("Bibliotekarz dodaje nową pozycję     Aktorzy: Bibliotekarz, System");
        assertEquals("Bibliotekarz dodaje nową pozycję", ms.getTitle());
    }

    @Test
    public void testActors() {
        ms.setActors(new ArrayList<String>());
        ms.readTitle("Bibliotekarz dodaje nową pozycję Aktorzy: Bibliotekarz, System    ");
        List<String> testList = new ArrayList<>();
        testList.add("Bibliotekarz");
        testList.add("System");
        assertEquals(testList, ms.getActors());
    }




}

