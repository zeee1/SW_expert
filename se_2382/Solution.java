package se_2382;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Solution {
    public static int N;
    public static microbe[][] maxValue;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("sample_input_2382.txt"));

        int testcaseNumber = scanner.nextInt();

        for(int i = 1; i <= testcaseNumber; i++){
            N = scanner.nextInt();
            int M = scanner.nextInt();
            int K = scanner.nextInt();
            maxValue = new microbe[N][N];

            microbe[][] cell = new microbe[N][N];
            ArrayDeque<microbe> microbes = new ArrayDeque<>();

            for(int j = 0 ; j < K; j++){
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                int number = scanner.nextInt();
                int direction = scanner.nextInt();

                cell[x][y] = new microbe(x, y, number, direction, 0);
                microbes.add(new microbe(x, y, number, direction, 0));
            }

            int result = solve(M, cell, microbes);

            System.out.println("#"+i+" "+result);
        }
    }

    public static int solve(int M, microbe[][] cell, ArrayDeque<microbe> microbes){
        for(int i = 1 ; i <= M; i++){
            int queueSize = microbes.size();
            for(int j = 0 ; j < queueSize; j++){
                microbe poppedmicrobe = microbes.poll();
                int x = poppedmicrobe.x;
                int y = poppedmicrobe.y;
                int number = poppedmicrobe.number;
                int direction = poppedmicrobe.direction;

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
                    if(cell[x][y].time < i){
                        cell[x][y] = new microbe(x, y, number, direction, i);
                        maxValue[x][y] = new microbe(x, y, number, direction, i);
                        microbes.add(new microbe(x, y, number, direction, i));
                    }
                    else{
                        microbe maxValueMicrobe = maxValue[x][y];
                        if(maxValueMicrobe.number < number){
                            maxValueMicrobe.number = number;
                            maxValueMicrobe.direction = direction;
                            cell[x][y].direction = direction;
                        }
                        cell[x][y].number += number;

                        Iterator<microbe> element = microbes.iterator();
                        while(element.hasNext()){
                            microbe nextElement = element.next();
                            if (nextElement.x == x && nextElement.y == y && nextElement.time == i){
                                nextElement.number = cell[x][y].number;
                                nextElement.direction = cell[x][y].direction;
                                break;
                            }
                        }
                    }


                }
                else{
                    cell[x][y] = new microbe(x, y, number, direction, i);
                    maxValue[x][y] = new microbe(x, y, number, direction, i);
                    microbes.add(new microbe(x, y, number, direction, i));
                }
            }
        }

        int result = 0;
        int queueSize = microbes.size();
        for(int i = 0 ; i < queueSize; i++){
            result += microbes.poll().number;
        }

        return result;
    }
}


class microbe{
    public int x;
    public int y;
    public int number;
    public int direction;
    public int time;

    public microbe(int x, int y, int number, int direction, int time) {
        this.x = x;
        this.y = y;
        this.number = number;
        this.direction = direction;
        this.time = time;
    }
}