import java.util.*;
class Solution {
    // Map에 단어 저장. 
    public int[] solution(int n, String[] words) {
        int[] result = new int[2];
        Map<String, Integer> map = new HashMap<>();
        
        int[] answer = new int[n+1]; // n번째 사람의 차례 값.
        for(int i=1; i<=n; i++) {
            answer[i] = 1;
        }
        int cnt = 1;
        String pre = ""; 
        boolean check = true;
        for(int i=0; i<words.length; i++) {
            if(pre == "") { // 처음이면 패스 
                pre = words[i];
                int tmp = cnt%n;
                if(tmp==0) {
                    answer[n]++;
                }else {
                    answer[tmp]++;
                }
                cnt++;        
                map.put(words[i], 1);
                continue;
            }
            
            // 끝이 같지 않거나, 같아도 map에 있으면 gg
            if(pre.charAt(pre.length()-1) != words[i].charAt(0)) {
                check = false;
                int a = 0;
                if(cnt%n == 0) {
                    a = n;
                }else {
                    a = cnt%n;
                }
                result[0] = a;
                result[1] = answer[a];
                break;
            } 
            
            if(map.containsKey(words[i])) {
                check = false;
                int a = 0;
                if(cnt%n == 0) {
                    a = n;
                }else {
                    a = cnt%n;
                }
                result[0] = a;
                result[1] = answer[a];
                break;
            }
            
            map.put(words[i], 1);
            pre = words[i];
            int b = 0 ;
            if(cnt%n ==0) {
                b = n;
            }else {
                b = cnt%n;
            }
            answer[b]++;
            cnt++;                  
        }
        
        if(check) {
            return new int[] {0, 0};
        }

        return result;
    }
}