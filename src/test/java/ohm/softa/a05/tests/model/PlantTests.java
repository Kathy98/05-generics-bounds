package ohm.softa.a05.tests.model;

import ohm.softa.a05.model.Flower;
import ohm.softa.a05.model.Plant;
import ohm.softa.a05.model.PlantColor;
import ohm.softa.a05.model.Shrub;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlantTests {
    ///// FLOWERS /////
    @Test
    void testCreateGreenFlower() {
        assertThrows(IllegalArgumentException.class, () -> new Flower(0.5, "Korbblütler", "Sonnenblume", PlantColor.GREEN));
    }

    @Test
    void testCreateFlowerWithEmptyFamily() {
        assertThrows(IllegalArgumentException.class, () -> new Flower(0.5, null, "Sonnenblume", PlantColor.YELLOW));
    }

    @Test
    void testCreateFlowerWithEmptyName() {
        assertThrows(IllegalArgumentException.class, () -> new Flower(0.5, "Korbblütler", null, PlantColor.YELLOW));
    }

    @Test
    void testCreateFlowerWithNegativeHeight() {
        assertThrows(IllegalArgumentException.class, () -> new Flower(-0.5, "Korbblütler", "Sonnenblume", PlantColor.YELLOW));
    }

    @Test
    void testGetCorrectColor() {
        Flower f = new Flower(0.5, "Korbblütler", "Sonnenblume", PlantColor.YELLOW);
        assertEquals(PlantColor.YELLOW, f.getColor());
    }

    @Test
    void testGetCorrectFlowerHeight() {
        Flower f = new Flower(0.5, "Korbblütler", "Sonnenblume", PlantColor.YELLOW);
        assertEquals(0.5, f.getHeight(), 0.0000001);
    }

    @Test
    void testGetCorrectFlowerName() {
        Flower f = new Flower(0.5, "Korbblütler", "Sonnenblume", PlantColor.YELLOW);
        assertEquals("Sonnenblume", f.getName());
    }

    @Test
    void testGetCorrectFlowerFamily() {
        Flower f = new Flower(0.5, "Korbblütler", "Sonnenblume", PlantColor.YELLOW);
        assertEquals("Korbblütler", f.getFamily());
    }

    @Test
    void testSortFlowers() {
        List<Plant> flowers = new LinkedList<>();

        flowers.add(new Shrub(3.5, "Buchsbaumgewächse", "Buchsbaum"));
        flowers.add(new Flower(1.1, "Korbblütler", "Sonnenblume", PlantColor.YELLOW));
        flowers.add(new Shrub(5.5, "Buchsbaumgewächse", "Buchsbaum"));
        flowers.add(new Flower(0.5, "Korbblütler", "Sonnenblume", PlantColor.YELLOW));
        flowers.add(new Shrub(1.5, "Buchsbaumgewächse", "Buchsbaum"));
        flowers.add(new Flower(0.75, "Korbblütler", "Sonnenblume", PlantColor.YELLOW));
        flowers.add(new Shrub(4.5, "Buchsbaumgewächse", "Buchsbaum"));
        flowers.add(new Flower(2.5, "Korbblütler", "Sonnenblume", PlantColor.YELLOW));

        // Syntax: <Class name>::<method name>
        //Bsp.:
        // stream.forEach( s-> System.out.println(s));
        // stream.forEach( System.out::println(s));
        flowers.sort(Plant::compareTo); // Sortieren Pflanzen nach Höhe
        double lastHeight = 0.0;
        for(Plant f : flowers){
            assertTrue(f.getHeight() > lastHeight);
            lastHeight = f.getHeight();
        }
    }
    
    ////// SHRUB //////
    @Test
    void testGetColor() {
        assertEquals(PlantColor.GREEN, new Shrub(1.5, "Buchsbaumgewächse", "Buchsbaum").getColor());
    }

    @Test
    void testCreateShrubWithEmptyFamily() {
        assertThrows(IllegalArgumentException.class, () -> new Shrub(1.5, null, "Buchsbaum"));
    }

    @Test
    void testCreateShrubWithEmptyName() {
        assertThrows(IllegalArgumentException.class, () -> new Shrub(1.5, "Buchsbaumgewächse", null));
    }

    @Test
    void testCreateShrubWithNegativeHeight() {
        assertThrows(IllegalArgumentException.class, () -> new Shrub(-1.5, "Buchsbaumgewächse", "Buchsbaum"));
    }

    @Test
    void testGetCorrectShrubHeight() {
        Shrub s = new Shrub(1.5, "Buchsbaumgewächse", "Buchsbaum");
        assertEquals(1.5, s.getHeight(), 0.0000001);
    }

    @Test
    void testGetCorrectShrubName() {
        Shrub s = new Shrub(1.5, "Buchsbaumgewächse", "Buchsbaum");
        assertEquals("Buchsbaum", s.getName());
    }

    @Test
    void testGetCorrectShrubFamily() {
        Shrub s = new Shrub(1.5, "Buchsbaumgewächse", "Buchsbaum");
        assertEquals("Buchsbaumgewächse", s.getFamily());
    }
}