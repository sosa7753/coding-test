// DFS로 일렬로 서게 된 문자열 경우의 수를 가진다. 
// 경우의 수 마다 만족하는지 계산한다. 

class Solution {
    String[] friends = {"A", "C", "F", "J", "M", "N", "R", "T"};
    boolean[] visited = new boolean[8];
    int answer;
    public int solution(int n, String[] data) {
        answer = 0;
             
        DFS("", 0, data);
        return answer;
    }
    
    public void DFS(String start, int idx, String[] data) {
        if(idx == 8) {
            if(check(start, data)) {
                answer++;
            }
            return;
        }
        
        for(int i=0; i<8; i++) {
            if(visited[i]) {
                continue;
            }
            visited[i] = true;
            StringBuilder sb = new StringBuilder();
            sb.append(start);
            sb.append(friends[i]);
            DFS(sb.toString(), idx+1, data);
            visited[i] = false;        
        }          
    }
    
    public boolean check(String line, String[] data) {       
        boolean isfalse = true;
        for(int i=0; i<data.length; i++) {
            String str = data[i];
            
            char first = str.charAt(0);
            char second = str.charAt(2);
                       
            int gap = Math.abs(line.indexOf(first) - line.indexOf(second)) -1;
            
            char c = str.charAt(3);
            int value = str.charAt(4) - '0';
            if(c == '=' && value == gap) {
                continue;
            }
            if(c == '<' && value > gap) {
                continue;
            }
            if(c == '>' && value < gap) {
                continue;
            }
            isfalse = false;
            break;
        }
        
        return isfalse;
    }
}