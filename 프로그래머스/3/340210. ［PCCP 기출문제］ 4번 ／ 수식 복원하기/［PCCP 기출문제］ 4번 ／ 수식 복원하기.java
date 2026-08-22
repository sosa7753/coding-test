import java.util.*;
class Solution {
    public String[] solution(String[] expressions) {
        Set<Integer> set = new HashSet<>(); // 가능한 진법만 남기기
        
        int max = 0;
        List<String> listX = new ArrayList<>();
        List<String> nonX = new ArrayList<>();
        for(String e : expressions) {
            if(e.charAt(e.length()-1) == 'X') listX.add(e);
            else nonX.add(e);
            
            max = Math.max(max, maxNumber(e));              
        }
        
        for(int i=max+1; i<=9; i++) {
            set.add(i);
        }
        
        // 가능한 진법 체크
        boolean get = false;
        for(String e : nonX) {
            if(get) break;
            for(int i=max+1; i<=9; i++) {
                if(set.size() == 1) {
                    get = true;
                    break;
                }
                
                if(!set.contains(i)) continue;
                
                String[] str = e.split(" ");
                int one = toTen(i, Integer.parseInt(str[0]));
                int two = toTen(i, Integer.parseInt(str[2]));
                int three = toTen(i, Integer.parseInt(str[4]));
                if((str[1].equals("+") && (one + two == three)) || 
                   str[1].equals("-") && (one - two == three)) {
                    continue;
                }
                set.remove(i);
            }
        }
        
        String[] answer = new String[listX.size()];
        for(int i=0; i<listX.size(); i++) { // set을 순회해서 찾기
            answer[i] = cal(set, listX.get(i));
        }
        
        return answer;
    }
    
    public String cal(Set<Integer> set, String s) {
        Set<String> result = new HashSet<>();
        String[] str = s.split(" ");
        
        for(int t : set) {
            int one = toTen(t, Integer.parseInt(str[0]));
            int two = toTen(t, Integer.parseInt(str[2]));
            if(str[1].equals("+")) result.add(fromTen(t, (one + two)));
            else result.add(fromTen(t, (one - two)));
        }
        
        if(result.size() == 1) {
            for(String r : result) return s.replace("X", r);
        }
        return s.replace("X", "?");
    }
    
    public int maxNumber(String s) {
        int max = 0; 
        String[] str = s.split(" ");
        for(int i=0; i<=4; i=i+2) {
            if(str[i].equals("X")) break;
            
            for(char c : str[i].toCharArray()) {
                max = Math.max(max, c - '0');
            }
        }
        return max;
    }
    
    public int toTen(int cnt, int v) { // cnt 진법 -> 10진법
       int result = 0; 
       int idx = 1;
       while(v != 0) {
           result += (v%10) * idx;
           v /= 10;
           idx *= cnt;
       }
        return result;
    }
    
    public String fromTen(int cnt, int v) { // 10진법 -> cnt 진법 변환
        if(v == 0) return "0";
        StringBuilder sb = new StringBuilder();
        int idx = 1;
        while(v != 0) {
            sb.append(v%cnt);
            v /= cnt;
        }
        return sb.reverse().toString();  
    }
}