package se_2105;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Solution {
    public static int N;
    public static int[][] map;
    public static Position startPosition;
    public static int[] dx = {1, 1, -1, -1};
    public static int[] dy = {1, -1, -1, 1};
    public static boolean[] numberAppeared;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("sample_input_2105.txt"));
        //Scanner scanner = new Scanner(System.in);

        int testcaseNumber = scanner.nextInt();

        for(int i = 1; i <= testcaseNumber; i++){
            N = scanner.nextInt();

            map = new int[N][N];

            int maxNumber = 0;

            for(int k = 0; k < N; k++){
                for(int p = 0; p < N; p++){
                    map[k][p] = scanner.nextInt();

                    if(maxNumber < map[k][p])
                        maxNumber = map[k][p];
                }
            }


            int maxLength = 0;

            for(int k = 0; k < N; k++){
                for(int p = 1; p < N-1; p++){
                    numberAppeared = new boolean[maxNumber];
                    startPosition = new Position(k, p);

                    int count = solve(new Position(k, p), -1, 0);

                    if(maxLength < count){
                        maxLength = count;
                    }
                }
            }

            if(maxLength == 0)
                maxLength = -1;
            System.out.println("#"+i+" "+maxLength);
        }
    }

    public static int solve(Position pos, int prevDirection, int length){
        int currentValue = map[pos.x][pos.y];
        numberAppeared[currentValue-1] = true;
        int nextX;
        int nextY;
        int directionRange = 0;

        ArrayList<Position> nextPositionList = new ArrayList<>();

        if(prevDirection == -1){
            prevDirection = 0;
            directionRange = 0;
        }else if(prevDirection == 3){
            directionRange = 3;
        }
        else{
            directionRange = prevDirection+1;
        }

        for(int i = prevDirection ; i <= directionRange; i++){
            nextX = pos.x+dx[i];
            nextY = pos.y+dy[i];

            if(nextX == startPosition.x && nextY == startPosition.y){
                return length+1;
            }
            if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= N || numberAppeared[map[nextX][nextY]-1] == true){
                prevDirection+=1;
                continue;
            }

            nextPositionList.add(new Position(nextX, nextY));
        }

        int maxLength = 0;

        for(int i = 0 ; i < nextPositionList.size(); i++){

            int result = solve(nextPositionList.get(i), prevDirection+i, length+1);

            if (result > maxLength){
                maxLength = result;
            }
        }

        numberAppeared[map[pos.x][pos.y]-1] = false;
        return maxLength;
    }
}

class Position{
    public int x;
    public int y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }
}