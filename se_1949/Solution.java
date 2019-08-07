package se_1949;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Solution {
    public static int K = 0;
    public static boolean Kused = false;
    public static int[][] map;
    public static boolean[][] visited;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("sample_input_1949.txt"));

        int testcaseNumber = scanner.nextInt();

        for (int i = 0; i < testcaseNumber; i++) {
            int N = scanner.nextInt();
            K = scanner.nextInt();

            map = new int[N][N];
            visited = new boolean[N][N];
            int peekHeight = 0;

            for (int j = 0; j < N; j++) {
                for (int p = 0; p < N; p++) {
                    map[j][p] = scanner.nextInt();

                    if (peekHeight < map[j][p]) {
                        peekHeight = map[j][p];
                    }
                }
            }

            solve(i, map, visited, N, K, peekHeight);
        }
    }

    public static void solve(int index, int[][] map, boolean[][] visited, int N, int K, int peekHeight) {
        ArrayList<Position> peekList = new ArrayList<>();

        for (int k = 0; k < N; k++) {
            for (int p = 0; p < N; p++) {
                if (map[k][p] == peekHeight) {
                    peekList.add(new Position(k, p));
                }
            }
        }

        int result = 0;

        for (int k = 0; k < peekList.size(); k++) {
            int tmp = DFSSearching(peekList.get(k), map, visited);
            if (result < tmp){
                result = tmp;
            }
        }
        System.out.println("#"+(index+1)+" "+result);
    }

    public static int DFSSearching(Position pos,int[][] map, boolean[][] visited){
        int answer = 1;
        visited[pos.x][pos.y] = true;

        int[] directX = {-1, 1, 0, 0};
        int[] directY = {0, 0, -1, 1};
        int prev_answer = 0;

        for(int i = 0 ; i < 4 ; i++){

            int nextX = pos.x + directX[i];
            int nextY = pos.y + directY[i];

            if(nextX < 0 || nextX >= map.length || nextY < 0 || nextY >= map.length)
            {
                continue;
            }

            if(map[pos.x][pos.y] > map[nextX][nextY]  && visited[nextX][nextY] == false)
            {
                answer = DFSSearching(new Position(nextX, nextY), map, visited)+1;
            }

            else if(Kused == false && map[pos.x][pos.y] > map[nextX][nextY]-K && visited[nextX][nextY] == false)
            {
                Kused = true;
                int tmp = map[nextX][nextY];
                int prev_result = 0;

                for(int p = 1 ; p <= K ; p++){
                    map[nextX][nextY] = map[pos.x][pos.y]-p;
                    answer = DFSSearching(new Position(nextX, nextY), map, visited)+1;

                    if(prev_result > answer){
                        answer = prev_result;
                    }

                    prev_result = answer;
                }

                map[nextX][nextY] = tmp;
                Kused = false;
            }

            if (prev_answer > answer)
                answer = prev_answer;

            prev_answer = answer;
        }

        visited[pos.x][pos.y] = false;
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