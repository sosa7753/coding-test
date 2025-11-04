import java.util.*;
import java.io.*;
import java.math.BigInteger;
class Main {
    static int max = 1000;
    static BigInteger[] H3 = new BigInteger[max+1];
    static BigInteger[] H4 = new BigInteger[max+1];
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        H3[0] = BigInteger.ZERO;
        for(int i=1; i<=max; i++) {
            H3[i] = BigInteger.valueOf(2).pow(i).subtract(BigInteger.ONE);
        }
        
        H4[0] = BigInteger.ZERO;
        H4[1] = BigInteger.ONE;
        for(int i=2; i<=max; i++) {
            BigInteger best = null;
            for(int t=1; t<i; t++) {
                BigInteger cand = H4[t].shiftLeft(1).add(H3[i-t]);
                if(best == null || cand.compareTo(best)< 0) best = cand;
            }
            H4[i] = best;
        }
        
        
        String str;
        int idx = 1;    
        while((str = br.readLine()) != null && !str.isEmpty()) {
            int n = Integer.parseInt(str.trim());
            bw.write("Case " + idx++ + ": " + H4[n] + "\n");
        }
        bw.flush();
    }                   
}