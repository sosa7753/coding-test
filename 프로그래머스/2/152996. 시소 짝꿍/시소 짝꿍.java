import java.util.*;
class Solution {
    int w;
    public long solution(int[] weights) {
        w = weights.length;
        long answer = 0;
        
        Arrays.sort(weights);
        
        int prev = 0;
        for(int i=0; i< w-1; i++) {
            if(i > 0 && weights[i] == weights[i-1]) { // 중복체크
                prev--;
                answer+=prev;
                continue;
            }
        
            int max = binarySearch(weights, weights[i], i+1); // i+1 ~ 범위찾기
            prev = 0;
            
            // 구간 내에서 조건에 맞는 짝꿍 찾기 
            for(int j=i+1; j<max; j++) {
                if(weights[i] == weights[j] || weights[i] * 2 == weights[j] ||
                   weights[i] * 3 == weights[j] * 2 || 
                   weights[i] * 4 == weights[j] * 3) {
                    prev++;
                }
            }
            answer+=prev;
             
        }
        return answer;
    }
    
    // 최대 범위 찾기 
    public int binarySearch(int[] weights, int num, int i) {
        int left = i;
        int right = w-1;
        
        while(left <= right) {
            int mid = left + (right -left)/2;
            if(weights[mid] > num * 2) {
                return mid;
            }else {
                left = mid + 1;
            }
        }
        
        return left;
    }
}