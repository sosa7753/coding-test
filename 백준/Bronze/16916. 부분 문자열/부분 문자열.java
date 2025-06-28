import java.util.*;
import java.io.*;
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String S = br.readLine();
        String P = br.readLine();
        
        int[] f = pattern(P);       
        System.out.print(find(S, P, f));
        
    }
    
    public static int find(String S, String P, int[] f) {
        int n = S.length();
        int m = P.length();
        int j = 0;
        
        for(int i=0; i<n; i++) {
            while(j > 0 && S.charAt(i) != P.charAt(j)) {
                j = f[j-1];
            }
            
            if(S.charAt(i) == P.charAt(j)) {
                if(j == m-1) {
                    return 1;
                }
                j++;
            }
        }
        return 0;
    }
    
    public static int[] pattern(String P) {
        int n = P.length();
        int[] p = new int[n];
        
        int j=0;
        for(int i=1; i<n; i++) {
            while(j > 0 && P.charAt(i) != P.charAt(j)) {
                j = p[j-1];
            }
            
            if(P.charAt(i) == P.charAt(j)) {
                p[i] = ++j;
            }
        }
        return p;
    }
}