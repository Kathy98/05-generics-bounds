package ohm.softa.a05.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public abstract class Plant implements Comparable<Plant>{
    private final double height;
    private final String family;
    private final String name;

    protected Plant(double height, String family, String name){
        if(height <= 0.0) throw new IllegalArgumentException("Height may not be less or equal zero");
        if(family == null || family.length() == 0) throw new IllegalArgumentException("Specify a family");
        if(name == null || name.length() == 0) throw new IllegalArgumentException("Specify a name");

        this.height = height;
        this.family = family;
        this.name = name;
    }

    public double getHeight() {
        return height;
    }

    public String getFamily() {
        return family;
    }

    public String getName() {
        return name;
    }

    public abstract PlantColor getColor();

    // Methoden equals, hascode, toString implementieren:
    // siehe auch: https://www.java-blog-buch.de/040311-besondere-methoden-equals-hashcode-und-tostring/
    // https://www.infoworld.com/article/2072488/apache-commons-equalsbuilder-and-hashcodebuilder.html
    public boolean equals(Object obj) {
        if (!(obj instanceof Plant))
            return false;
        if (this == obj)
            return true;
        final Plant otherObject = (Plant) obj;

        // append() takes two arguments to compare; can also compare arrays
        return new EqualsBuilder()
                .append(getHeight(), otherObject.getHeight())
                .append(getFamily(), otherObject.getFamily())
                .append(getName(), otherObject.getName())
                .append(getColor(), otherObject.getColor())
                .isEquals();
    }

    // Die Methode hashCode() berechnet zu dem Objekt, auf dem sie gerufen wird, einen Hash-Code.
    // Der Hash-Code ist ein integraler Wert, der verwendet wird, um Objekte in einem hash-basierten Container
    // abzulegen oder sie in einem solchen Container zu finden.
    // A hashCode which creates a hash from the two unique identifiers
    public int hashCode( ) {
        return new HashCodeBuilder(17, 37)
                .append(getHeight())
                .append(getFamily())
                .append(getName())
                .append(getColor())
                .toHashCode();
    }

    public String toString() {
        return new ToStringBuilder(this)
                .append("family", getFamily())
                .append("name", getName())
                .append("height", getHeight())
                .append("color", getColor())
                .toString();
    }

    //   Flowers compare to each other by height
    @Override
    public int compareTo(Plant plant) {
        return Double.compare(this.getHeight(), plant.getHeight());
    }
}
