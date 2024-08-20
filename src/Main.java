import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DataReader dr = new DataReader("iris_training.txt", "iris_test.txt");
        DataClassifier dc = new DataClassifier(dr);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Podaj k");
            int k = Integer.parseInt(scanner.nextLine());
            System.out.println("Aby sprawdzić dokładność klasyfikacji atrybutów z pliku testowego wprowadź 1");
            System.out.println("Aby wprowadzić własny wektor wprowadź 2");
            int response = Integer.parseInt(scanner.nextLine());

            switch (response) {
                case 1:
                    System.out.println(dc.classifyTestFile(k));
                    break;
                case 2:
                    System.out.println("Wprowadź wektor (oddzielając atrybuty tabem)");
                    String s = scanner.nextLine();
                    String[] data = s.split("\t");
                    List<Double> vec = new ArrayList<>();
                    for (String d : data) {
                        d = d.replaceAll(",", ".");
                        d = d.replaceAll(" ", "");
                        vec.add(Double.parseDouble(d));
                    }
                    System.out.println("Podany wektor zaklasyfikowany został jako: " + dc.classify(vec, k));
            }
        }
    }
}