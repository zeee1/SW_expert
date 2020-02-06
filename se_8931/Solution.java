package se_8931;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("sample_input_8931.txt"));

        int testcaseNumber = scanner.nextInt();

        for(int i = 1; i <= testcaseNumber ;i++){
            int K = scanner.nextInt();

            Stack<Integer> stack = new Stack<>();
            int sum = 0;
            for(int j = 0 ; j < K ; j++){
                int input = scanner.nextInt();

                if(input == 0 ){
                    int pop = stack.pop();
                    sum -= pop;
                }else{
                    stack.push(input);
                    sum += input;
                }
            }

            System.out.println("#"+i+" "+sum);
        }
    }
}
