package se_1949;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Solution {
    public static int N;
    public static int K;
    public static int[][] map;
    public static boolean[][] isVisit;
    public static int peekHeight = 0;
    public static boolean Kused;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("sample_input_1949.txt"));

        int testcaseNumber = scanner.nextInt();

        for(int i = 1; i <= testcaseNumber ; i++){
            N = scanner.nextInt();
            K = scanner.nextInt();
            Kused = false;

            map = new int[N][N];
            isVisit = new boolean[N][N];

            for(int j = 0 ; j < N ; j++){
                for(int k = 0 ; k < N ; k++){
                    map[j][k] = scanner.nextInt();

                    if(map[j][k] > peekHeight){
                        peekHeight = map[j][k];
                    }
                }
            }
            solve(i);
            peekHeight = 0;
        }

    }

    public static void solve(int index){
        ArrayList<Position> peekList = new ArrayList<>();

        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < N ; j++){
                if(map[i][j] == peekHeight){
                    peekList.add(new Position(i, j));
                }
            }
        }

        int maxAnswer = 0;
        for(int i = 0 ; i < peekList.size(); i++){
            int answer = dfs(peekList.get(i));

            if(answer > maxAnswer){
                maxAnswer = answer;
            }
        }

        System.out.println("#"+index+" "+maxAnswer);
    }

    public static int dfs(Position pos){
        int answer = 1;
        int prevAnswer = 0;
        int currentX = pos.x;
        int currentY = pos.y;
        isVisit[currentX][currentY] = true;

        int[] dx = {-1, 1, 0, 0}; // up, down, left, right
        int[] dy = {0, 0, -1, 1}; //

        for(int i = 0 ; i < 4; i++){
            int tmpAnswer= 1;

            int nextX = currentX + dx[i];
            int nextY = currentY + dy[i];

            if(nextX < 0 || nextY < 0 || nextX == N || nextY == N || isVisit[nextX][nextY] == true){
                continue;
            }

            int tmpAnswer1 = 1;
            if(map[nextX][nextY] < map[currentX][currentY]){
                tmpAnswer1 = dfs(new Position(nextX, nextY))+1;
            }

            int tmpAnswer2 = 1;
            if(Kused == false && map[nextX][nextY] > map[currentX][currentY]-K){
                Kused = true;
                int nextHeight = map[nextX][nextY];
                int maxResult = 0;

                for(int p = 1 ; p <= K ; p++){
                    map[nextX][nextY] -= p;

                    if(map[nextX][nextY] < map[currentX][currentY]) {
                        int a = dfs(new Position(nextX, nextY))+1;
                        maxResult = Math.max(maxResult, a);
                    }

                    map[nextX][nextY] = nextHeight;
                }

                tmpAnswer2 = maxResult;
                Kused = false;
            }

            tmpAnswer = Math.max(tmpAnswer1, tmpAnswer2);
            answer = Math.max(prevAnswer, tmpAnswer);
            prevAnswer = answer;
        }

        isVisit[currentX][currentY] = false;
        return answer;
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