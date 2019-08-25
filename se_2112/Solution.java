package se_2112;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution {
    public static int D;
    public static int W;
    public static int K;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("sample_input.txt"));
        int testcaseNumber = scanner.nextInt();

        for(int i = 0 ; i < testcaseNumber; i++){
            D = scanner.nextInt();
            W = scanner.nextInt();
            K = scanner.nextInt();

            int[][] filmCell = new int[D][W];

            for(int k = 0 ; k < D ;k++){
                for(int p = 0 ; p < W; p++){
                    filmCell[k][p] = scanner.nextInt();
                }
            }

            solve(filmCell);
        }
    }

    public static void solve(int[][] fileCell){
        int count = 0;
        boolean passOrfail = false;

        while(passOrfail != true)
        {
            
            count += 1;
        }
    }
}
