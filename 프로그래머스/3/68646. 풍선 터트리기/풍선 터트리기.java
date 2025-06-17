class Solution {
    public int solution(int[] a) {
        int answer = 1;
        
        int len = a.length;
        
        int[] R = new int[len];
        R[len-1] = a[len-1];
        
        for(int i=len-2; i>=0; i--) {
            R[i] = Math.min(R[i+1], a[i]);
        }
        
        int[] L = new int[len];
        L[0] = a[0];
        for(int i=1; i<len; i++) {
            L[i] = Math.min(L[i-1], a[i]);
            if(a[i] > R[i] && L[i] < a[i]) {
                continue;
            }
            answer++;
        }
        return answer;
    }
}