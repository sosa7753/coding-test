import java.util.*;
import java.io.*;
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int L = Integer.parseInt(br.readLine());
        String s = br.readLine();
        int n = s.length();
        
        int[] p = pattern(s);
        
        System.out.print(n - p[n-1]);
    }
    
    public static int[] pattern(String s) {
        int n = s.length();
        int[] p = new int[n];
        int j=0;
        
        for(int i=1; i<n; i++) {
            while(j > 0 && s.charAt(i) != s.charAt(j)) {
                j = p[j-1];
            }
            
            if(s.charAt(i) == s.charAt(j)) {
                p[i] = ++j;
            } 
        }
        return p;
    } 
}