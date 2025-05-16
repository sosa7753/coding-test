import java.util.*;
class Solution {
    public int solution(int[] food_times, long k) {      
        PriorityQueue<int[]> pq = new PriorityQueue<>((x,y) -> (x[0] - y[0]));
            
        for(int i=0; i<food_times.length; i++) {
            pq.add(new int[]{food_times[i], i+1});
        }
            
        long cnt = k;
        int min = 0;
        List<Integer> backup = new ArrayList<>();
        while(cnt > 0 && !pq.isEmpty()) {
            backup = new ArrayList<>();
            int mul =  pq.peek()[0] - min;
            cnt -= (long)mul * pq.size();
            
            min = pq.peek()[0];
            while(!pq.isEmpty() && pq.peek()[0] == min) {
                int[] tmp = pq.poll();
                backup.add(tmp[1]);
            }
        }
        
        if(cnt > 0 || (cnt == 0 && pq.isEmpty())) {
            return -1;
        }
                
        int[] index = new int[backup.size() + pq.size()];
        int len = index.length;
        
        int c = 0;
        for(int i=0; i<backup.size(); i++) {
            index[c++] = backup.get(i);
        }
        
        while(!pq.isEmpty()) {
            index[c++] = pq.poll()[1];   
        }
        Arrays.sort(index);

        int idx = (((int)cnt % len) + len)%len;
        return index[idx];
    }
}