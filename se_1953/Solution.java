package se_1953;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Solution {
    public static int count;
    public static int[][] map;
    public static boolean[][] visited;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("sample_input_1953.txt"));
        //Scanner scanner = new Scanner(System.in);
        int testcaseNumber = scanner.nextInt();

        for(int i = 1 ; i <= testcaseNumber; i++){
            int N = scanner.nextInt();
            int M = scanner.nextInt();
            int R = scanner.nextInt();
            int C = scanner.nextInt();
            int L = scanner.nextInt();

            map = new int[N][M];
            visited = new boolean[N][M];

            for(int k = 0 ; k < N; k++){
                for(int p = 0; p < M; p++){
                    map[k][p] = scanner.nextInt();
                }
            }

            Position startPos = new Position(R, C);
            count = 0;

            countAvailablePosition(startPos, L-1, N, M);
            System.out.println("#"+i+" "+count);
        }
    }

    public static void countAvailablePosition(Position pos, int restTime, int N, int M){
        if(visited[pos.x][pos.y] == false)
            count += 1;

        visited[pos.x][pos.y] = true;

        if(restTime == 0){
            return;
        }

        ArrayList<Position> nextPositionList = new ArrayList<>();

        int value = map[pos.x][pos.y];

        if(value == 1){
            addUpCell(nextPositionList, pos, N, M);
            addDownCell(nextPositionList, pos,N, M);
            addLeftCell(nextPositionList, pos, N, M);
            addRightCell(nextPositionList, pos, N, M);

        }
        else if(value == 2){
            addUpCell(nextPositionList, pos, N, M);
            addDownCell(nextPositionList, pos, N, M);
        }
        else if(value == 3){
            addLeftCell(nextPositionList, pos, N, M);
            addRightCell(nextPositionList, pos,N, M);
        }
        else if(value == 4){
            addUpCell(nextPositionList, pos,N, M);
            addRightCell(nextPositionList, pos, N, M);
        }
        else if(value == 5){
            addDownCell(nextPositionList, pos, N, M);
            addRightCell(nextPositionList, pos, N, M);
        }
        else if(value == 6){
            addDownCell(nextPositionList, pos, N, M);
            addLeftCell(nextPositionList, pos, N, M);
        }
        else if(value == 7){
            addUpCell(nextPositionList, pos, map.length, map[0].length);
            addLeftCell(nextPositionList, pos, map.length, map[0].length);
        }

        for(int i = 0 ; i < nextPositionList.size(); i++){
            Position nextPos = nextPositionList.get(i);
            countAvailablePosition(nextPos, restTime-1,N, M);
        }

    }

    public static void addUpCell(ArrayList<Position> arrayList, Position currentPos, int xMax, int yMax){
        int nextX = currentPos.x -1;
        int nextY = currentPos.y;

        if(nextX < 0 || nextX == xMax || nextY < 0 || nextY == yMax)
            return ;

        if(map[nextX][nextY] == 5 || map[nextX][nextY] == 6 || map[nextX][nextY] == 2 || map[nextX][nextY] == 1)
            arrayList.add(new Position(nextX,nextY)); // up
    }

    public static void addDownCell(ArrayList<Position> arrayList, Position currentPos, int xMax, int yMax){
        int nextX = currentPos.x + 1;
        int nextY = currentPos.y;

        if(nextX < 0 || nextX == xMax || nextY < 0 || nextY == yMax)
            return ;

        if(map[nextX][nextY] == 4 || map[nextX][nextY] == 7 || map[nextX][nextY] == 2 || map[nextX][nextY] == 1){
            arrayList.add(new Position(nextX,nextY)); // up
        }

    }

    public static void addLeftCell(ArrayList<Position> arrayList, Position currentPos, int xMax, int yMax){
        int nextX = currentPos.x;
        int nextY = currentPos.y-1;

        if(nextX < 0 || nextX == xMax || nextY < 0 || nextY == yMax)
            return ;

        if(map[nextX][nextY] == 4 || map[nextX][nextY] == 5 || map[nextX][nextY] == 3 || map[nextX][nextY] == 1)
            arrayList.add(new Position(nextX,nextY)); // up
    }

    public static void addRightCell(ArrayList<Position> arrayList, Position currentPos, int xMax, int yMax){
        int nextX = currentPos.x;
        int nextY = currentPos.y+1;

        if(nextX < 0 || nextX == xMax || nextY < 0 || nextY == yMax)
            return ;

        if(map[nextX][nextY] == 6 || map[nextX][nextY] == 7 || map[nextX][nextY] == 3 || map[nextX][nextY] == 1)
            arrayList.add(new Position(nextX,nextY)); // up
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
