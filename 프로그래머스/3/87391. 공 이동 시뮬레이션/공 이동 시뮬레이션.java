class Solution {
    int[] dr = {0, 0, -1, 1};
    int[] dc = {-1, 1, 0, 0};
    int r1, c1, r2, c2; // 가능한 사각형 범위
    int m, n;
    public long solution(int n, int m, int x, int y, int[][] queries) {
        int k = queries.length;    
        this.m = m;
        this.n = n;
        r1 = x; c1 = y; r2 = x; c2 = y;

        for(int i=k-1; i>=0; i--) {
            int[] q = queries[i];
            if(!move(q)) return 0;     
        }
            
        return (long)(r2 - r1 + 1) * (long)(c2 - c1 + 1);
    }
    
    public boolean move(int[] query) {
        int d = query[0]; // 방향
        int s = query[1]; // 가는 길

        if(d == 0) { // 열 감소
            c2 = Math.min(c2+s, m-1);
            if(c1 != 0) {
                c1 += s;
                if(c1> m-1) return false;
            }
        }else if(d == 1) { // 열 증가
            c1 = Math.max(c1-s, 0);
            if(c2 != m-1) {
                c2 -=s;
                if(c2 < 0) return false;
            }
        }else if(d == 2) { // 행 감소
            r2 = Math.min(r2+s, n-1);
            if(r1 != 0) {
                r1 += s;
                if(r1 > n-1) return false;
            }
        }else {
            r1 = Math.max(r1-s, 0);
            if(r2 != n-1) {
                r2 -= s;
                if(r2 < 0) return false;
            }
        }
        return true;
    } 
}