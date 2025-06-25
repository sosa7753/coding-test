import java.util.*;
import java.io.*;
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String str;
        while(!(str = br.readLine()).equals(".")) {
            int n = str.length();
            int[] pi = pattern(str);
            
            int lastP = pi[n-1];
            int min = n - lastP;
            
            if(n % min == 0) {
                bw.write(String.valueOf(n/min));
            }else {
                bw.write("1");        
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }
    
    public static int[] pattern(String p) {
        int n = p.length();
        int[] pi = new int[n];
        int j=0; 
        
        for(int i=1; i<n; i++) {
            while(j > 0 && p.charAt(i) != p.charAt(j)) {
                j = pi[j-1];
            }
            
            if(p.charAt(i) == p.charAt(j)) {
                pi[i] = ++j;
            }
        }
        return pi;
    }
}