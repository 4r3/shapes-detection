public class Flower {
    double sepalLength;
    double sepalWidth;
    double petalLength;
    double petalWidth;
    FlowerType type;

    public Flower(double sepalLength, double sepalWidth, double petalLength, double petalWidth, String type) {
        this.sepalLength = sepalLength;
        this.sepalWidth = sepalWidth;
        this.petalLength = petalLength;
        this.petalWidth = petalWidth;
        if(type.contains("virginica")){
            this.type = FlowerType.IrisVirginica;
        }else if (type.contains("versicolor")){
            this.type = FlowerType.IrisVersicolour;
        }else{
            this.type = FlowerType.IrisSetosa;
        }
    }

    public double distance(Flower f){
        double dist = 0;
        dist += Math.abs(f.petalLength-petalLength);
        dist += Math.abs(f.petalWidth-petalWidth);
        dist += Math.abs(f.sepalLength-sepalLength);
        dist += Math.abs(f.sepalWidth-sepalWidth);

        return dist;
    }

    @Override
    public String toString() {
        return "sL: " + sepalLength + " sW: " + sepalWidth + " pL: "+ petalLength +" pW: " + petalWidth + ", type: " + type;
    }
}
