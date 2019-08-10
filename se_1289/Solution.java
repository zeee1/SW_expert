package se_1289;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        int testcaseNumber = scanner.nextInt();

        for(int i = 0 ; i < testcaseNumber; i++){
            String bitStr = scanner.nextLine();

            int stringLength = bitStr.length();
            String initString = "";

            for(int j = 0 ; j < stringLength; j++)
                initString += "0";

            for(int j = 0 ; j < stringLength ; j++){
                if(initString.charAt(j) == bitStr.charAt(j))
                    continue;


            }
        }
    }
}
