// DFS로 일렬로 서게 된 문자열 경우의 수를 가진다. 
// 경우의 수 마다 만족하는지 계산한다. 

class Solution {
    String[] friends = {"A", "C", "F", "J", "M", "N", "R", "T"};
    int answer;
    public int solution(int n, String[] data) {
        answer = 0;
        boolean[] visited = new boolean[8];
         
        DFS("", 0, data, visited);
        return answer;
    }
    
    public void DFS(String start, int idx, String[] data, boolean[] visited) {
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
            DFS(sb.toString(), idx+1, data, visited);
            visited[i] = false;        
        }          
    }
    
    public boolean check(String line, String[] data) {       
        boolean isfalse = true;
        for(int i=0; i<data.length; i++) {
            String str = data[i];
            
            char first = str.charAt(0);
            char second = str.charAt(2);
            
            int[] tmp = new int[2];
            int idx = 0;
            
            for(int j=0; j<line.length(); j++) {
                if(line.charAt(j) == first || line.charAt(j) == second) {
                    tmp[idx++] = j;
                }
            }
            int gap = tmp[1] - tmp[0] -1;
            
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