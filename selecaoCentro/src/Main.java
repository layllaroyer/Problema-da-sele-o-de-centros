import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        ArrayList<Ponto> pontos;
        ArrayList<Ponto> pontosExato;
        ArrayList<Distancias> distancias;
        DistanciasExato distanciasExato;
        ArrayList<Ponto> centros;
        Random random = new Random();
        int n = 100;
        int k = 2;
        long tempoInicial;
        long tempoFinal;

        while(n<=5000) {
            pontos = new ArrayList<>();
            pontosExato = new ArrayList<>();
            distancias = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                pontos.add(new Ponto(random.nextDouble(n), random.nextDouble(n), i));
                pontosExato.add(new Ponto(random.nextDouble(n), random.nextDouble(n), i));
            }

            tempoInicial = System.currentTimeMillis();
            distanciasExato = new DistanciasExato();
            distanciasExato.forca_bruta_csp(pontos, k);
            tempoFinal = System.currentTimeMillis();
            System.out.println("Tempo para algoritmo exato com " + n + " pontos e " + k + " centros: " + (tempoFinal - tempoInicial) + " milissegundos.");
            for (int i=0; i<k;i++){
                System.out.println("Ponto "+i+": x = "+distanciasExato.getBest_centers().get(i).getX()+" y = "+distanciasExato.getBest_centers().get(i).getY());
            }
            System.out.println("");

            tempoInicial = System.currentTimeMillis();
            for (int i = 0; i < n; i++) {
                distancias.add(new Distancias(i, pontos));
            }
            centros = greedyAlgorithm(pontos, k, distancias, distanciasExato.getBest_centers().get(0).getLocalArray());
            tempoFinal = System.currentTimeMillis();
            System.out.println("Tempo para algoritmo aproximado com " + n + " pontos e " + k + " centros: " + (tempoFinal - tempoInicial) + " milissegundos.");
            for (int i=0; i<k;i++){
                System.out.println("Ponto "+i+": x = "+centros.get(i).getX()+" y = "+centros.get(i).getY());
            }
            System.out.println("\n");

            if(n<1000)
                n = n+100;
            else
                n = n+500;
        }
    }

    public static ArrayList<Ponto> greedyAlgorithm(ArrayList<Ponto> pontos, int k, ArrayList<Distancias> distancias, int primeiroCentro){
        ArrayList<Ponto> centros = new ArrayList<>();
        int n = pontos.size();
        centros.add(pontos.get(primeiroCentro));
        pontos.get(primeiroCentro).setPertenceCentros();
        while(centros.size()<k){
            int maior = calcularMaiorDist(centros, pontos, distancias);
            if(maior == -1)
                break;
            centros.add(pontos.get(maior));
            pontos.get(maior).setPertenceCentros();
        }
        return centros;
    }

    public static int calcularMaiorDist(ArrayList<Ponto> centros, ArrayList<Ponto> pontos, ArrayList<Distancias> distancias){
        double mediaMaior = 0;
        int maior = -1;
        for(int i = 0; i< centros.size(); i++){
            int indexPonto = pontos.indexOf(centros.get(i));
            int aux = distancias.get(indexPonto).getMaior();
            if(!pontos.get(aux).pertenceCentros){
                double media =0;
                for(int j = 0; j<centros.size();j++) {
                    int indexPonto2 = pontos.indexOf(centros.get(j));
                    media = media + distancias.get(indexPonto2).pegarDistancia(aux);
                }
                media = media/centros.size();
                if(media>mediaMaior){
                    mediaMaior = media;
                    maior = indexPonto;
                }
            }
        }
        if(maior == -1)
            return -1;
        return distancias.get(maior).getAndReplaceMaior();
    }
}