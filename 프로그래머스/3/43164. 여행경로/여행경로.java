import java.util.*;
class Solution {
    Map<String, PriorityQueue<String>> map = new HashMap<>();
    LinkedList<String> list = new LinkedList<>();
    public String[] solution(String[][] tickets) {  
        for(int i=0; i<tickets.length; i++) {
            map.computeIfAbsent(tickets[i][0], 
                            key -> new PriorityQueue<>()).add(tickets[i][1]);
        }
        
        DFS("ICN");
        
        return list.toArray(new String[0]);
    }
    
    public void DFS(String now) {
        PriorityQueue<String> pq = map.get(now);
        while(pq != null && !pq.isEmpty()) {
            DFS(pq.poll());
        }
        list.addFirst(now); 
    }
}