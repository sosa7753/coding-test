import java.util.*;
class Solution {
    char[] user = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    boolean[] visited = new boolean[8];
    int answer = 0; 
    public int solution(int n, String[] data) {
        char[] arr = new char[8];
        DFS(arr, data, 0);
        
        return answer;
    }
    
    public void DFS(char[] arr, String[] data, int cnt) {
        if(cnt == arr.length) {
            if(check(arr, data)) {
                answer++;
            }
            return;
        }
        
        for(int i=0; i<arr.length; i++) {
            if(visited[i]) {
                continue;
            }
 
            visited[i] = true;
            arr[cnt] = user[i];
            DFS(arr, data, cnt + 1);
            visited[i] = false;
        }
    }
    
    public boolean check(char[] arr, String[] data) {    
        Map<Character, Integer> map = new HashMap<>();
        for(int i=0; i<arr.length; i++) {
            map.put(arr[i], i);
        }
        
        boolean result = true;
        for(String d : data) {
            char[] c = d.toCharArray();
            char f = c[0];
            char s = c[2];
            char o = c[3];
            int in = c[4] - '0';
            
            int gap = Math.abs(map.get(f) - map.get(s)) - 1;
            
            if(o == '=' && gap == in) {
               continue; 
            }
            
            if(o == '<' && gap < in) {
                continue;
            }
            
            if(o == '>' && gap > in) {
                continue;
            }
            result = false; 
            break;
        }
        return result;
    }
}