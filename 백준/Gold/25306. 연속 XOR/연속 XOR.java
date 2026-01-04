import java.util.*;
import java.io.*;
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        
        String[] str = s.split(" ");
        long start = Long.parseLong(str[0]);
        long end = Long.parseLong(str[1]);
        
        long answer = 0;
        
        long now = start;
        // start 보정
        if(start % 4 !=0) {
            long v = start;
            while(v % 4 != 0 && v <= end) {
                answer = answer^v;
                v++;
            }  
            now = v;
        }
        
        // end 보정 
        if(end % 4 != 3) {
           long v = end;
           while(v % 4 != 3 && v >= now) {
               answer = answer^v;
               v--;
           } 
        }
        
        System.out.println(answer);                                          
    }
}