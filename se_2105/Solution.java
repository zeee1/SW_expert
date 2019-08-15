package se_2105;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Solution {
    public static int N;
    public static int count;
    public static int[][] map;
    public static Position startPosition;
    public static boolean[][] visited;
    public static int[] dx = {1, 1, -1, -1};
    public static int[] dy = {1, -1, -1, 1};
    public static boolean[] numberAppeared;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);

        int testcaseNumber = scanner.nextInt();

        for(int i = 1; i <= testcaseNumber; i++){
            N = scanner.nextInt();

            map = new int[N][N];
            visited = new boolean[N][N];

            int maxNumber = 0;

            for(int k = 0; k < N; k++){
                for(int p = 0; p < N; p++){
                    map[k][p] = scanner.nextInt();

                    if(maxNumber < map[k][p])
                        maxNumber = map[k][p];
                }
            }

            numberAppeared = new boolean[maxNumber+1];

            count = 0;

            for(int k = 0; k < N; k++){
                for(int p = 0; p < N; p++){
                    startPosition = new Position(k, p);
                    visited[k][p] = true;
                    numberAppeared[map[k][p]] = true;
                    solve(new Position(k, p), 0, 1);
                    visited[k][p] = false;
                    numberAppeared[map[k][p]] = false;
                }
            }
            if(count<4) count = -1;
            System.out.println("#"+i+" "+count);
        }
    }

    public static void solve(Position pos, int prevDirection, int length){
        if(prevDirection == 4)
            return ;

        int nextX = pos.x + dx[prevDirection];
        int nextY = pos.y +dy[prevDirection];

        if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= N){
            return ;
        }

        if(visited[nextX][nextY]||numberAppeared[map[nextX][nextY]]){
            if(nextX == startPosition.x && nextY == startPosition.y)
                count = Math.max(count, length);
            return;
        }

        visited[nextX][nextY] = true;
        numberAppeared[map[nextX][nextY]] = true;

        solve(new Position(nextX, nextY), prevDirection, length+1);
        solve(new Position(nextX, nextY), prevDirection+1, length+1);

        numberAppeared[map[nextX][nextY]] = false;
        visited[nextX][nextY] = false;
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