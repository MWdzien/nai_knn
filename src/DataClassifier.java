
import java.util.*;

public class DataClassifier {

    DataReader dr;

    public DataClassifier(DataReader dr) {
        this.dr = dr;
    }

    public String classify(List<Double> attributes, int k){
        List<Pair<Double, String>> distances = new ArrayList<>();
        for (DataEntry trainingEntry : dr.trainingData){
            double dist = 0;
            for (int i = 0; i < trainingEntry.attributes.size(); i++) {
                dist += Math.pow(trainingEntry.attributes.get(i) - attributes.get(i), 2);
            }
            dist = Math.sqrt(dist);
            distances.add(new Pair<>(dist, trainingEntry.decAttribute));
        }

        distances.sort(Comparator.comparing(x -> x.val1));

        TreeMap<String, Integer> results = new TreeMap<>();


        int compared = 0;
        boolean top1Exists = false;
        while (compared < k || !top1Exists){
            results.putIfAbsent(distances.get(0).val2, 0);

            results.put(distances.get(0).val2, results.get(distances.get(0).val2)+1);
            distances.remove(distances.get(0));
            compared++;
            if (results.lastEntry().getValue().equals(results.floorEntry(results.lastKey()).getValue())) top1Exists = true;
        }

        return results.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();

    }

    public String classifyTestFile(int k){
        int correct = 0;
        for (DataEntry testEntry : dr.testData){
            if (testEntry.decAttribute.equals(classify(testEntry.attributes, k))) correct++;
        }
        int acc = correct*100/dr.testData.size();
        return "Ilość prawidłowo zaklasyfikowanych przykładów dla k = " + k + " to: " + correct + " (" + acc + "% dokładności)";
    }
}


