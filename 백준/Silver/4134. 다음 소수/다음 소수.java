import java.util.*;
import java.math.*;
import java.io.*;
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int t = Integer.parseInt(br.readLine()); // 테스트 케이스
        
        for(int i=0; i<t; i++) { // 테스트 반복
            BigInteger bi = new BigInteger(br.readLine());
            
            // n이랑 같거나 n보다 큰 소수
            if(bi.isProbablePrime(10)) {
                System.out.println(bi);
            }else {
                System.out.println(bi.nextProbablePrime());
            }
        }
    }
}