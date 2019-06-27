package pl.structures.engine;
import static pl.structures.engine.ReductionAlgorithm.*;
import java.util.Scanner;

public abstract class GraphOperations {

    public static void dataCorrectnessTest(int t, int num, Scanner input, int[][] graf) {
        int sum = 0;
        if (2 * t >= num) {
            System.out.println("Układ nie spełnia warunku koniecznego.");
            System.exit(0);
        }
            for (int i = 0; i < num; i++) {
                for (int j = 0; j < num; j++) {
                    if (!(input.hasNextLine())) {
                        System.out.println("Graf wejściowy zawiera za mało dnych");
                        System.exit(0);
                    }
                    int n = input.nextInt();
                    if ((n == 0) || (n == 1)) {
                        graf[i][j] = n;
                        sum++;
                    } else {
                        System.out.println("Błędne dane wejściowe w grafie.");
                        System.exit(0);
                    }
                }
            }
            if (input.hasNext()) {
                System.out.println("Graf wejściowy zawiera za dużo danych");
                System.exit(0);
            }

            if (!isDiagnoseable(graf, t)) {
                System.out.println("Warunek konieczny nie spelniony. System nie jest " + t + "-diagnozowalny!");
                System.exit(0);
            } else if (!isSufficient(graf, t)) {
                System.out.println("Warunek wystarczający nie spelniony. System nie jest " + t + "-diagnozowalny!");
                System.exit(0);
            } else System.out.println("Waruneki spelnione. System jest " + t + "-diagnozowalny!");
            System.out.print("\n");
    }

    public static void printGraph(int num, int[][] graf){
        for(int i=0;i<num;i++) {
            for(int j=0;j<num;j++){
                System.out.print(graf[i][j]+" ");
            }
            System.out.println("");
        }
    }
}
