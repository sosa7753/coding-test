import java.util.*;
class Solution {
    List<List<Integer>> list = new ArrayList<>();
    long[] b;
    long answer = 0;
    public long solution(int[] a, int[][] edges) {  
        for(int i=0; i<a.length; i++) {
            list.add(new ArrayList<>());
        }
                
        b = new long[a.length];
        for(int i=0; i<a.length; i++) {
            b[i] = (long)a[i];
        }
        
        for(int i=0; i<edges.length; i++) {
            list.get(edges[i][0]).add(edges[i][1]);
            list.get(edges[i][1]).add(edges[i][0]);       
        }

        long value = DFS(0, 0);
        if(value != 0) {
            return -1L;
        }                             
       
        return answer;
    }
    
    public long DFS(int node, int before) {
        // 리프노드 조건 
        if(list.get(node).size() == 1 && list.get(node).get(0) == before) {
            if(b[node] < 0) {
                answer -= b[node];
            }else {
                answer += b[node];
            }           
            return b[node]; 
        }
        
        // 과거에서 온 데이터 조건 
        long result = b[node]; 
        for(int i=0; i<list.get(node).size(); i++) {
            int next = list.get(node).get(i);
            if(next == before) {
                continue;
            }
            result += DFS(next, node);                   
        }
        if(result < 0) {
            answer -= result; 
        }else {
            answer += result;
        }                    
        return result;        
    }
}