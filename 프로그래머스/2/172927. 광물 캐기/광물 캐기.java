class Solution {
    int[][] pirodo = {{1,1,1}, {5,1,1}, {25,5,1}};
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
               
        answer = DFS(picks, minerals, 0);
        return answer;
    }
    
    public int DFS(int[] picks, String[] minerals, int cnt) {
        if((picks[0] == 0 && picks[1] == 0 && picks[2] == 0) 
           || cnt >= minerals.length) {
            return 0;
        }
        
        int min = Integer.MAX_VALUE;
        for(int i=0; i<3; i++) {
            if(picks[i] == 0) {
                continue;
            }
            
            int sum = 0;
            for(int j=cnt; j<Math.min(cnt+5, minerals.length); j++) {
                int idx = -1;
                if("diamond".equals(minerals[j])) {
                    idx = 0;
                }else if("iron".equals(minerals[j])) {
                    idx = 1;
                }else {
                    idx = 2;
                }
                
                sum += pirodo[i][idx];
            }
            picks[i]--;
            sum += DFS(picks, minerals, cnt+5);
            min = Math.min(sum, min);
            
            picks[i]++;
        }
        return min;
    }
}