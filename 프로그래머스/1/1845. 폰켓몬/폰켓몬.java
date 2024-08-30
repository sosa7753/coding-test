import java.util.*;
class Solution {
    public int solution(int[] nums) {
        Set<Integer> set = new HashSet<>();
        
        for(int number : nums) {
            set.add(number);
        }
        
        int answer = set.size();
        if(nums.length/2 >= answer) {
            return answer;
        }
        
        return nums.length/2;
    }
}