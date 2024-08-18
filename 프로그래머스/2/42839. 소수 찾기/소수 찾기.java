import java.util.*;
class Solution {
    int answer = 0;
    Set<Integer> set = new HashSet<>();
    char c[];
    boolean[] visited;
    public int solution(String numbers) {     
        
        c = numbers.toCharArray();
        visited = new boolean[numbers.length()];
        // 경우의 수 만들기 DFS      
        DFS("", 0);

        for(int value : set) {
            if(isPrime(value)) {
                answer++;
            }
        }
        
        return answer;
    }
    
    public void DFS(String start, int cnt) {
        if(cnt == c.length) {
            return;
        }
        
        for(int i=0; i<c.length; i++) {
            if(visited[i]) {
                continue;
            }
            
            visited[i] = true;
            String next = start + String.valueOf(c[i]);
            set.add(Integer.parseInt(next));
            DFS(next, cnt+1);
            visited[i] = false;
        }
    }
    
    public boolean isPrime(int num) {
        if(num == 0 || num == 1) {
            return false;
        }
            
        for(int i=2; i<=Math.sqrt(num); i++) {
            if(num % i == 0) {
                return false;
            }
        }
        return true;
    }
}