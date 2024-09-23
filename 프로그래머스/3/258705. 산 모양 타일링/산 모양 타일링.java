class Solution {
    public int solution(int n, int[] tops) {
        int INF = 10007;
        int answer = 0;
        
        int[] a = new int[n]; // 3번을 썼을 때
        int[] b = new int[n]; // 3번을 안썼을 때 
        
        a[0] = 1;
        if(tops[0] == 1) {
            b[0] = 3;
        }else {
            b[0] = 2;
        }
        
        for(int i=1; i<tops.length; i++) {
            a[i] = a[i-1] + b[i-1];
            
            if(tops[i] == 1) {
                b[i] = (a[i-1] * 2 + b[i-1] * 3) % INF;
            }else {
                b[i] = (a[i-1] + b[i-1] * 2) % INF;
            }
        }
        
        return (a[n-1] + b[n-1]) % 10007;
    }
}