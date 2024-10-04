import java.util.*;
class Solution {
    public int solution(int distance, int[] rocks, int n) {
        if(rocks.length == n) {
            return distance;
        }
                
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=0; i<rocks.length; i++) {
            pq.add(rocks[i]);
        }
        
        int[] arr = new int[rocks.length+1];
        int idx = 0;
        while(!pq.isEmpty()) {
            arr[idx++] = pq.poll();
        }
        
        arr[idx] = distance;
        
        int left = 1;
        int right = distance -1;
        
        while(left <= right) {
            int mid = right - (right - left)/2; // 최솟값
            
            boolean isfalse = false; // true면 불가능 
            int remove = n;
            int pre = 0;
            for(int i=0; i<arr.length; i++) {
                if(arr[i] - pre < mid) {
                    if(remove == 0) {
                        isfalse = true; 
                        break;
                    }else {
                        remove--;
                    }
                }else {
                    pre = arr[i];
                }
            }
                       
            if(isfalse) { // 불가능하면, 더 작은 값으로 찾아야함.
                right = mid -1;
            }else {
                left = mid + 1;
            }
        }
        
        return left - 1;
    }
}