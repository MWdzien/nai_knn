import java.util.List;

public class DataEntry {
    String decAttribute;
    List<Double> attributes;

    public DataEntry(String decAttribute, List<Double> attributes) {
        this.decAttribute = decAttribute;
        this.attributes = attributes;
    }
}
