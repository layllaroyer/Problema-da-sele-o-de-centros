import java.util.ArrayList;

public class DistanciasExato{
    private ArrayList<Ponto> best_centers;

    public double  calcularDistancias(Ponto p1, Ponto p2){
        double x1=p1.getX();
        double y1=p1.getY();
        double x2=p2.getX();
        double y2=p2.getY();
        return Math.sqrt(Math.pow((x2-x1),2.0)+Math.pow((y2-y1), 2.0));
    }
    public double total_distance(ArrayList<Ponto> pontos,  ArrayList<Ponto> centros) {
        double total_dist = 0;
        for(Ponto p :pontos) {
            p.setMenor_dist(Double.POSITIVE_INFINITY);
            for (Ponto center : centros) {
                double dist = calcularDistancias(p, center);
                if (dist < p.getMenor_dist()) {
                    p.setMenor_dist(dist);
                }
            }
            total_dist += p.getMenor_dist();
        }
        return total_dist;
    }

    public ArrayList<ArrayList<Ponto>> combinations(ArrayList<Ponto> points, int k) {
        ArrayList<ArrayList<Ponto>> result = new ArrayList<>();
        generateCombinations(points, k, new ArrayList<>(), 0, result);
        return result;
    }

    private static void generateCombinations(ArrayList<Ponto> points, int k, ArrayList<Ponto> current, int start, ArrayList<ArrayList<Ponto>> result) {
        if (current.size() == k) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (int i = start; i < points.size(); i++) {
            current.add(points.get(i));
            generateCombinations(points, k, current, i + 1, result);
            current.remove(current.size() - 1);
        }
    }

    public double forca_bruta_csp(ArrayList<Ponto> pontos, int k) {
        double best_distance =Double.POSITIVE_INFINITY;
        ArrayList<ArrayList<Ponto>> combinations = combinations(pontos, k);
        for (ArrayList<Ponto> combination : combinations) {
            double currentDistance = total_distance(pontos,combination);
            if (currentDistance < best_distance) {
                best_centers = combination;
                best_distance = currentDistance;
            }
        }
        return best_distance;
    }

    public ArrayList<Ponto> getBest_centers() {
        return best_centers;
    }

}
