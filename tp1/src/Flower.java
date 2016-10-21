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
        if(type == null){
            this.type = null;
        } else if(type.contains("virginica")){
            this.type = FlowerType.IrisVirginica;
        }else if (type.contains("versicolor")){
            this.type = FlowerType.IrisVersicolour;
        }else if (type.contains("setosa")){
            this.type = FlowerType.IrisSetosa;
        }else{
            this.type = null;
        }
    }

    @Override
    public String toString() {
        return "sL: " + sepalLength + " sW: " + sepalWidth + " pL: "+ petalLength +" pW: " + petalWidth + ", type: " + type;
    }
}
