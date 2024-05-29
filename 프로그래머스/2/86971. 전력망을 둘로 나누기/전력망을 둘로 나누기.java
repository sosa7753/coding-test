import java.util.*;
class Solution {
    List<ArrayList<Integer>> list;
    int min;
    public int solution(int n, int[][] wires) {
        
        list = new ArrayList<>();
        min = Integer.MAX_VALUE;
             
        for(int i=0; i<=n; i++) {
            list.add(new ArrayList<>());
        }
        
        for(int i=0; i<wires.length; i++) {
            list.get(wires[i][0]).add(wires[i][1]);
            list.get(wires[i][1]).add(wires[i][0]);
        }
        
        
        // 각 간선을 끊어서 DFS 돌리기
        for(int i=0; i<wires.length; i++) {
            int first = wires[i][0];
            int second = wires[i][1];
            
            boolean[] visited = new boolean[n+1];
            
            // 간선 제거
            list.get(first).remove(Integer.valueOf(second));
            list.get(second).remove(Integer.valueOf(first));
            
            int cnt = DFS(1, visited);
            int diff = Math.abs((n-cnt) - cnt);
            
            min = Math.min(diff, min);
            
            // 복구
            list.get(first).add(second);
            list.get(second).add(first);
        }
        
        return min;
    } 
    
    public int DFS(int v, boolean[] visited) {
        visited[v] = true;
        
        int cnt = 1;
        
        int tmp = list.get(v).size();
        for(int i=0; i<tmp; i++) {
            int value = list.get(v).get(i);
            if(!visited[value]) {
                cnt += DFS(value, visited);
            }          
        }
        return cnt;
    }
}
