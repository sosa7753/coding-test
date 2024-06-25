import java.util.*;
class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        
        int maxA = arrayA[0];
        int maxB = arrayB[0];
        for(int i=1; i<arrayA.length; i++) {
            maxA = gcd(maxA, arrayA[i]);
            maxB = gcd(maxB, arrayB[i]);
        }
        
        int max = 0;
        if(maxA != 1) {
            boolean isfalseA = true;
            for(int i=0; i<arrayB.length; i++) {
                if(arrayB[i]%maxA !=0) {
                    continue;
                }
                isfalseA = false;
                break;
            }
            if(isfalseA) {
                max = Math.max(max, maxA);
            }
        }
        
        if(maxB != 1) {
            boolean isfalseB = true;
            for(int i=0; i<arrayA.length; i++) {
                if(arrayA[i]%maxB !=0) {
                    continue;
                }
                isfalseB = false;
                break;
            }
            if(isfalseB) {
                max = Math.max(max, maxB);
            }
        }
        
        return max;
    }
    
    public int gcd(int a, int b) {
        if(b == 0) {
            return a;
        } 
            
        return gcd(b, a%b);
    }
}