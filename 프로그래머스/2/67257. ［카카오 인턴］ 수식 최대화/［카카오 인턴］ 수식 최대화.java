import java.util.*;
class Solution {
    char[] oper = {'+', '-', '*'};
    long answer = 0;
    boolean[] visited = new boolean[3];
    List<Long> nums = new ArrayList<>();
    List<Character> ops = new ArrayList<>();
    public long solution(String expression) {
        tokenize(expression);
        
        int[] arr = new int[3];
        DFS(arr, 0);
        return answer;
    }
      
    public void tokenize(String ex) {
        int n = ex.length();
        int cnt = 0;    
        while(cnt < n) {
            int end = cnt;
            while(end < n && Character.isDigit(ex.charAt(end))) {
                end++;
            }
            nums.add(Long.parseLong(ex.substring(cnt, end)));
            if(end < n) {
                ops.add(ex.charAt(end));
            }
            cnt = end + 1;
        }
    }
    
    public void DFS(int[] arr, int cnt) {
        if(cnt == 3) {
            long val = cal(arr);
            answer = Math.max(answer, Math.abs(val));
            return;
        }
        
        for(int i=0; i<3; i++) {
            if(visited[i]) {
                continue;
            }
            
            visited[i] = true;
            arr[cnt] = i;
            DFS(arr, cnt+1);
            visited[i] = false;
        }
    }
    
    public long cal(int[] arr) {
        List<Long> n = new ArrayList<>(nums);
        List<Character> o = new ArrayList<>(ops);
        for(int i=0; i<3; i++) {
            char op = oper[arr[i]];
            
            int cnt = 0;
            while(cnt < o.size()) {
                if(o.get(cnt) != op) {
                    cnt++;
                    continue;
                }
                
                long a = n.remove(cnt);
                long b = n.remove(cnt);
                long r = single(a, b, op);
                n.add(cnt, r);
                o.remove(cnt);
            }
        }
        return n.get(0);
    }
    
    public long single(long a, long b, char op) {
        switch(op) {
            case '+' : return a+b;
            case '-' : return a-b;
            default: return a*b;    
        }
    }
}