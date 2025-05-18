import java.util.*;
class Solution {
    PriorityQueue<Node> pq = new PriorityQueue<>((x,y) -> {
        if(x.task == y.task) {
            if(x.start == y.start) {
                return x.num - y.num;
            }else {
                return x.start - y.start;
            }
        }else {
            return x.task - y.task;
        }
    });
    public int solution(int[][] jobs) {
        int answer = 0;
        
        PriorityQueue<Node> init = new PriorityQueue<>((x,y) -> {
            if(x.start == y.start) {
                if(x.task == y.task) {
                    return x.num - y.num;
                }else {
                    return x.task - y.task;
                }
            }else {
                return x.start - y.start;
            }
        });
        
        for(int i=0; i<jobs.length; i++) {
            init.offer(new Node(i, jobs[i][0], jobs[i][1]));
        }
        
        int time = 0; // 끝나는 작업 시간 
        while(!pq.isEmpty() || !init.isEmpty()) {
            if(pq.isEmpty()) {
                pq.offer(init.poll());          
            }
            
            Node now = pq.poll();
            if(time < now.start) {
                time = now.start;
            }
                      
            // 대기 작업 추가
            while(!init.isEmpty()) {
                if(time + now.task >= init.peek().start) {
                    pq.offer(init.poll());
                }else {
                    break;
                }
            }
            
            int value = time + now.task - now.start;
            answer += value;
            time += now.task;
        }
            
        return answer/jobs.length;
    }
}

class Node {
    int num;
    int start;
    int task;
    Node(int num, int start, int task) {
        this.task = task;
        this.start = start;
        this.num = num;
    }
}