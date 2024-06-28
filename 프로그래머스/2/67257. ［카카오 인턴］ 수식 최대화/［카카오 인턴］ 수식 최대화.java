class Solution {
    String[] cal = {"+", "-", "*"};
    boolean[] visited = new boolean[3];
    long max = 0;
    public long solution(String expression) {
      
        String[] arr = new String[3];
        DFS(arr, expression, 0);
        return max;
    }
    
    public void DFS(String[] arr, String expression, int depth) {
        if(depth == arr.length) {
            result(arr, expression, new boolean[3], 0);
            return;
        }
        
        for(int i=0; i<3; i++) {
            if(visited[i]) {
                continue;
            }
            arr[depth] = cal[i];
            visited[i] = true;
            DFS(arr, expression, depth+1);
            visited[i] = false;
        }
    }
    
    public void result(String[] arr, String expression, boolean[] visited, int cnt) {
        if(cnt == 3) {
            max = Math.max(Math.abs(Long.parseLong(expression)), max);
            return;
        }
        visited[cnt] = true;
        
        String str = expression;
            
        // +, -, *이 되었다 하자.
        
        if(!visited[(cnt+1)%3]) {
            String[] s1 = str.split("\\" + arr[(cnt+1)%3]); // -로 나뉜 값
            for(String s11 : s1) {
                if(!visited[(cnt+2)%3]) {
                    String[] s2 = s11.split("\\" + arr[(cnt+2)%3]); // 각 *로 나뉜 값.
                    for(String s22 : s2) {  // 각각을 +로 계산 
                        if(s22.contains(arr[cnt%3])) {
                            long tmp = calcul(s22, arr[cnt%3]);
                            str = str.replace(s22, String.valueOf(tmp));
                        }                 
                    }
                }else {
                    if(s11.contains(arr[cnt%3])) {
                        long tmp = calcul(s11, arr[cnt%3]);
                        str = str.replace(s11, String.valueOf(tmp));
                    } 
                }
            }
        }else {
            if(str.contains(arr[cnt%3])) {
                long tmp = calcul(str, arr[cnt%3]);
                str = str.replace(str, String.valueOf(tmp));
            }
        }
        
        result(arr, str, visited, cnt+1);
    }
    
    public long calcul(String s, String operator) {
        String[] str = s.split("\\" + operator);
        
        long value = 0;
        for(int i=0; i<str.length; i++) {
            if("+".equals(operator)) {
                value += Long.parseLong(str[i]);
            }else if("-".equals(operator)) {
                if(i==0) {
                    value += Long.parseLong(str[i]);
                }else {
                    value -= Long.parseLong(str[i]);
                }                
            }else {
                if(i == 0) {
                    value = 1;
                }
                value = value * Long.parseLong(str[i]);
            }
        }
        return value;
        
    }
}