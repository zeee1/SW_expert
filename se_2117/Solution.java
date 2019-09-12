package se_2117;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution {
    public static int N;
    public static int M;
    public static int[][] map;
    public static int answer;
    public static int houseCount;
    public static int securityCost;
    public static int cost;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("sample_input_2117.txt"));

        int testcaseNumber = scanner.nextInt();

        for(int i = 1; i <= testcaseNumber; i++){
            N = scanner.nextInt();
            M = scanner.nextInt();
            map = new int[N][N];
            answer = 0;

            for(int k = 0 ; k < N; k++){
                for(int p = 0 ; p < N; p++){
                    map[k][p] = scanner.nextInt();
                }
            }


            for(int k = 0 ; k < N; k++){
                for(int p = 0; p < N; p++){
                    int result = solve(new Position(k, p),1);
                    answer = Math.max(answer, result);
                }
            }

            System.out.println("#"+i+" "+answer);

            if(i == 2)
                break;
        }
    }

    public static int solve(Position center, int K){
        if (K >= N)
            return -1;

        houseCount = countHouse(center, K);
        securityCost = K*K +(K-1)*(K-1);
        cost = houseCount*M-securityCost;

        int subresult = solve(center, K+1);
        
        int result = Math.max(houseCount, subresult);

        return result;
    }

    public static int countHouse(Position center, int K){
        int count = 0;
        int minX = center.x-(K-1);
        int maxX = center.x+(K-1);

        for(int i = minX; i <= maxX; i++){
            int diff = Math.abs(center.x-i);
            for(int j = center.y-(K-1-diff); j <= center.y+(K-1-diff); j++){
                if(i < 0 || i > N-1 ||j < 0 || j > N-1)
                    continue;

                if(map[i][j] == 1){
                    count += 1;
                }
            }
        }

        return count;
    }
}

class Position{
    int x;
    int y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;

    }
}
