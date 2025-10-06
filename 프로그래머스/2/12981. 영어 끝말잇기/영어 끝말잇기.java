import java.util.*;
class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];
        
        Set<String> set = new HashSet<>();
        set.add(words[0]);
        String pre =  words[0];
        for(int i=1; i<words.length; i++) {
            if((pre.charAt(pre.length()-1) != words[i].charAt(0)) ||
              set.contains(words[i])) {
                if((i+1)%n == 0) {
                    answer[0] = n;
                }else {
                    answer[0] = (i+1)%n;
                }
                answer[1] = i/n + 1;
                break;
            }
            
            pre = words[i];
            set.add(words[i]);
        }

        return answer;
    }
}