package se_2117;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution {
    public static int N;
    public static int M;
    public static int[][] map;
    public static int answer;

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
                    solve(new Position(k, p),1);
                }
            }
        }
    }

    public static void solve(Position center, int K){
        int houseCount = countHouse(center, K);
        int securityCost = K*K +(K-1)*(K-1);
        int cost = securityCost - houseCount*M;

        if (cost < 0){
            return;
        }

        answer = Math.max(houseCount, answer);

        solve(center, K+1);
    }

    public static int countHouse(Position center, int K){
        
        return -1;
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
