package se_4014;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("sample_input_4014.txt"));

        int testcaseNum = scanner.nextInt();

        for(int i = 1; i <= testcaseNum; i++){
            int N = scanner.nextInt();
            int X = scanner.nextInt();

            int[][] map = new int[N][N];

            for(int k = 0 ; k < N; k++){
                for(int p = 0; p < N; p++){
                    map[k][p] = scanner.nextInt();
                }
            }

            int result = solve(map, X);
            System.out.println("#"+i+" "+result);
        }
    }

    public static int solve(int[][] map, int X){
        int count = 0;

        for(int i = 0 ; i < map.length; i++){
            int duration = 1;
            boolean flag = true;

            for(int j = 1; j < map.length; j++){
                int diff = map[i][j]-map[i][j-1];

                if(Math.abs(diff) >= 2){
                    flag = false;
                    break;
                }
                else if(diff == 0){
                    duration += 1;
                }
                else if(diff == 1 && duration < X){
                    flag = false;
                    break;
                }
                else if(diff == 1){
                    duration = 1;
                }
                else if(diff == -1){
                    for(int k = j ; k < j+X; k++){
                        if(k >= map.length || map[i][k] != map[i][j])
                        {
                            flag = false;
                            break;
                        }
                    }
                    if(!flag)
                        break;
                    else{
                        j = j+X-1;
                        duration =1;
                    }
                }
            }

            if(flag){
                count += 1;
            }
        }

        for(int i = 0 ; i < map.length; i++){
            int[] tmp = new int[map.length];

            for(int j = 0; j < map.length ; j++){
                tmp[j] = map[j][i];
            }

            int duration = 1;
            boolean flag = true;

            for(int j = 1; j < map.length; j++){
                int diff = tmp[j]-tmp[j-1];

                if(Math.abs(diff) >= 2){
                    flag = false;
                    break;
                }
                else if(diff == 0){
                    duration += 1;
                }
                else if(diff == 1 && duration < X){
                    flag = false;
                    break;
                }
                else if(diff == 1){
                    duration  = 1;
                }
                else if(diff == -1){
                    for(int k = j ; k < j+X; k++){
                        if(k >= map.length || tmp[j] != tmp[k])
                        {
                            flag = false;
                            break;
                        }
                    }
                    if(!flag)
                        break;
                    else{
                        j = j+X-1;
                        duration =1;
                    }
                }
            }

            if(flag){
                count += 1;
            }
        }

        return count;
    }
}
