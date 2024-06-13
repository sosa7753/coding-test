import java.util.*;
class Solution {
    int[] parents;
    public int solution(int n, int[][] costs) {
        return kruskal(n, costs);
    }
    
    public int kruskal(int n, int[][] costs) {
        int result = 0;
        
        parents = new int[n+1];
        for(int i=1; i<=n; i++) {
            parents[i] = i;
        }
        
        Arrays.sort(costs, (x,y) -> (x[2] -y[2]));
        
        for(int i=0; i<costs.length; i++) {
            if(find(costs[i][0]) != find(costs[i][1])) {
                union(costs[i][0], costs[i][1]);
                result += costs[i][2];
            }
        }
        return result;
    }
    
    public void union(int a, int b) {
        int a1 = find(a);
        int b1 = find(b);
        
        if(a1 < b1) {
            parents[b1] = a1;
        }else {
            parents[a1] = b1;
        }
    }
    
    public int find(int a) {
        if(a == parents[a]) {
            return a;
        }
        return parents[a] = find(parents[a]);
    }
}