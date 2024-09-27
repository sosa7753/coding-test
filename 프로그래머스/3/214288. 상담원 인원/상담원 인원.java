import java.util.*;
class Solution {
    int[] arr;
    int answer = Integer.MAX_VALUE;
    public int solution(int k, int n, int[][] reqs) {
        arr = new int[k];
        
        DFS(0, n-k, reqs);
        return answer;
    }
    
    // 중복조합의 DFS 연산 
    public void DFS(int cnt, int max, int[][] reqs) {
        if(cnt == arr.length) {
            answer = Math.min(answer, cal(reqs));
            return;
        }
        
        for(int i=0; i<=max; i++) { // arr을 채워넣을 때 무조건 + 1을 해주어야함.
            arr[cnt] = i+1;
            DFS(cnt+1, max-i, reqs);
        }
    }
    
    // 배열이 주어지면 기다린 시간 연산 
    public int cal(int[][] reqs) {
        int wait = 0;        
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        
        for(int i=0; i<reqs.length; i++) {
            int[] now = reqs[i];
            
            if(!map.containsKey(now[2])) {
               map.put(now[2], new PriorityQueue<>());
               map.get(now[2]).offer(now[0]+now[1]);
               continue;
            }
            
            // 이미 있다. 
            PriorityQueue<Integer> pq = map.get(now[2]);
            
            // 비어있으면 안기다리고 바로 넣기 
            if(pq.isEmpty()) {
                pq.offer(now[0]+now[1]);
                continue;
            }
            
            // 종료되면 내보내기 
            if(pq.peek() <= now[0]) {
                pq.poll();
            }
            
            // 여유 있는 경우 
            if(arr[now[2]-1] > pq.size()) {
                pq.offer(now[0]+now[1]);
                continue;
            }
            
            // 기다리기.
            int tmp = pq.peek() - now[0];
            wait += tmp;
            pq.poll();
            pq.offer(now[0]+now[1] + tmp);            
        }
        return wait;
    }
}