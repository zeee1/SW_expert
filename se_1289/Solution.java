package se_1289;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
        //Scanner scanner = new Scanner(System.in);
        Scanner scanner = new Scanner(new FileInputStream("sample_input_1289.txt"));
        int testcaseNumber = scanner.nextInt();

        for(int i = 1 ; i <= testcaseNumber; i++){
            String bitStr = scanner.next();

            int stringLength = bitStr.length();
            String initString = "";

            for(int j = 0 ; j < stringLength; j++)
                initString += "0";

            String prevBitStr = "";
            int count = 0;


            for(int j = 0 ; j < stringLength ; j++){
                String tmp ="";
                if(initString.charAt(j) == bitStr.charAt(j)){
                    prevBitStr += bitStr.charAt(j);
                    continue;
                }

                if(initString.charAt(j) == '0'){
                    tmp = prevBitStr;
                    for(int k = j; k < stringLength; k++){
                        tmp += '1';
                    }
                }   else{
                    tmp = prevBitStr;
                    for(int k = j; k < stringLength; k++){
                        tmp += '0';
                    }
                }

                initString = tmp;
                count += 1;

                if(tmp.equals(bitStr))
                    break;

                prevBitStr += bitStr.charAt(j);
            }

            System.out.println("#"+i+" "+count);
        }
    }
}
