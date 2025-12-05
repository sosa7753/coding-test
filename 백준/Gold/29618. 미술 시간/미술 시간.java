import java.util.*;
import java.io.*;
class Main {
    static int N, Q;
    static int[] answer, next;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        answer = new int[N+1];
        next = new int[N+2]; // N+1 인덱스는 탈출 조건 
        
        for(int i=1; i<=N+1; i++) {
            next[i] = i;
        }
        
        for(int i=0; i<Q; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            
            int pos = find(s);
            while(pos <= e) {
                answer[pos] = v; // 칠할 곳이 남아있으면 색칠하기
                int old = pos; // old는 현재 칠한 idx
                pos = find(pos+1); // 다음 위치의 안칠해진 구간으로 업데이트
                next[old] = pos; // 그곳으로 현재 idx에서 연결하기
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=N; i++) {
            sb.append(answer[i]).append(' ');
        }
        System.out.print(sb);
    }
    
    public static int find(int x) {
        if(next[x] == x) return x;
        return next[x] = find(next[x]);
    }
}