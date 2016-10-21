import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TP1 {
    public static ArrayList<Flower> jardin = new ArrayList<>();
    public static ArrayList<Flower> nearest;

    public static void main(String[] args){
        nearest = new ArrayList<>();
        getJardin();
        System.out.println(jardin.size());
        int i = 1;
        Flower myFlower = jardin.get(i);
        jardin.remove(i);

        KNN knn = new KNN(jardin);
        ArrayList<Flower> res = knn.findNearest(myFlower,7);
        System.out.println(knn.goodGuessRate(myFlower,res));
    }

    public static void getJardin(){
        String csvFile = "datas/iris.data";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";


        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] f = line.split(cvsSplitBy);

                Flower flower = new Flower(Double.parseDouble(f[0]),
                        Double.parseDouble(f[1]),
                        Double.parseDouble(f[2]),
                        Double.parseDouble(f[3]),
                        f[4]);

                jardin.add(flower);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
