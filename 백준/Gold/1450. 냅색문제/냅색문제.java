import java.util.*;
import java.io.*;
class Main {
    static int N, C;
    static List<Integer> left, right, ls, rs;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        left = new ArrayList<>();
        right = new ArrayList<>();
        ls =  new ArrayList<>();
        rs = new ArrayList<>();
        
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            if(i<N/2) {
                left.add(Integer.parseInt(st.nextToken()));
                continue;
            }
            right.add(Integer.parseInt(st.nextToken()));
        }
        
        DFS(left, ls, 0, 0);
        DFS(right, rs, 0, 0);
        
        Collections.sort(ls);
        
        long answer = 0;
        for(int i=0; i<rs.size(); i++) {
            int l = 0;
            int r = ls.size()-1;
            int mid = 0;
            int result = 0;
            while(l<=r) {
                mid = (l+r)/2;
                if(ls.get(mid) + rs.get(i) <= C) {
                    l = mid+1;
                    result = mid;
                }else {
                    r = mid - 1;
                }
            }
            answer += result + 1;
        }
        
        System.out.print(answer);
    }
    
    public static void DFS(List<Integer> list, List<Integer> result, int idx, int val) {
        if(val > C) {
            return;
        }
        
        if(idx == list.size()) {
            result.add(val);
            return;
        }
        
        DFS(list, result, idx+1, val+list.get(idx));
        DFS(list, result, idx+1, val);        
    }
}