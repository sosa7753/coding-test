import java.util.*;
class Solution {
    int start = 0; // 최소 진법찾기
    List<Integer> list = new ArrayList<>(); // X가 포함된 행의 인덱스
    Set<Integer> set = new HashSet<>();
    public String[] solution(String[] expressions) {
        // 최소 진법 찾기
        for(String e : expressions) { 
            String[] str = e.split(" ");
            for(int i=0; i<=4; i+=2) {
                if(!str[i].equals("X")) {
                    for(int j=0; j<str[i].length(); j++) {
                        start = Math.max(start, (str[i].charAt(j)-'0') + 1);
                    }
                }
            }
        }
        
        // 최소 진법 이상을 set에 추가하기
        for(int i=start; i<=9; i++) {
            set.add(i);
        }
        
        // X 요소 추출하기
        for(int i=0; i<expressions.length; i++) {
            String s = expressions[i];
            if(s.charAt(s.length()-1) == 'X') list.add(i);
        }
        
        // 각 문자열로 가능한 진법 찾기
        for(String e : expressions) {
            String[] str = e.split(" ");
            if(str[4].equals("X")) continue;
            Set<Integer> tmp = new HashSet<>();
            for(int i=start; i<=9; i++) {
                int one = cal(str[0], i); 
                int two = cal(str[2], i);
                int three = cal(str[4], i);
                if((str[1].equals("+") && (one + two) == three) || 
                    (str[1].equals("-") && (one - two) == three)) {
                    tmp.add(i);
                } 
            }  
            
            if(tmp.size() == 1) {
                set = tmp;
                break;
            }else {
                Set<Integer> re = new HashSet<>();
                for(int s : set) {
                    if(!tmp.contains(s)) re.add(s);
                }
                set.removeAll(re);
                if(set.size() == 1) {
                    break;
                }
            }
        }
       
        String[] answer = new String[list.size()];
        
        int idx = 0;
        for(int l : list) { // 각 X가 포함된 문자열에 대해
            Set<String> result = new HashSet<>(); // 모든 진법에 대한 값
            String st = expressions[l];
            String[] str = st.split(" ");
            for(int s : set) { // 가능한 진법들
                int one = cal(str[0], s);
                int two = cal(str[2], s);        
                String v = (str[1].equals("+")) ? 
                    reverseCal(one+two, s) : reverseCal(one-two, s);
                result.add(v);
            }
            
            if(result.size() >=2) {
                answer[idx++] = st.replace("X", "?");
            }else {
                for(String r : result) {
                    answer[idx++] = st.replace("X", r);
                }  
            }
        }   
        return answer;
    }
    
    public int cal(String s, int op) {
        int v = 0;
        int idx = s.length()-1;
        int cnt = 0;
        while(cnt < s.length()) {
            v += (int)Math.pow(op, cnt) * (s.charAt(idx)-'0');
            idx--;
            cnt++;
        }
        return v;
    }
    
    public String reverseCal(int r, int op) {
        if(r == 0) return "0";
        
        StringBuilder sb = new StringBuilder();
        
        int cnt = 0;
        while(r >= (int)Math.pow(op, cnt)) {
            cnt++;
        }
        cnt--;
        
        for(int i=cnt; i>=0; i--) {
            sb.append(r/(int)Math.pow(op, i));
            r %= (int)Math.pow(op, i);
        }
        return sb.toString();
    }
}