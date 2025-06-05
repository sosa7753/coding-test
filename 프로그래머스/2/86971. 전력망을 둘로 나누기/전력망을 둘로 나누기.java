import java.util.*;
class Solution {
    List<List<Integer>> list = new ArrayList<>();
    int answer = Integer.MAX_VALUE;
    boolean[] visited;
    public int solution(int n, int[][] wires) {
        visited = new boolean[n+1];
        
        for(int i=0; i<=n; i++) {
            list.add(new ArrayList<>());
        }
        
        for(int i=0; i<wires.length; i++) {
            list.get(wires[i][0]).add(wires[i][1]);
            list.get(wires[i][1]).add(wires[i][0]);      
        }
        
        for(int i=0; i<wires.length; i++) {
            int s = wires[i][0];
            int e = wires[i][1];
            visited = new boolean[n+1];
            
            list.get(s).remove(Integer.valueOf(e));
            list.get(e).remove(Integer.valueOf(s));
            
            int cnt = BFS();
            answer = Math.min(answer, Math.abs(cnt - (n - cnt)));
            
            list.get(s).add(e);
            list.get(e).add(s);
        }
        
        return answer;
    }
    
    public int BFS() {
        Queue<Integer> q = new LinkedList<>();
        q.offer(1);
        visited[1] = true;
        
        int result = 1;
        while(!q.isEmpty()) {
            int now = q.poll();
                          
            for(int next : list.get(now)) {
                if(visited[next]) {
                    continue;
                }
                visited[next] = true;
                result++;
                q.offer(next);
            }
        }
        return result;
    }
}