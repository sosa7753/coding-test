import java.util.*;
class Solution {
    List<List<int[]>> list = new ArrayList<>();
    Set<Integer> set = new HashSet<>();
    int answer = 0;
    public int solution(int n, int infection, int[][] edges, int k) {
        for(int i=0; i<=n; i++) {
            list.add(new ArrayList<>());
        }
        
        for(int[] edge : edges) {
            list.get(edge[0]).add(new int[]{edge[1], edge[2]});
            list.get(edge[1]).add(new int[]{edge[0], edge[2]});
        } 
        
        set.add(infection);
        DFS(0, k);
        
        return answer;
    }
    
    public void DFS(int dep, int k) {
        answer = Math.max(answer, set.size());
        if(dep == k) return;
        
        for(int i=1; i<=3; i++) {
            List<Integer> newInfected = spread(i);
            DFS(dep+1, k);
            set.removeAll(newInfected);
        }
    }
    
    public List<Integer> spread(int type) {
        List<Integer> result = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>(set);
        
        while(!q.isEmpty()) {
            int now = q.poll();
            for(int[] next : list.get(now)) {
                int nextNode = next[0], nextType = next[1];
                if(nextType == type && !set.contains(nextNode)) {
                    set.add(nextNode);
                    result.add(nextNode);
                    q.add(nextNode);
                }
            }
        }
        return result;
    }
}