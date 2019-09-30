package se_4013;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Solution {
    public static int[][] magnets = new int[4][8];
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("sample_input_4013.txt"));

        int testcaseNumber =scanner.nextInt();

        for(int i = 1; i <= testcaseNumber; i++){
            int K = scanner.nextInt();

            magnets = new int[4][8];

            for(int k = 0 ; k < 4; k++){
                for(int p = 0 ; p < 8 ; p++){
                    magnets[k][p] = scanner.nextInt();
                }
            }

            int j = 0;

            while(j < K){
                int target = scanner.nextInt();
                int moveCount = scanner.nextInt();


                rotateMagnet(target-1, moveCount, -1);
                j++;
            }
            int sum = 0;
            if(magnets[0][0] == 1){
                sum+= 1;
            }
            if(magnets[1][0] == 1){
                sum += 2;
            }
            if(magnets[2][0] == 1){
                sum += 4;
            }
            if(magnets[3][0] == 1){
                sum += 8;
            }

            System.out.println("#"+i+" "+sum);
        }
    }

    public static void rotateMagnet(int target, int moveCount, int callingTarget){
        ArrayList<Integer> affectedTarget = new ArrayList<>();

        if(target == 0){
            if(magnets[target][2] != magnets[target+1][6]){
                if(callingTarget != target+1)
                    affectedTarget.add(target+1);
            }
            //only 1st target would be affected
        }
        else if(target == 3){
            //only 2nd target would be affected
            if(magnets[target][6] != magnets[target-1][2]){
                if(callingTarget != target-1)
                    affectedTarget.add(target-1);
            }
        }else{
            if(magnets[target][2] != magnets[target+1][6]){
                if(callingTarget != target+1)
                    affectedTarget.add(target+1);
            }
            if(magnets[target][6] != magnets[target-1][2]){
                if(callingTarget != target-1)
                    affectedTarget.add(target-1);
            }
        }

        int absMoveCount = Math.abs(moveCount);

        if(moveCount < 0){
            int[] tmp = new int[8];
            System.arraycopy(magnets[target], absMoveCount, tmp, 0, 8-absMoveCount);
            tmp[7] = magnets[target][0];
            System.arraycopy(tmp, 0, magnets[target], 0, 8);
        }
        else{
            int[] tmp = new int[8];
            System.arraycopy(magnets[target], 0, tmp, moveCount, 8-moveCount);
            tmp[0] = magnets[target][7];
            System.arraycopy(tmp, 0, magnets[target], 0, 8);
        }

        for(int i = 0 ; i < affectedTarget.size() ; i++){
            rotateMagnet(affectedTarget.get(i), (-1)*moveCount, target);
        }
    }
}
