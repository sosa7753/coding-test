import java.util.*;
import java.io.*;

class Main {
    static int N, M;
    static long[] arr, tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int size = 1;
        while (size < N) size <<= 1; // 리프 노드 시작 위치
        tree = new long[size * 2];
        arr = new long[N + 1]; // 필요 없지만 사용자 변수 유지 목적

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            if (t == 0) {
                int l = Math.min(s, e);
                int r = Math.max(s, e);
                sb.append(query(size + l - 1, size + r - 1)).append("\n");
            } else {
                update(size + s - 1, (long)e);
            }
        }

        System.out.print(sb);
    }

    static void update(int idx, long val) {
        tree[idx] = val;
        while (idx > 1) {
            idx >>= 1;
            tree[idx] = tree[idx << 1] + tree[(idx << 1) | 1];
        }
    }

    static long query(int l, int r) {
        long sum = 0;
        while (l <= r) {
            if ((l & 1) == 1) sum += tree[l++];
            if ((r & 1) == 0) sum += tree[r--];
            l >>= 1;
            r >>= 1;
        }
        return sum;
    }
}