package ohm.softa.a05.utils;

import ohm.softa.a05.collections.SimpleList;
import ohm.softa.a05.collections.SimpleListImpl;
import ohm.softa.a05.model.Plant;
import ohm.softa.a05.model.PlantBed;
import ohm.softa.a05.model.PlantColor;

import java.util.HashMap;
import java.util.Map;

// Why should this class be declared abstract and have a private constructor?
// abstract: Weil...
// private constructor: Weil...

// Utility Klassen sind Java Klassen, welche lediglich Methoden und Konstanten anbieten.
// Beispiel: Die Klasse Math ist so eine klassische Utility Klasse.
public abstract class PlantBedUtility{

    private PlantBedUtility(){}

    // 2. Implement the utility method splitBedByColor in a utility class PlantBedUtility.
    // Map mit key = PlantColor und value = SimleList<T>
    // UML: public <T> Map<PlantColor, SimpleList<T>> splitBedByColor(PlantBed<T> bed)
    // "<T extends Plant>" wegen Eingabeparameter "PlantBed<T> bed"
    // -> T muss eine Klasse sein, die von Plant erbt (also Flower oder Shrub)

    public static <T extends Plant> Map<PlantColor, SimpleList<T>> splitBedByColor(PlantBed<T> plantBed) {
        Map<PlantColor, SimpleList<T>> result = new HashMap<>();

        for (T plant : plantBed.getPlants()) {
            /* Wenn das Ergebnis die Pflanzenfarbe nicht enthält, füge die Farbe und eine neue leere Liste ein */
            if (!result.containsKey(plant.getColor())) {
                result.put(plant.getColor(), new SimpleListImpl<>());
            }
            /* Erhalte die entsprechende Liste und füge die aktuelle Pflanze ein */
            result.get(plant.getColor()).add(plant);
        }
        return result;
    }

    //shortest variant
    public static <T extends Plant> Map<PlantColor, SimpleList<? extends T>>
    splitBedByColor2(PlantBed<? extends T> plantBed) {
        Map<PlantColor, SimpleList<? extends T>> result = new HashMap<>();
        /* Iteriere über die Enum Werte */
        for (PlantColor color : PlantColor.values()) {
            /* Erhalte alle Pflanzen der aktuellen Farbe (nutze Methode getPlantsByColor von Klasse PlantBed) */
            result.put(color, plantBed.getPlantsByColor(color));
        }
        return result;
    }

}
