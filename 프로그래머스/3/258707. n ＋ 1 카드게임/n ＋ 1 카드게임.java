import java.util.*;
class Solution {
    public int solution(int coin, int[] cards) {
        int n = cards.length;
        int len = n/3;
        int totalRounds = len; // 최대 진행 라운드
        
        // 각 값 별 등장 라운드
        int[] slot = new int[n+1];
        for(int i=0; i<len ; i++) {
            slot[cards[i]] = 0;
        }
        
        for(int r=1; r<=totalRounds; r++) {
            int idx1 = len + 2 * (r-1);
            int idx2 = idx1+1;
            slot[cards[idx1]] = r;
            slot[cards[idx2]] = r;
        }
        
        List<List<Integer>> list = new ArrayList<>(); // (v, n+1-v) 별 (ready, cost) 모으기
        for(int i=0; i<=totalRounds; i++) list.add(new ArrayList<>());
        
        for(int v=1; 2*v < n+1; v++) {
            int p = n+1-v;
            int ready = Math.max(slot[v], slot[p]);
            int cost = (slot[v] > 0 ? 1 : 0) + (slot[p] > 0 ? 1 : 0);
            list.get(ready).add(cost);
        }
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        long totalCost = 0;
        
        for(int c : list.get(0)) pq.offer(c);
        
        for(int r=1; r<=totalRounds; r++) {
            for(int c : list.get(r)) pq.offer(c);
            
            if(pq.isEmpty()) return r;
            
            int c = pq.poll();
            if(totalCost + c > coin) return r;
            totalCost += c; 
        }
        return totalRounds + 1;
    }
}