import java.util.*;
import java.io.*;
class Main {
    static int[] dx = {1, -1, -1, 1};
    static int[] dy = {1, 1, -1, -1};
    static int N,M,L,K;
    static class Node {
        int x;
        int y;
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken()); // 열
        M = Integer.parseInt(st.nextToken()); // 행
        L = Integer.parseInt(st.nextToken()); // 한변 길이
        K = Integer.parseInt(st.nextToken()); // 별동별 수
        
        Node[] arr = new Node[K];
        for(int i=0; i<K; i++) {
            String[] s = br.readLine().split(" ");
            arr[i] = new Node(Integer.parseInt(s[0]), Integer.parseInt(s[1]));
        }
        
        int answer = 0;
        for(int i=0; i<K; i++) { // 첫 번째 별
            for(int j=0; j<K; j++) { // 두 번째 별
                int sx = arr[i].x;
                int sy = arr[j].y;
                
                int cnt = 0;
                for(int k=0; k<K; k++) { // 포함되는 별 검사
                    int tx = arr[k].x; int ty = arr[k].y;
                    if(sx <= tx && tx <= sx+L && sy <= ty && ty <= sy + L) cnt++;
                }
                
                answer = Math.max(cnt, answer);
            }
        }
        
        System.out.print(K - answer);
    }
}