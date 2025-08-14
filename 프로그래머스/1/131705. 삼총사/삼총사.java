class Solution {
    int answer = 0;
    public int solution(int[] number) {
        // DFS를 돌려 3명의 조합을 짜기
        DFS(number, 0, 0, 0);
        return answer;
    }
    
    public void DFS(int[] number, int idx, int sum, int cnt) {
        if(cnt == 3) {
            if(sum == 0) {
                answer++;
            }
            return;
        }
        
        for(int i=idx; i<number.length; i++) {
            DFS(number, i+1, sum + number[i], cnt+1);
        }
    }
}