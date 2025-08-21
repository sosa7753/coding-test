import java.util.*;
class Solution {
    public int solution(int[] people, int limit) {
        
        // 정렬을 해서, 가장 큰사람과 탈 수 있는지 확인
        // 탈 수 없으면 그 다음 사람, answer++;
        // 탈 수 있으면 양쪽 가까워짐, answer++;
        
        Arrays.sort(people);
        int l = 0;
        int r = people.length-1;
        
        int answer = 0;
        while(l < r) {
            int sum = people[l] + people[r];
            if(sum <= limit) {
                l++;
            }
            r--;
            answer++;
            
            if(l == r) {
                answer++;
            }
        }
            
        return answer;
    }
}