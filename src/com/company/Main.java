package com.company;

import java.util.*;

public class Main {
    public static final int SIZE = 30;
    public static final int MAX = 10000;

    public static ArrayList<Integer> readArray(Scanner sc){
        ArrayList<Integer> arr = new ArrayList<>();
        int sizeA = sc.nextInt();
        if(sizeA>SIZE){
            return null;
        }
        for(int i=0;i<sizeA;i++){
            arr.add(sc.nextInt());
        }
        for(int i=0;i<sizeA;i++){
            if(arr.get(i)>MAX){
                return null;
            }
        }
        return arr;
    }

    public static boolean splitArray(ArrayList<Integer> arr){
        if(!possible(arr)){
            return false;
        }
        int sum = 0;
        for(Integer i:arr){
            sum+= i;
        }
        double avg = (double) sum/arr.size(); // the average that we are looking for; if two subsets have the same average, then it will also be the average of the original sum of the array
        Integer[][] sums = new Integer[sum+1][arr.size()+1]; // store all possible sums of the sub arrays
        sums[0][0]=0; // initialize the structure for 0 number of elements;
        for(int i=1;i<arr.size()+1;i++){
            int current = arr.get(i);
            for(int j=current;j<sum+1;j++){
                if(sums[j-current][i-1]!=null){
                    int prev = sums[j-current][i-1];
                    sums[j-current][i] = prev;
                    sums[j][i] = prev+1;
                    if(sums[j][i] < arr.size() && (double) j/sums[j][i] == avg){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static boolean possible(ArrayList<Integer> arr){
        int sum = 0;
        for(Integer i:arr){
            sum+= i;
        }
        boolean ok = false;
        for(int i=0;i<arr.size();i++){
            if((i*sum)%arr.size()==0){
                ok = true;
            }
        }
        return ok;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> A = new ArrayList<>();
        A = readArray(sc);
        Collections.sort(A);
        System.out.println(splitArray(A));
    }
}
