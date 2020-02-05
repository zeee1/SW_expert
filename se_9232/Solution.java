package se_9232;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream("sample_input_9232.txt")));
        StringTokenizer st;
        int testcaseNumber = Integer.parseInt(bf.readLine());

        for(int i = 1; i <= testcaseNumber ; i++){
            st = new StringTokenizer(bf.readLine());
            int n = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());

            ArrayList<Pair> arrayList = new ArrayList<>();
            st = new StringTokenizer(bf.readLine());

            int curMax = Integer.MAX_VALUE;
            for(int j = 1 ; j <= n ; j++){
                int r = Integer.parseInt(st.nextToken());

                if(curMax > r){
                    arrayList.add(new Pair(j, r));
                    curMax = r;
                }
            }

            int[] qR = new int[q+1];

            st = new StringTokenizer(bf.readLine());
            for(int j = 1 ; j <= q ; j++){
                qR[j] = Integer.parseInt(st.nextToken());
            }

            int answer = n+1;

            for(int j = 1; j <= q; j++){
                if(answer <= 0)
                    break;

                int check = upperBound(0, arrayList.size()-1, qR[j], arrayList);

                if(check == -1){
                    answer -= 1;
                }else{
                    answer = Math.min(answer-1, arrayList.get(check).index-1);
                }
            }

            System.out.println("#"+i+" "+answer);
        }
    }

    public static int upperBound(int left, int right, int r, ArrayList<Pair> list){
        if(left == right){
            if(left == list.size()-1){
               if(list.get(left).r >= r){
                   return -1; // 디스크가 블럭 제일 밑에 도착한 경우
               }
            }
            return left; // 디스크가 놓여질 위치 반환
        }

        int mid = (left+right)/2;

        if(list.get(mid).r < r){
            return upperBound(left, mid, r, list);
        }else{
            return upperBound(mid+1, right, r, list);
        }

    }
}

class Pair{
    public int index;
    public int r;

    public Pair(int index, int r){
        this.index = index;
        this.r = r;
    }
}