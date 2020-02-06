package se_9088;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("sample_input_9088.txt"));

        int testcaseNumber = scanner.nextInt();

        for(int i = 1; i <= testcaseNumber; i++){
            int N = scanner.nextInt();
            int K = scanner.nextInt();

            int[] diamondArray = new int[N];

            for(int j = 0 ; j < N; j++){
                diamondArray[j] = scanner.nextInt();
            }

            Arrays.sort(diamondArray);
            int answer = 0;


            for(int j = 0 ; j < N-1; j++){
                int count = 1;
                int j_dia = diamondArray[j];

                for(int k = j+1; k < N ; k++){
                    if((diamondArray[k]-j_dia) <= K){
                        count +=1;
                    }
                }

                if(answer < count){
                    answer = count;
                }
            }

            System.out.println("#"+i+" "+answer);
        }
    }
}
