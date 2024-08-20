import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataReader {
    List<DataEntry> trainingData;
    List<DataEntry> testData;
    public DataReader(String trainingFilePath, String testFilePath) {
        this.trainingData = new ArrayList<>();
        this.testData = new ArrayList<>();
        readFile(trainingFilePath, trainingData);
        readFile(testFilePath, testData);
    }

    private void readFile(String filePath, List<DataEntry> dataList){
        File file = new File(filePath);

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;
            while ((line = br.readLine()) != null){
                List<Double> nums = new ArrayList<>();

                line = line.replaceAll(",", ".");
                line = line.replaceAll(" ", "");
                String[] data = line.split("\t");
                for (int i = 0; i < data.length - 1; i++){
                    nums.add(Double.parseDouble(data[i]));
                }

                DataEntry de = new DataEntry(data[data.length-1].replaceAll(" ", ""), nums);

                dataList.add(de);
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
