class Solution {
    public String solution(String[] seoul) {
        StringBuilder sb = new StringBuilder();
        
        sb.append("김서방은 ");
        
        int get = -1;
        for(int i=0; i<seoul.length; i++) {
            if("Kim".equals(seoul[i])) {
                get = i;
                break;
            }
        }
        
        sb.append(get);
        sb.append("에 있다");
        return sb.toString();
    }
}