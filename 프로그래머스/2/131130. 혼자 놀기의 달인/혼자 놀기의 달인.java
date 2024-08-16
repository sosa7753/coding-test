import java.util.*;
class Solution {
    int[] parent;
    public int solution(int[] cards) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((x,y) -> (y[1] - x[1]));
        int answer = 0;
        
        parent = new int[cards.length];
        
        for(int i=0; i<cards.length; i++) {
            parent[i] = i;
        }
        
        boolean[] visited = new boolean[cards.length];
        
        for(int i=0; i<cards.length; i++) {
            if(visited[i]) {
                continue;
            }
            visited[i] = true;
            
            if(find(i) != find(cards[i]-1)) {
                union(find(i), find(cards[i]-1));
            }     
        }
        
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<parent.length; i++) {
            map.put(find(i), map.getOrDefault(find(i), 0) + 1);
        }
        
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            pq.offer(new int[] {entry.getKey(), entry.getValue()});
        }
        
        if(pq.size() >=2) {
            int f = pq.poll()[1];
            int s = pq.poll()[1];
            answer = f * s;
        }
        
        return answer;
    }
    
    public void union(int a, int b) {
        int aP = find(a);
        int bP = find(b);
        
        if(aP < bP) {
            parent[b] = parent[a];
        }else {
            parent[a] = parent[b];
        }
    }
    
    public int find(int a) {
        if(parent[a] == a) {
           return a; 
        }
        
        return parent[a] = find(parent[a]);
    }
}