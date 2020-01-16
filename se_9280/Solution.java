package se_9280;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("sample_input_9280.txt"));

        int testcaseNumber = scanner.nextInt();

        for(int i = 1; i <= testcaseNumber; i++){
            int answer = 0;

            int n = scanner.nextInt();
            int m = scanner.nextInt();

            //ParkingCell[] parkingCells = new ParkingCell[n];
            int[] R = new int[n];

            for(int j = 0; j < n; j++){
                R[j] = scanner.nextInt();
            }

            int minParkingWeight = R[0];
            int minParkingWeightIndex = 0;
            boolean[] cellCheck = new boolean[n];
            int parkingCount = 0;
            ArrayDeque<Integer> waitingQueue = new ArrayDeque<>();

            int[] W = new int[m];
            int[] parkingCell = new int[m];
            for(int j = 0 ;j < m; j++){
                W[j] = scanner.nextInt();
                parkingCell[j] = -1;
            }

            for(int j = 0 ; j < 2*m; j++){
                int input = scanner.nextInt();

                if(input > 0){ // 차가 진입
                    int inputCar = input-1;

                    // 빈 공간이 있다
                    if(parkingCount < n){
                        cellCheck[minParkingWeightIndex] = true;
                        parkingCount += 1;
                        answer += (W[input-1]*minParkingWeight);
                        parkingCell[inputCar] = minParkingWeightIndex;
                        int index = 1;

                        if(parkingCount < n){
                            while(true){
                                int checkIndex = (minParkingWeightIndex+index)%n;
                                if(cellCheck[checkIndex] == false){
                                    minParkingWeightIndex  = checkIndex;
                                    minParkingWeight = R[minParkingWeightIndex];
                                    break;
                                }
                                index += 1;
                            }
                        }

                    }else{
                        waitingQueue.add(inputCar);
                    }
                }else{ // 차 나감
                    int carIndex = Math.abs(input)-1;

                    cellCheck[parkingCell[carIndex]] = false;
                    parkingCount -= 1;
                    // 현재 최소 무게 셀 찾기

                    for(int k = 0 ; k < n ; k++){
                        if(cellCheck[k] == false){
                            minParkingWeight = R[k];
                            minParkingWeightIndex = k;
                            break;
                        }
                    }

                    // 대기 중인 차가 있다
                    if(waitingQueue.size() > 0){
                        int popped = waitingQueue.poll();
                        answer += (W[popped]*minParkingWeight);
                        cellCheck[minParkingWeightIndex] = true;
                        parkingCell[popped] = minParkingWeightIndex;
                        parkingCount += 1;
                    }
                }
            }

            System.out.println("# "+i+" "+answer);
        }


    }
}
