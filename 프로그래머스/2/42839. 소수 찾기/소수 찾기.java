import java.util.*;
class Solution {
    int answer;
    Set<Integer> set = new HashSet<>();
    public int solution(String numbers) {
        answer = 0;
        boolean[] visited = new boolean[numbers.length()];
        
        DFS("", visited, numbers, 0);
        
        for(int t : set) {
            if(isPrime(t)) {
                answer++;
            }
        }
        return answer;
    }
    
    public void DFS(String start, boolean[] visited, String numbers, int cnt) {
        if(cnt == numbers.length()) {
            return;
        }
        
        for(int i=0; i<numbers.length(); i++) {
            if(visited[i]) {
                continue;
            }
            visited[i] = true;
            String s = start + numbers.charAt(i);
            set.add(Integer.parseInt(s));
            DFS(s, visited, numbers, cnt+1);
            visited[i] = false;
        }          
    }
    
    public boolean isPrime(int t) {       
        if(t<=1) {
            return false;
        }
               
        for(int i=2; i<=t/2; i++) {
            if(t%i == 0) {
                return false;
            }
        }
        return true;
    }
}