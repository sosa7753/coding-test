import java.util.*;
import java.util.stream.*;
class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        
        int answer = n;
        
        List<Integer> lostList = new ArrayList<>();
        List<Integer> reserveList = new ArrayList<>();
        
        for(int r : reserve) {
            reserveList.add(r);
        }
        
        
        for(int l : lost) {
            if(reserveList.contains(l)) { // 도난당한 사람이 여벌을 가지고 있을경우 
                reserveList.remove(Integer.valueOf(l));
                continue;
            }
            lostList.add(l);
        }
        
        
        Collections.sort(lostList);
        Collections.sort(reserveList); 
        
        int lostLen = lostList.size();
        answer = answer - lostLen;
        
        for(int i=0; i<lostLen; i++) {
            int value = lostList.get(i);
            
            if(reserveList.contains(value-1)) { // 이전 번호가 빌려줄 수 있음.
                answer++;
                reserveList.remove(Integer.valueOf(value-1));
                continue;              
            }
            
            if(reserveList.contains(value+1)) {
                answer++;
                reserveList.remove(Integer.valueOf(value+1));
                continue;
            }
        }
        
        return answer;
    }
}