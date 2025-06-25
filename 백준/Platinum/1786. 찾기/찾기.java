import java.util.*;
import java.io.*;
class Main {
    static String T,P;
    static Stack<Integer> stack = new Stack<>(); // 어느 위치 인지
    static int answer = 0; // 몇 번 나타나는지
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        T = br.readLine();
        P = br.readLine();
        
        int[] pattern = pattern();
        find(pattern);
        
        System.out.println(answer);
        
        StringBuilder sb = new StringBuilder();
        for(int idx : stack) {
            sb.append(idx).append(" ");
        }   
        System.out.print(sb.toString());
    }
    
    public static void find(int[] p) {
        int n = T.length();
        int m = P.length();
        int j = 0;
        
        for(int i=0; i<n; i++) {     
            while(j > 0 && T.charAt(i) != P.charAt(j)) {
                j = p[j-1];
            } 
            
            if(T.charAt(i) == P.charAt(j)) {
                if(j == m - 1) {
                    answer++;
                    stack.push(i - m + 2);
                    j = p[j];
                }else {
                    j++;
                }
            }
        }
    }
    
    public static int[] pattern() { 
        int n = P.length();
        int[] p = new int[n]; // 실패시 돌아가야할 위치
        
        int idx = 0;
        for(int i=1; i<n; i++) {
            while(idx > 0 && P.charAt(i) != P.charAt(idx)) {
                idx = p[idx-1];
            }
            
            if(P.charAt(i) == P.charAt(idx)) {
                p[i] = ++idx;
            }
        }
        return p;
    }
}