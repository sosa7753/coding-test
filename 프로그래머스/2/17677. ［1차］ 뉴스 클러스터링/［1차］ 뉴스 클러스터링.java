import java.util.*;
class Solution {
    public int solution(String str1, String str2) {
        String s1 = str1.toUpperCase();
        String s2 = str2.toUpperCase();

        List<String> one = new ArrayList<>();
        List<String> two = new ArrayList<>();
              
        for(int i=0; i<s1.length()-1; i++) {
            String str = s1.substring(i, i+2);
            if(('A' <= str.charAt(0) && str.charAt(0) <= 'Z') &&
                ('A' <= str.charAt(1) && str.charAt(1) <= 'Z')) {
                one.add(str);
            }
        }
        
        for(int i=0; i<s2.length()-1; i++) {
            String str = s2.substring(i, i+2);
            if(('A' <= str.charAt(0) && str.charAt(0) <= 'Z') &&
                ('A' <= str.charAt(1) && str.charAt(1) <= 'Z')) {
                two.add(str);
            }
        }
        
        List<String> list = new ArrayList<>(two);
              
        int answer = 65536;
        if(one.size() == 0 && two.size() == 0) {
            return answer;
        }
        
        int dup = 0;
        for(String s : one) {
            String k = "";
            for(String t : list) {
                if(t.equals(s)) {
                    dup++;
                    k = t;
                    break;
                }
            } 
            list.remove(k);
        }
 
        answer *= dup;
        answer /= (one.size() + two.size() - dup);
        return answer;
    }
}