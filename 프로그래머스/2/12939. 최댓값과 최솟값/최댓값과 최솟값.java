class Solution {
    int max = Integer.MIN_VALUE;
    int min = Integer.MAX_VALUE;
    public String solution(String s) {
        String answer = "";
        
        String[] str = s.split(" ");
        for(int i=0; i<str.length; i++) {
            max = Math.max(max, Integer.parseInt(str[i]));
            min = Math.min(min, Integer.parseInt(str[i]));
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(String.valueOf(min));
        sb.append(" ");
        sb.append(String.valueOf(max));
        
        return sb.toString();
    }
}