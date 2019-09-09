package se_2112;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution {
    public static int D;
    public static int W;
    public static int K;
    public static int[][] filmCell;
    public static int answer;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("sample_input_2112.txt"));
        int testcaseNumber = scanner.nextInt();

        for(int i = 0 ; i < testcaseNumber; i++){
            answer = Integer.MAX_VALUE;
            D = scanner.nextInt();
            W = scanner.nextInt();
            K = scanner.nextInt();

            filmCell = new int[D][W];

            for(int k = 0 ; k < D ;k++){
                for(int p = 0 ; p < W; p++){
                    filmCell[k][p] = scanner.nextInt();
                }
            }

            answer = solve(0,0);
            System.out.println("#"+(i+1)+" "+answer);
        }
    }

    public static int solve(int currentIndex, int count){
        if(count >= answer){
            return Integer.MAX_VALUE;
        }

        if (check() == true){
            answer = Math.min(count, answer);
            return answer;
        }

        if (count == K){
            return Integer.MAX_VALUE;
        }

        for(int i = currentIndex; i < filmCell.length; i++){
            int[] originalCellValues = changeIndexCell(i, 0);
            int tmp = solve(i, count+1);
            recoverOriginalCell(i, originalCellValues);

            originalCellValues = changeIndexCell(i, 1);
            int tmp2 = solve(i, count+1);
            recoverOriginalCell(i, originalCellValues);

            answer = Math.min(answer, tmp);
            answer = Math.min(answer, tmp2);

        }

        return answer;
    }

    public static int[] changeIndexCell(int index, int changeNumber){
        int[] originalCellValues = new int[W];
        System.arraycopy(filmCell[index], 0, originalCellValues, 0, W);

        for(int i = 0 ; i < W; i++){
            filmCell[index][i] = changeNumber;
        }

        return originalCellValues;
    }

    public static void recoverOriginalCell(int index, int[] originalCellValues){
        filmCell[index] = originalCellValues;
    }


    public static boolean check(){
        boolean flag = false;
        int[] cells = new int[D];
        for (int i = 0 ; i < W ; i++){
            for(int j = 0; j < D ; j++){
                cells[j] = filmCell[j][i];
            }

            int prev_value = -1;
            int sequenced_count = 1;

            for(int p = 0 ; p < D; p++){
                if (prev_value == cells[p]){
                    sequenced_count += 1;
                }
                else{
                    sequenced_count = 1;
                }

                if(sequenced_count == K){
                    flag = true;
                    break;
                }

                prev_value = cells[p];
            }

            if (flag == false){
                return false;
            }
            flag = false;
        }

        return true;
    }
}
