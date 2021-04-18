package ohm.softa.a05.model;

import ohm.softa.a05.collections.SimpleList;
import ohm.softa.a05.collections.SimpleListImpl;


// Implement a PlantBed which manages a list of plants (use SimpleList<T>)
// A PlantBed may contain any subclass of Plant but nothing else!
// Use appropriate bounds when declaring the generic class.     --> PlantBed<T extends Plant>

public class PlantBed<T extends Plant>{

    private final SimpleList<T> plants;

    public PlantBed(){
        this.plants = new SimpleListImpl<>();
    }

    public void add(T plant){
        plants.add(plant);
    }

    public int size() {
        return plants.size();
    }

    // The method getPlantsByColor is very easy to implement if you think of the filter method of the SimpleList!
    public SimpleList<T> getPlantsByColor(PlantColor color){
        // Usage of "default SimpleList<T> filter(SimpleFilter<T> filter)" in class "SimpleList"
        return plants.filter(p -> p.getColor().equals(color));
    }

    public SimpleList<T> getPlants() {
        // hack to get a copy of the current list
        return plants.map(p -> p);
    }
}
