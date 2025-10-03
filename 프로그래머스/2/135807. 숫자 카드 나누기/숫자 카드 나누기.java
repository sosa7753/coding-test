class Solution {
    public int solution(int[] arrayA, int[] arrayB) {       
        int maxA = arrayA[0];
        int maxB = arrayB[0];
        for(int i=1; i<arrayA.length; i++) {
            maxA = gcd(maxA, arrayA[i]);
            maxB = gcd(maxB, arrayB[i]);
        }
        
        int answer = 0;
        for(int i=0; i<arrayA.length; i++) {
           if(arrayA[i]%maxB == 0) {
               break;
           }
           if(i == arrayA.length-1) {
               answer = Math.max(answer, maxB);
           }
        }
        
        for(int i=0; i<arrayB.length; i++) {
           if( arrayB[i]%maxA == 0) {
               break;
           }
           if(i == arrayB.length-1) {
               answer = Math.max(answer, maxA);
           }
        }
        
        return answer;
    }
    
    public int gcd(int a, int b) { // a = b * q + r , gcd(a, b) = gcd(b, r);  
        if(b == 0) {
            return a;
        }
        return gcd(b, a%b);
    }
}