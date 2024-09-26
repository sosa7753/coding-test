import java.util.*;
class Solution {
    List<List<Integer>> list = new ArrayList<>(); // 이진트리
    boolean[] visited;
    int answer = 0;
    public int solution(int[] info, int[][] edges) {
        visited = new boolean[info.length];
        
        for(int i=0; i<info.length; i++) {
            list.add(new ArrayList<>());
        }
        
        for(int i=0; i<edges.length; i++) {
            list.get(edges[i][0]).add(edges[i][1]);
        }
        
        DFS(info, 0, 1, 0, new ArrayList<>());              
        return answer;
    }
    
    public void DFS(int[] info, int node, int sheep, int wolf, List<Integer> next) {
        for(int arrive : list.get(node)) {
            next.add(arrive);
        }
                
        int s = 0;
        int w = 0;
        for(int i=0; i<next.size(); i++) {
            int nextNode = next.get(i); 
            if(visited[nextNode]) {
                continue;
            }
            
            s = sheep;
            w = wolf;
            if(info[nextNode] == 0) {
                s++;
            }else {
                w++;
            }
                        
            if(s == w) { // 잡아먹힘
               answer = Math.max(answer, s);
               continue;
            }

            visited[nextNode] = true;
            List<Integer> nNext = new ArrayList<>(next);
            nNext.remove(i);
            DFS(info, nextNode, s, w, nNext);
            visited[nextNode] = false;
        }
        answer = Math.max(answer, sheep);
    } 
}
