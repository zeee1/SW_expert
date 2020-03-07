package se_5644;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution {
    public static int M;
    public static int A;
    public static int[] dx = {0, 0, 1, 0, -1};
    public static int[] dy = {0, -1, 0, 1, 0};
    public static AP[] AParray;


    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("sample_input_5644.txt"));

        int testcaseNumber = scanner.nextInt();

        for(int i = 1; i <= testcaseNumber; i++){
            M = scanner.nextInt();
            A = scanner.nextInt();

            int[] AmoveArray = new int[M+1];
            int[] BmoveArray = new int[M+1];

            for(int j = 1 ; j <= M; j++){
                AmoveArray[j] = scanner.nextInt();
            }

            for(int j = 1 ; j <= M; j++){
                BmoveArray[j] = scanner.nextInt();
            }

            AParray = new AP[A];

            for(int j = 0 ; j < A; j++){
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                int C = scanner.nextInt();
                int P = scanner.nextInt();
                AParray[j] = new AP(x, y, C, P);
            }

            int Ax = 1;
            int Ay = 1;
            int Bx = 10;
            int By = 10;

            int sum = getAnswer(Ax, Ay, Bx, By);
            for(int j = 1 ; j <= M ; j++){
                int Amove = AmoveArray[j];
                int Bmove = BmoveArray[j];

                Ax += dx[Amove];
                Ay += dy[Amove];
                Bx += dx[Bmove];
                By += dy[Bmove];

                sum += getAnswer(Ax, Ay, Bx, By);
            }

            System.out.println("#"+i+" "+sum);
        }
    }

    public static int getAnswer(int ax, int ay, int bx, int by){
        int[] APower= new int[A];
        int[] BPower = new int[A];

        for(int i = 0 ; i < A ; i++){
            if(getDistance(ax, ay, AParray[i].x, AParray[i].y) <= AParray[i].C){
                APower[i] = AParray[i].P;
            }

            if(getDistance(bx, by, AParray[i].x, AParray[i].y) <= AParray[i].C){
                BPower[i] = AParray[i].P;
            }
        }

        int sum = 0;

        for(int i = 0 ; i < A; i++){
            for(int j = 0 ; j < A ; j++){
                if(i == j){
                    sum = Math.max(sum, APower[i]);
                    sum = Math.max(sum, BPower[i]);
                }
                else{
                    sum = Math.max(sum, APower[i]+BPower[j]);
                }
            }
        }

        return sum;
    }

    public static int getDistance(int x, int y, int APx, int APy){
        return Math.abs(APx-x)+Math.abs(APy-y);
    }
}

class AP{
    public int x;
    public int y;
    public int C;
    public int P;

    public AP(int x, int y, int c, int p){
        this.x = x;
        this.y = y;
        this.C = c;
        this.P = p;
    }
}
