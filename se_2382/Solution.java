package se_2382;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;

public class Solution {
    public static int N;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("sample_input_2382.txt"));

        int testcaseNumber = scanner.nextInt();

        for(int i = 1; i <= testcaseNumber; i++){
            N = scanner.nextInt();
            int M = scanner.nextInt();
            int K = scanner.nextInt();

            microbe[][] cell = new microbe[N][N];
            ArrayDeque<microbe> microbes = new ArrayDeque<>();

            for(int j = 0 ; j < K; j++){
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                int number = scanner.nextInt();
                int direction = scanner.nextInt();

                cell[x][y] = new microbe(x, y, number, direction);
                microbes.add(new microbe(x, y, number, direction));
            }

            int result = solve(M, cell, microbes);

            System.out.println("#"+i+" "+result);
        }
    }

    public static int solve(int M, microbe[][] cell, ArrayDeque<microbe> microbes){
        for(int i = 0 ; i < M; i++){
            for(int j = 0 ; j < microbes.size(); j++){
                microbe poppedmicrobe = microbes.poll();
                int x = poppedmicrobe.x;
                int y = poppedmicrobe.y;
                int number = poppedmicrobe.number;
                int direction = poppedmicrobe.direction;
                int prev_x = x;
                int prev_y = y;

                if(poppedmicrobe.direction == 1) { //up
                    x -=1;
                }
                else if(poppedmicrobe.direction == 2){    //down
                    x += 1;
                }
                else if(poppedmicrobe.direction == 3){    //left
                    y -= 1;
                }
                else if(poppedmicrobe.direction == 4){    //right
                    y += 1;
                }


                if (x == 0 || x == N-1 || y == 0 || y == N-1){
                    if (direction == 1){
                        direction = 2;
                    }
                    else if (direction == 2){
                        direction = 1;
                    }
                    else if (direction == 3){
                        direction = 4;
                    }
                    else if (direction == 4){
                        direction = 3;
                    }

                    number/= 2;

                }

                if(cell[x][y] != null){

                    cell[x][y] = new microbe(x, y, number, direction);
                    microbes.add(new microbe(x, y, number, direction));

                    if(cell[x][y].number < number){
                        cell[x][y].direction = direction;
                    }
                    cell[x][y].number += number;
                }
                else{
                    cell[x][y] = new microbe(x, y, number, direction);
                    microbes.add(new microbe(x, y, number, direction));
                }
                cell[prev_x][prev_y] = null;
            }
        }
        int result = 0;
        for(int i = 0 ; i < microbes.size(); i++){
            result += microbes.pop().number;
        }

        return result;
    }
}


class microbe{
    public int x;
    public int y;
    public int number;
    public int direction;

    public microbe(int x, int y, int number, int direction) {
        this.x = x;
        this.y = y;
        this.number = number;
        this.direction = direction;
    }
}