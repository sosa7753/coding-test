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
        
        Arrays.sort(costs, (x,y) -> (x[2] - y[2]));
        
        for(int[] cost : costs) {
            if(find(cost[0]) != find(cost[1])) {
                union(cost[0], cost[1]);
                result += cost[2];
            }
        }
        return result;
    }
    
    public void union(int a, int b) {
        a = find(a);
        b = find(b);
        
        if(a <= b) {
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