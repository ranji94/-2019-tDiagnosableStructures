package pl.structures.run;

import java.io.File;
import java.util.*;
import static pl.structures.engine.ReductionAlgorithm.*;
import static pl.structures.engine.GraphOperations.*;

public class Main {

    public static void main(String[] args) throws java.io.FileNotFoundException{
        Scanner input = new Scanner(new File("src/graph.txt"));
        Scanner scan = new Scanner(System.in);
        System.out.println("Podaj liczbe wierzcholkow grafu: ");
        int num = scan.nextInt();
        System.out.println("Podaj diagnozowalność układu: ");
        int t = scan.nextInt();
        int graf[][] = new int[num][num];
        int optimized[][];

        try {
            dataCorrectnessTest(t,num,input,graf);

            System.out.println("MACIERZ WEJŚCIOWA:");
            printGraph(num,graf);

            optimized = optimizeGraph(graf,t);
            System.out.println("\nMACIERZ OPTYMALNA:");
            printGraph(num, optimized);

            if(isSufficient(optimized,t)){
                System.out.println("Warunek wystarczający zoptymalizowanego grafu jest spełniony.");
            }

            if(isDiagnoseable(optimized,t)){
                System.out.println("Warunek konieczny zoptymalizowanego grafu jest spełniony.");}

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
