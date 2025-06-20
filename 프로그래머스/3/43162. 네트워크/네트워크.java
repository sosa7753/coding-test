import java.util.*;
class Solution {
    int[] parents;
    public int solution(int n, int[][] computers) {
        parents = new int[n];
        for(int i=0; i<parents.length; i++) {
            parents[i] = i;
        }
        
        for(int i=0; i<computers.length; i++) {
            for(int j=i+1; j<computers.length; j++) {              
                if(i != j && computers[i][j] == 1) {
                    union(i, j);
                }
            }            
        }
             
        int answer = 0;
        for(int i=0; i<parents.length; i++) {
            if(parents[i] == i) {
                answer++;
            }
        }
        
        System.out.print(Arrays.toString(parents));
        return answer;
    }
    
    public void union(int a, int b) {
        a = find(a);
        b = find(b);
        
        if(a < b) { // 작은 거로 업데이트 
            parents[b] = a;       
        }else {
            parents[a] = b;
        }
    }
    
    public int find(int a) {
        if(a == parents[a]) {
            return a;
        }
        return parents[a] = find(parents[a]);
    }
}