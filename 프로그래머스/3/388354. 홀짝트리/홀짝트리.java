import java.util.*;
class Solution {
    List<List<Integer>> list = new ArrayList<>();
    public int[] solution(int[] nodes, int[][] edges) {
        int[] answer = new int[2];
        
        for(int i=0; i<=1000000; i++) {
            list.add(new ArrayList<>());
        }
        
        for(int i=0; i<edges.length; i++) {
            list.get(edges[i][0]).add(edges[i][1]);
            list.get(edges[i][1]).add(edges[i][0]);
        }
        
        for(int i=0; i<nodes.length; i++) {
            int n = nodes[i];
            int size = list.get(n).size();
            int value = 0;
            if(n%2 == size%2) {
                value = 0;
            }else {
                value = 1;
            }
            if(checkTree(n, value, 0)) {
                answer[value]++;
            }
        }
            
        return answer;
    }
    
    public boolean checkTree(int root, int type, int before) { // 0이면 홀짝트리, 1면 역홀짝 트리 
        int check = 0; 
        
        int len = list.get(root).size(); 
        int ro = len; // 다음 순회
        if(before != 0) { // 루트를 제외한 나머지
            len--;
        }
        
        if(root%2 == len%2) { // 홀짝 트리
           check = 0; 
        }else { // 역홀짝 트리 
           check = 1;
        }
        
        if(type != check) {
            return false;
        }

        for(int i=0; i<ro; i++) {
            int next = list.get(root).get(i);
            if(next == before) {
                continue;
            }
            if(!checkTree(next, type, root)) {
                return false;
            }
        }
        return true;
    }
}