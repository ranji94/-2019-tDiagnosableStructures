package pl.structures.engine;

import com.google.common.collect.Sets;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class ReductionAlgorithm {

    public static int[][] optimizeGraph(int[][] graph, int t){
        for(int k=0;k<graph.length;k++) {
            List<Integer> xAxis = findColumnsToCheck(graph, t);
            List<Integer> y = new ArrayList<>();
            int row = 0;

            for (Integer x : xAxis) {
                for (int i = 0; i < graph.length; i++) {
                    if (graph[i][x] == 1) {
                        y.add(i);
                    }
                }
                row = calculateCut(graph, y);
                y.clear();
                graph[row][x] = 0;
            }
        }
        return graph;
    }

    public static List<Integer> findColumnsToCheck(int[][] graf, int t){
        int sum=0;
        List<Integer> checkList = new ArrayList<>();

        for(int i=0;i<graf.length;i++){ ///WYZEROWANIE DIAGONALII
            graf[i][i]=0;
        }

        for(int j=0;j<graf.length;j++) {    ///ZEBRANIE INFORMACJI O KOLUMNACH MOŻLIWYCH DO ZOPTYMALIZOWANIA
            for (int i = 0; i < graf.length; i++) {
                if(i==j) continue;       //Pominięcie sprawdzania skosów
                if(graf[i][j]==1) sum++; //SPAWDZANIE KOLUMN CZY RÓWNE T
            }
            if(sum>t) checkList.add(j);
            sum=0;
        }

        return checkList;
    }

    public static Integer calculateCut(int[][] graph, List<Integer> y){     //Wyznaczenie indeksu w tablicy, w której wystąpi cięcie
        int max=0;
        int maxIndex=0;
        int tempIndex=0;
        int sum=0;

        for(Integer w : y){
            for(int j=0;j<graph[w].length;j++){
                if(graph[w][j]==1) sum++;
                tempIndex=w;
            }
            if(max<sum){ max=sum; maxIndex=tempIndex; }
            sum=0;
        }
        return maxIndex;
    }

    public static boolean isDiagnoseable( int[][] graf, int t){     //Sprawdzenie warunku koniecznego Hakimiego
        int sum =0;
        for (int j = 0; j < graf.length; j++) {
            for (int i = 0; i < graf.length; i++) {
                if(i==j) continue; //Nie sprawdzamy skosów
                sum=sum+graf[i][j];
            }
            if(sum<t)return false;
            sum=0;
        }
        return true;
    }

    public static boolean isSufficient(int[][] graf, int t){        //Sprawdzenie warunku wystarczającego Amina
        Set<Integer> set = new HashSet<Integer>();
        int sum;
        for(int i=0;i<graf.length;i++){
            set.add(i);
        }
        for(int p=0;p<t;p++){
            int licz=graf.length-2*t+p;
            sum=0;
            Set<Set<Integer>> temp = Sets.combinations(set,licz);
            for (Set<Integer> z: temp) {
                for (int k:z)
                {
                    for(int i=0;i<graf.length;i++){
                        if(k==i)continue;
                        if(!z.contains(i))sum=sum+graf[k][i];
                    }
                }
                if(sum<=p)return false;
            }
        }
        return true;
    }
}
