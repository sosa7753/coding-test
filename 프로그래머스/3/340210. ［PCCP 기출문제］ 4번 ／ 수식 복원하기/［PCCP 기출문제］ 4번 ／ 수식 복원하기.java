import java.util.*;
class Solution {
    public String[] solution(String[] expressions) {
        int max = 0;
        for(String e : expressions) { // 등장하는 숫자들 중 최댓값 찾기
            for(char c : e.toCharArray()) {
                if(Character.isDigit(c)) max = Math.max(max, c-'0');
            }
        }
        
        List<Integer> valid = new ArrayList<>(); // 가능한 진법만 모으기
        for(int i = max+1; i<=9; i++) {
            if(isValid(i, expressions)) valid.add(i);
        }
     
        List<String> answer = new ArrayList<>();
        for(String e : expressions) {
            String[] str = e.split(" ");
            if(!str[4].equals("X")) continue;
            answer.add(e.replace("X", resolve(str, valid)));
        }
        
        return answer.toArray(new String[0]);
    }
    
    public boolean isValid(int op, String[] expressions) {
        for(String e : expressions) {
            String[] str = e.split(" ");
            if(str[4].equals("X")) continue;
            int cal = calc(str, op);
            if(cal != toTen(str[4], op)) return false;
        }
        return true;
    }
    
    public String resolve(String[] str, List<Integer> valid) {
        Set<String> results = new HashSet<>();
        for(int l : valid) results.add(fromTen(calc(str, l), l));
        return results.size() == 1 ? results.iterator().next() : "?";
    }
    
    public int calc(String[] str, int op) {
        int a = toTen(str[0], op); 
        int b = toTen(str[2], op);
        return str[1].equals("+") ? a+b : a-b;
    }
    
    public int toTen(String num, int op) { // 한 칸씩 밀고, op 진법을 곱해주는 방법
        int result = 0;
        for(char c : num.toCharArray()) result = result * op + (c - '0');
        return result;
    }
    
    public String fromTen(int v, int op) {
        if(v == 0) return "0";
        StringBuilder sb =  new StringBuilder();
        while(v > 0) {
            sb.append(v%op);
            v /= op;
        }
        return sb.reverse().toString();
    }
}