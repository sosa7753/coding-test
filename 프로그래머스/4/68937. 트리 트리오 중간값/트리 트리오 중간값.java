import java.util.*;
class Solution {
    List<List<Integer>> list = new ArrayList<>();
    int cnt = 0;
    int max = 0;
    public int solution(int n, int[][] edges) {
        int answer = 0;
        
        for(int i=0; i<=n; i++) {
            list.add(new ArrayList<>());
        }
        
        for(int i=0; i<edges.length; i++) {
            list.get(edges[i][0]).add(edges[i][1]);
            list.get(edges[i][1]).add(edges[i][0]);
        }
        
        // 3번 탐색. 가장 먼 정점 찾기 -> 다른 정점 찾기 -> 지름 경로 개수 찾기        
        int node = edges[0][0];
        for(int i=0; i<3; i++) {
            node = BFS(node);
            if(i==0 || cnt <=1) {
                cnt = 0;
                continue;
            }else {
                return max;
            }
        }                
        return max - 1;
    }
    
    public int BFS(int node) {
        boolean[] visited = new boolean[250001];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(node);
        queue.offer(0);
        visited[node] = true;
        
        int last = 0;
        while(!queue.isEmpty()) {
            int now = queue.poll();
            last= now;
            int level = queue.poll();
                        
            for(int i=0; i<list.get(now).size(); i++) {
                int next = list.get(now).get(i);
                
                if(!visited[next]) {
                    queue.offer(next);
                    queue.offer(level+1);
                    if(level+1 == max) {
                        cnt++;
                    }else {
                        cnt = 1;
                        max = level + 1;
                    }
                    visited[next] = true;
                }
             }
        }
        return last;
    }
}