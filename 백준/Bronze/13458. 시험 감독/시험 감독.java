import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N]; // 각 시험장 인원 수 
              
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        st = new StringTokenizer(br.readLine());
        int F = Integer.parseInt(st.nextToken()); // 총시험 감독관 감시자 수
        int S = Integer.parseInt(st.nextToken()); // 부시험 감독관 감시자 수
        
        long answer = 0;
        for(int i=0; i<arr.length; i++) {                   
            answer++;
            arr[i] -=F;
            
            if(arr[i] > 0) {
               answer += (arr[i] -1)/S + 1;    
            }                
        }        
        System.out.println(answer); 
    }
}