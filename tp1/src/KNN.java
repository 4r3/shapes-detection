import java.util.ArrayList;

public class KNN {
    private double petalLengthMin;
    private double petalLengthMax;
    private double petalWidthMin;
    private double petalWidthMax;
    private double sepalLengthMin;
    private double sepalLengthMax;
    private double sepalWidthMin;
    private double sepalWidthMax;
    private ArrayList<Flower> jardin;

    public KNN(ArrayList<Flower> jardin){
        this.jardin = jardin;

        petalLengthMax = jardin.get(0).petalLength;
        sepalLengthMax = jardin.get(0).sepalLength;
        petalWidthMax = jardin.get(0).petalWidth;
        sepalWidthMax = jardin.get(0).sepalWidth;
        petalLengthMin = petalLengthMax;
        sepalLengthMin = sepalLengthMax;
        petalWidthMin = petalWidthMax;
        sepalWidthMin = sepalWidthMax;

        for(Flower f : jardin){
            petalLengthMax=Math.max(petalLengthMax,f.petalLength);
            sepalLengthMax=Math.max(sepalLengthMax,f.sepalLength);
            petalWidthMax=Math.max(petalWidthMax,f.petalWidth);
            sepalWidthMax=Math.max(sepalWidthMax,f.sepalWidth);

            petalLengthMin=Math.min(petalLengthMin,f.petalLength);
            sepalLengthMin=Math.min(sepalLengthMin,f.sepalLength);
            petalWidthMin=Math.min(petalWidthMin,f.petalWidth);
            sepalWidthMin=Math.min(sepalWidthMin,f.sepalWidth);
        }
    }

    public ArrayList<Flower> findNearest(Flower f,int k){
        Flower fNorm = normalize(f);
        ArrayList<Flower> nearest = new ArrayList<>();
        nearest.add(jardin.get(0));
        for (int i = 1; i < jardin.size(); i++) {
            double dist = manathanDistance(fNorm,normalize(jardin.get(i)));
            int j = 0;
            while (j < nearest.size() && manathanDistance(fNorm,normalize(nearest.get(j)))<dist){
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
        return nearest;
    }

    private double manathanDistance(Flower f1, Flower f2){
        double dist = 0;
        dist += Math.abs(f1.petalLength-f2.petalLength);
        dist += Math.abs(f1.petalWidth-f2.petalWidth);
        dist += Math.abs(f1.sepalLength-f2.sepalLength);
        dist += Math.abs(f1.sepalWidth-f2.sepalWidth);
        return dist;
    }

    private double minkowskiDistance(Flower f1, Flower f2,int k){
        double dist = 0;
        dist += Math.pow(f1.petalLength-f2.petalLength,k);
        dist += Math.pow(f1.petalWidth-f2.petalWidth,k);
        dist += Math.pow(f1.sepalLength-f2.sepalLength,k);
        dist += Math.pow(f1.sepalWidth-f2.sepalWidth,k);
        return Math.pow(dist,1.0/k);
    }

    private Flower normalize(Flower f){
        return new Flower(normalize(f.sepalLength,sepalLengthMin,sepalLengthMax),
                normalize(f.sepalWidth,sepalWidthMin,sepalWidthMax),
                normalize(f.petalLength,petalLengthMin,petalLengthMax),
                normalize(f.petalWidth,petalWidthMin,sepalWidthMax),null);
    }

    private double normalize(double data,double min,double max){
        return (data-min)/(max-min);
    }

    public double goodGuessRate(Flower f, ArrayList<Flower> res){
        double gg = 0;
        for(Flower r : res){
            if(r.type==f.type){
                gg++;
            }
        }
        return 100*gg/res.size();
    }
}
