package se_9280;

import java.util.ArrayDeque;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        int testcaseNumber = scanner.nextInt();

        for(int i = 1; i <= testcaseNumber; i++){
            int n = scanner.nextInt();
            int m = scanner.nextInt();

            int[] R = new int[n];

            for(int j = 0; j < n; j++){
                R[j] = scanner.nextInt();
            }

            int[] W = new int[m];

            for(int j = 0 ;j < m; j++){
                W[j] = scanner.nextInt();
            }

            for(int j = 0 ; j < 2*m; j++){
                int input = scanner.nextInt();
                
            }
        }
    }
}
