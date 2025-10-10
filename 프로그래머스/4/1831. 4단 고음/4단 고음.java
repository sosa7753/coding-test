class Solution {
    int answer = 0;
    public int solution(int n) {
        DFS(n, 0);
        return answer;
    }
    
    public void DFS(int val, int plus) {
       if(Math.pow(3, plus/2) > val) {
           return;
       }
        
       if(val == 3) {
           if(plus == 2) {
               answer++;
           }
           return;
       }
        
       if(val > 3) {
           if(plus >=2 && val%3 == 0) {
               DFS(val/3, plus-2);
           }
           DFS(val-1, plus+1);
       }
    }
}