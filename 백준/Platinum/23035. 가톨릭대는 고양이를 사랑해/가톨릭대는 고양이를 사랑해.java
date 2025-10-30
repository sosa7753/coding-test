import java.util.*;
import java.io.*;
class Main {
    static int N,M,T;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(br.readLine());
        
        List<int[]> list = new ArrayList<>();
        for(int i=0; i<T; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if(r > N || c > M) continue;
            
            list.add(new int[]{r, c});
        }
        
        Collections.sort(list, (x,y) -> { // 행이 작은순, 같다면 열이 작은 순 
            if(x[0] == y[0]) {
                return x[1] - y[1];
            }else {
                return x[0] - y[0];
            }
        });
        
        List<Integer> lis = new ArrayList<>();
        for(int[] l : list) {
            int x = l[1];
            int idx = upperBound(lis, x); // 첫 > x 위치 
            if(idx == lis.size()) {
                lis.add(x);
            }else {
                lis.set(idx, x);
            }
        }
        System.out.print(lis.size());
    }
    
    public static int upperBound(List<Integer> a, int x) { // 첫 번째로 값이 x보다 큰 위치 
        int l = 0; int r = a.size();
        while(l < r) {
            int m  = (l+r) >>> 1;
            if(a.get(m) <= x) {
                l = m + 1;
            }else {
                r = m;
            }
        }
        return l;
    }
}