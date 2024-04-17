import java.util.ArrayList;

public class Distancias {
    private Ponto ponto;
    private ArrayList<Ponto> pontos;
    private double []distancias;
    private int maior;
    private double maiorDistancia = 0;

    public Distancias(int i, ArrayList<Ponto> pontos){
        ponto = pontos.get(i);
        this.pontos=pontos;
        distancias = new double[pontos.size()];
        calcularDistancias();
    }

    public void calcularDistancias(){
        for(int i = 0; i<pontos.size();i++){
            Ponto aux = pontos.get(i);
            distancias[i] = Math.sqrt(Math.pow((ponto.getX()-aux.getX()),2.0)+Math.pow((ponto.getY()-aux.getY()), 2.0));
            if(distancias[i]>maiorDistancia) {
                maiorDistancia = distancias[i];
                maior = i;
            }
        }
    }

    public double pegarDistancia(int i){
        return distancias[i];
    }

    public int getAndReplaceMaior(){
        int aux = maior;
        double auxDistancia = maiorDistancia;
        maiorDistancia = 0;
        for(int i = 0; i<pontos.size();i++){
            if((distancias[i]>maiorDistancia)&&(distancias[i]<auxDistancia)) {
                maior = i;
                maiorDistancia = distancias[i];
            }
        }
        return aux;
    }

    public int getMaior(){
        return maior;
    }
}
