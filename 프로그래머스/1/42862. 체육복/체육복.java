// 모든 학생 수에서 못 받는 학생은 -- 
// 같은 값은 미리 제거 
// lost값을 만났다면 체크
// 작은 값, 큰 값이 reserve에 있다. 작은값 사용 
// 각각 하나씩 있다. 하나 사용 
// 그 외 -- 
import java.util.*;
class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = n;
            
        // reserve  저장 
        List<Integer> r = new ArrayList<>();
        for(int i=0; i<reserve.length; i++) {
            r.add(reserve[i]);
        }
        
        // lost 저장
        List<Integer> l = new ArrayList<>();
        for(int i=0; i<lost.length; i++) {
            if(r.contains(lost[i])) {
                r.remove(Integer.valueOf(lost[i]));
                continue;
            }
            l.add(lost[i]);
        }
        
        Collections.sort(r);
        Collections.sort(l);
        
        // lost 순회 
        for(int i=0; i<l.size(); i++) {
            int target = l.get(i);
            
            if(r.contains(target-1)) { // 작은 값
                while(!r.isEmpty() && r.get(0) <= target - 1) {
                    r.remove(0);
                }
            }else if(r.contains(target+1)) { // 큰 값
                while(!r.isEmpty() && r.get(0) <= target + 1) {
                    r.remove(0);
                }
            }else { // 양쪽이 없음.
                answer--;
            }
        }
        
        return answer;
    }
}