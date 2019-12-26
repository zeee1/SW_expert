package se_9229;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("sample_input_9229.txt"));

        int testcaseNumber = scanner.nextInt();

        for(int i = 1; i <= testcaseNumber; i++){
            int N = scanner.nextInt();
            int M = scanner.nextInt();
            int answer = 0;

            int[] weights = new int[N];

            for(int j = 0 ; j < N ; j++){
                weights[j] = scanner.nextInt();
            }

            int mindiff = Integer.MAX_VALUE;

            for(int j = 0 ; j < N ; j++){
                for(int k = j; k < N ; k++){
                    if(j == k){
                        continue;
                    }

                    int sum = weights[j]+weights[k];
                    int diff = M - sum;

                    if(diff < 0){
                        continue;
                    }

                    if(diff < mindiff){
                        mindiff = diff;
                        answer = sum;
                    }
                }
            }

            if(answer== 0){
                answer = -1;
            }

            System.out.println("#"+i+" "+answer);
        }

    }
}
