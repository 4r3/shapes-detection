import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TP1 {
    public ArrayList<Flower> jardin = new ArrayList<>();
    public ArrayList<Flower> nearest;

    public static void main(String[] args){
        double res = 0;
        for (int i = 0; i < 150; i++) {
            TP1 t = new TP1();
            res += t.testFor(i,5,1);
        }
        res = res/150;
        System.out.println(res);
    }

    TP1(){
        nearest = new ArrayList<>();
        getJardin();
    }

    public double testFor(int flower,int k,int order){
        System.out.println(jardin.size());
        int i = flower;
        Flower myFlower = jardin.get(i);
        jardin.remove(i);

        KNN knn = new KNN(jardin);
        ArrayList<Flower> res = knn.findNearest(myFlower,k,order);
        return knn.goodGuessRate(myFlower,res);
    }

    public void getJardin(){
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
