import java.util.*;
class Solution {
    char[] people = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    int answer;
    public int solution(int n, String[] data) {
        
        answer = 0;
        char[] friends = new char[8]; // 일렬 배열 
        boolean[] visited = new boolean[8];
        
        DFS(visited, friends, data, 0);
        return answer;
    }
    
    public void DFS(boolean[] visited, char[] friends, String[] data, int cnt) {
        if(cnt == friends.length) {
            if(check(friends, data)) {
                answer++;      
            }
            return;
        }
        
        for(int i=0; i<8; i++) {
            if(visited[i]) {
                continue;
            }
            
           friends[cnt] = people[i];
           visited[i] = true;
           DFS(visited, friends, data, cnt+1);
           visited[i] = false;
        }
    }
    
    public boolean check(char[] friends, String[] data) {
        Map<Character, Integer> map = new HashMap<>();
        
        for(int i=0; i<friends.length; i++) {
            map.put(friends[i], i);
        }
        
        boolean good = true;
        for(int i=0; i<data.length; i++) {
            // data[i]의 길이는 5
            char first = data[i].charAt(0);
            char second = data[i].charAt(2);
            
            int gap = Math.abs(map.get(second) - map.get(first)) - 1;
            
            // 각 부호에 따라 계산 
            if(data[i].charAt(3) == '=' && (int)(data[i].charAt(4) - '0') == gap) {
                continue;
            }
            
            if(data[i].charAt(3) == '<' && (int)(data[i].charAt(4) - '0') > gap) {
                continue;
            }
            
            if(data[i].charAt(3) == '>' && (int)(data[i].charAt(4) - '0') < gap) {
                continue;
            }
            good = false;
            break;
        }
        return good;        
    }
}