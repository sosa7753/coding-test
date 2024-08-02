import java.util.*;
class Solution {
    public int solution(int[] array) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<array.length; i++) {
            map.put(array[i], map.getOrDefault(array[i], 0) + 1);
        }
        
        int cnt = 1;
        int max = Integer.MIN_VALUE;
        int answer = 0;
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if(max < entry.getValue()) {
                cnt = 1;
                max = entry.getValue();
                answer = entry.getKey();
            }else if(max == entry.getValue()) {
                cnt++;
            }
        }
        
        if(cnt >=2) {
            return -1;
        }
        return answer;
    }
}