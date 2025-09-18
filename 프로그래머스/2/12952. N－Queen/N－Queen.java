class Solution {
    int answer = 0;
    public int solution(int n) {     
        DFS(0, n, 0,0,0);
        return answer;
    }
    
    public void DFS(int r, int n, int col, int ld, int rd) {
        if(r == n) {
            answer++;
            return;
        }
        
        // 이용할 수 있는 곳은 col,ld,rd에서 모두 0인 부분만 남기기(64비트 뒷부분 자르기 )
        int a = ((1 << n) - 1) & ~(col | ld | rd);
        
        while(a != 0) {
            int pos = a & -a; // 가장 오른쪽 1비트 추출
            a -= pos; // 그 비트 제거
            
            DFS(r+1, n, (col | pos), (ld | pos) << 1, (rd | pos) >> 1);
        }   
    }
}