public class Ponto {
    private double x;
    private double y;

    private int localArray;

    boolean pertenceCentros = false;
    private double menor_dist;
    public Ponto(double x, double y, int localArray){
        this.x = x;
        this.y = y;
        this.localArray = localArray;
    }

    public double getY() {
        return y;
    }

    public double getX() {
        return x;
    }
    public int getLocalArray(){
        return localArray;
    }

    public void setPertenceCentros(){
        pertenceCentros = true;
    }
    public void setMenor_dist(double dist){
        menor_dist=dist;
    }

    public double getMenor_dist(){
        return menor_dist;
    }
}
