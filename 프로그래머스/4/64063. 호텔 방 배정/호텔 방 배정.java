import java.util.*;
class Solution {
    Map<Long, Long> map = new HashMap<>();
    public long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];

        for(int i=0; i<room_number.length; i++) {
            answer[i] = assign(room_number[i]);
        }
        return answer;
    }
    
    public long assign(long num) {
        if(!map.containsKey(num)) {
            map.put(num, num+1);
            return num;
        }
        
        long room = assign(map.get(num));
        map.put(num, room);
        return room;      
   }
}
