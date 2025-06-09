import java.util.*;
class Solution {
    List<List<Integer>> list = new ArrayList<>();
    int answer = 0;
    public int solution(int n, int[][] lighthouse) {
        for(int i=0; i<=n; i++) {
            list.add(new ArrayList<>());
        }
        
        for(int i=0; i<lighthouse.length; i++) {
            list.get(lighthouse[i][0]).add(lighthouse[i][1]);
            list.get(lighthouse[i][1]).add(lighthouse[i][0]);
        }
        
        DFS(1, 0);
        return answer;
    }
    
    // 자식 : 살수 없으면 F, 살 수 있으면 T 
    // 부모는 자식이 1명이라도 F면 켜야함 -> T 
    // 자식 : 모두 T면 안켜도됨 -> T 
    public boolean DFS(int now, int pre) {
        if(list.get(now).size() == 1 && 
           list.get(now).get(0) == pre) { // 리프노드 
            return false;
        }
        
        boolean result = true;
        for(int next : list.get(now)) {
            if(next == pre) {
                continue;
            }
            
            if(!DFS(next, now)) {
                result = false;
            }
        }
        
        if(!result) {
            answer++;   
            return true;
        }
        
        return false;
    }
}