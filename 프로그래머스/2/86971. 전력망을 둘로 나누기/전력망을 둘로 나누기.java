// 간선정보를 하나씩 끊기.
// 끊은 연결리스트 가지고 DFS로 개수 세기 
// 방문배열로 이미 방문한 적 있는 곳은 패스 
import java.util.*;
class Solution {
    List<List<Integer>> list = new ArrayList<>();
    boolean[] visited;
    int min;
    public int solution(int n, int[][] wires) {
        min = Integer.MAX_VALUE;
        
        for(int i=0; i<=n; i++) {
            list.add(new ArrayList<>());
        }
        
        for(int i=0; i<wires.length; i++) {
            list.get(wires[i][0]).add(wires[i][1]);
            list.get(wires[i][1]).add(wires[i][0]);            
        }
        
        // 하나씩 끊어서 DFS 돌리기 
        for(int i=0; i<wires.length; i++) {
            list.get(wires[i][0]).remove(Integer.valueOf(wires[i][1]));
            list.get(wires[i][1]).remove(Integer.valueOf(wires[i][0]));
            
            visited = new boolean[n+1];
            
            int cnt = DFS(1, visited);
            min = Math.min(min,  Math.abs(cnt - (n - cnt)));
            
            list.get(wires[i][0]).add(wires[i][1]);
            list.get(wires[i][1]).add(wires[i][0]);
        }
        
        return min;
    }
    
    public int DFS(int start, boolean[] visited) {
        visited[start] = true;
        
        int cnt = 1;
        
        for(int i=0; i<list.get(start).size(); i++) {
            int next = list.get(start).get(i);
            if(!visited[next]) {
                cnt += DFS(next, visited);
            }
        }
        return cnt;
    }
}