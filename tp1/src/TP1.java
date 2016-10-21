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
        int i = 70;
        Flower myFlower = jardin.get(i);
        jardin.remove(i);
        findNearest(myFlower,5);
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

    public static void findNearest(Flower f,int k){
        nearest.add(jardin.get(0));
        for (int i = 1; i < jardin.size(); i++) {
            double dist = f.distance(jardin.get(i));
            int j = 0;
            while (j < nearest.size() && f.distance(nearest.get(j))<dist){
                j++;
            }

            if(j < k){
                nearest.add(j,jardin.get(i));
                if(nearest.size()>k){
                    nearest.remove(k);
                }
            }
        }
        System.out.println(f);
        for (int i = 0; i < k; i++) {
            System.out.println(nearest.get(i));
        }
    }
}
