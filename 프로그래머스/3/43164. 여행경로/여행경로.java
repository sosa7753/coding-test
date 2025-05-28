import java.util.*;
class Solution {
    boolean[] visited;
    List<String> list = new ArrayList<>();
    int n = 0;
    public String[] solution(String[][] tickets) {
        
        n = tickets.length;
        visited = new boolean[n];
        
        DFS("ICN", "ICN", tickets, 0);
        Collections.sort(list);
        return list.get(0).split(" ");

    }
    
    public void DFS(String now, String route, String[][] tickets, int cnt) {
        if(cnt == n) {
            list.add(route);
            return;
        }
        
        for(int i=0; i<tickets.length; i++) {
            if(!visited[i] && now.equals(tickets[i][0])) {
                visited[i] = true;
                DFS(tickets[i][1], route + " " + tickets[i][1], 
                    tickets, cnt+1);
                visited[i] = false;
            }
        }
    }
}