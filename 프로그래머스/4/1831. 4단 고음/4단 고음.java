class Solution {
    int answer = 0;
    public int solution(int n) {
        
        DFS(n, 0);
        return answer;
    }
    
    public void DFS(int now, int plus) {
      if(Math.pow(3, plus/2) > now) { // +를 두번 썼다면 3이상 이어야 3으로 나눌 수 있음.
          return;
      }
        
      if(now == 3) { // 마지막은 *을 써야하기 때문에 남은 plus는 2여야함.
          if(plus == 2) {
              answer++;
          }
          return;
      }
       
      if(now > 3) {
          if(plus >=2 && now%3 == 0) {
              DFS(now/3, plus-2);
          }
          DFS(now-1, plus+1);
      }        
    }
}