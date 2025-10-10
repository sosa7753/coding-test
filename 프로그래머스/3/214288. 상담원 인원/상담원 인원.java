import java.util.*;
class Solution {
    int answer = Integer.MAX_VALUE;
    Map<Integer, List<int[]>> map = new HashMap<>();
    public int solution(int k, int n, int[][] reqs) {       
        // x + y + z + w + v = 15  19C4 = 경우의 수 가능 
        // DFS로 각 담당 배정하기
        // Map과 List로 상담 시작 기준 정렬해놓기
        // DFS를 토대로 Map과 계산하기 
        
        for(int[] req : reqs) {
            int s = req[0]; int e = req[1]; int type = req[2];
            map.computeIfAbsent(type, key -> new ArrayList<>()).
                add(new int[]{s, e});        
        }
        
        int[] arr = new int[k+1]; // 1~k 유형 인원수
        DFS(arr, 1, n-k);
        return answer;
    }
    
    public void DFS(int[] arr, int t, int remain) {
        if(t == arr.length) {
            if(remain == 0) {
               answer = Math.min(answer, cal(arr));
            }    
            return;
        }
        
        for(int i=0; i<=remain; i++) {
            arr[t] = i;
            DFS(arr, t+1, remain-i);
        }
    }
    
    public int cal(int[] arr) {
        int result = 0;
        
        int[] a = new int[arr.length];
        for(int i=1; i<arr.length; i++) { // 1씩 보정
            a[i] = arr[i]+1;
        }
        
        for(int i=1; i<a.length; i++) {  
            if(!map.containsKey(i)) continue;
            
            List<int[]> list = map.get(i);
            
            PriorityQueue<Integer> pq = new PriorityQueue<>(); // 빨리 끝나는 시간 기준 
            for(int j=0; j<list.size(); j++) {
                int[] user = list.get(j);
                if(pq.size() >= a[i]) {
                    int v = pq.poll();
                    if(user[0] < v) {
                        result += v - user[0];
                        pq.offer(v + user[1]);
                    }else {
                        pq.offer(user[0] + user[1]);                        
                    }
                }else {
                    pq.offer(user[0] + user[1]);
                }
            }
        }
        return result;
    }
}