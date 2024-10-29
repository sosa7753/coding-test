class Solution {
    public int solution(int[] a) {
        int answer = 0;
        
        int L = a[0];
        int[] R = new int[a.length];
        R[a.length-1] = a[a.length-1];
        for(int i = R.length-2; i>=1; i--) {
            R[i] = Math.min(R[i+1], a[i]);
        }
        
        for(int i=0; i<a.length; i++) {
            if(!(L < a[i] && a[i] > R[i])) {
                answer++;
            }
            L = Math.min(L, a[i]);
        }
        return answer;
    }
}