class Solution {
    int n, answer;
    int[] child;
    int[] info;
    public int solution(int[] info, int[][] edges) {
        n = info.length;
        this.info = info;

        child = new int[n];
        for(int[] edge : edges) {
            int idx = edge[0];
            int c = edge[1];
            child[idx] |= (1 << c);
        }
        
        answer = 0;
        DFS(0, 0, 1 << 0);
        return answer;
    }
    
    public void DFS(int sheep, int wolf, int next) {
        answer = Math.max(answer, sheep);
        
        for(int i=0; i<n; i++) {
            if((next & (1 << i)) == 0) continue;
            
            int ns = sheep + (info[i] == 0 ? 1 : 0);
            int nw = wolf + (info[i] == 1 ? 1 : 0);
            
            if(nw >= ns) continue;
            
            int nextRoute = (next | child[i]) & ~(1 << i);
            DFS(ns, nw, nextRoute);
        }
    }
}