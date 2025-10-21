import java.util.*;
class Solution {
    public int solution(int[][] jobs) {
        PriorityQueue<Task> pq = new PriorityQueue<>((x,y) -> {
            if(x.len == y.len) {
                if(x.s == y.s) {
                    return x.num - y.num;
                }else {
                    return x.s - y.s;
                }
            }else {
                return x.len - y.len;
            }
        });
        
        int n = jobs.length;
        Arrays.sort(jobs, (x,y) -> (x[0] - y[0]));
        // last >= s면, 끝나기 전에 작업이 추가 되어야하니까 pq에 넣기 continue;
        // last < s면? last를 answer에 추가하고, 다음 pq.peek값으로 업데이트 (반복)
        // 결국 last >= s 인 순간까지 온다 -> pq에 넣기 
        // 마지막에 pq 빼면서 다 추가해주기
        
        
        int answer = 0;
        int last = 0;
        int idx = 0;
        int cnt = 0;
        while(cnt < n) { 
            while(idx < n && jobs[idx][0] <= last) { // 현재 시각보다 빠르게 들어옴
                pq.offer(new Task(jobs[idx][1], jobs[idx][0], idx));
                idx++;
            }
            
            if(pq.isEmpty()) {
                last = Math.max(last, jobs[idx][0]); 
                continue;
            }
            
            Task cur = pq.poll(); // 가장 짧은 작업 하나 처리
            last += cur.len;
            answer += last - cur.s;
            cnt++;
        }
        
        return answer/n;
    }
}

class Task {
    int len;
    int s;
    int num;
    Task(int len, int s, int num) {
        this.len = len;
        this.s = s;
        this.num = num;
    }
}