import java.util.*;
class Solution {
    List<List<Integer>> list = new ArrayList<>();
    int cnt = 0;
    int max = 0;
    int n;
    public int solution(int n, int[][] edges) {
        this.n = n;
        
        for(int i=0; i<=n; i++) {
            list.add(new ArrayList<>());
        }
        
        for(int[] edge : edges) {
            list.get(edge[0]).add(edge[1]);
            list.get(edge[1]).add(edge[0]);
        }
        
        int node = edges[0][0];
        for(int i=0; i<3; i++) {
            node = BFS(node);
            if(i == 0 || cnt <=1) {
                cnt = 0;
                continue;
            }else {
                return max; // 지름이 2개이상
            }
        }
    
        // 트리의 지름 :  a로 해서 가장 멀리간 점. 여기서 가장 멀리간 점 = 지름의 한 노드
        // 지름이 2개면? 중간값은 지름
        // 지름이 1개면? 지름 -1      
        return max - 1;
    }
    
    public int BFS(int start) {
        boolean[] visited = new boolean[n+1];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{start, 0});
        visited[start] = true;
        
        int last = 0;
        while(!q.isEmpty()) {
            int[] now = q.poll();
            int cur = now[0]; int dep = now[1]; 
            last = cur;
            
            for(int next : list.get(cur)) {
                if(visited[next]) continue;
                
                visited[next] = true;
                q.offer(new int[]{next, dep+1});
                
                if(dep+1 == max) {
                    cnt++;
                }else if(dep+1 > max) {
                    cnt = 1;
                    max = dep+1;
                }
            }
        }
        return last;
    }
}