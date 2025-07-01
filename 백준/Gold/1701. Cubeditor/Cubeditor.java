import java.util.*;
class Main {
    public static void main(String[] args) {
        int answer = 0; 
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        
        int n = s.length();
        int[] p = new int[n];
        
        int j = 0;
        for(int i=0; i<n; i++) {
            String str = s.substring(i);
            answer = Math.max(answer, pattern(str));
        }
        System.out.print(answer);    
    }
    
    public static int pattern(String s) {
        int n = s.length();
        int[] p = new int[n];
        int j = 0;
        
        int answer = 0;
        for(int i=1; i<n; i++) {
            while(j > 0 && s.charAt(i) != s.charAt(j)) {
                j = p[j-1];          
            }
            
            if(s.charAt(i) == s.charAt(j)) {
                p[i] = ++j;
                answer = Math.max(answer, p[i]);
            }
        } 
        return answer;
    }
}