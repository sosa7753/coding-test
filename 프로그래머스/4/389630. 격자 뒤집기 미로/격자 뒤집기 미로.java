class Solution {
    int[] allCol, sumCol, visitR;
    int n, m;
    int answer = 0;
    public int solution(int[][] visible, int[][] hidden, int k) {
        n = visible.length;
        m = visible[0].length;
        // 열별 전체 합 을 1차원 배열로 만들기
        // DFS로 2가지 기록하기 
            // 어느 행을 뒤집었는지 (1차원 배열)
            // 현재의 map에서 열을 뒤집기 전 열의 합(1차원 배열)
                // 시작 값 allcol을 복사하고, 안뒤집었다 = 뒤집힌 값 제거 / 뒤집었다 = 안뒤집힌 값 제거
        
        // 열을 순회해서 최적화하기 (allCol에서 만들어진 열의 합 vs allCol -열의합 - k) 
        // -> 전체 값은 X
        // 짝짝인 경우,  for(n*m/2) 를 순회해서 해당 열라인을 다시 보자
            // 이 때, 각 최적화 값을 저장해 놨다가, 전체 값에서 제거해 줘야함.
        
        allCol = new int[m];
        sumCol = new int[m];
        for(int i=0; i<m; i++) { // 열 순회
            int ac = 0;
            int sc = 0;
            for(int j=0; j<n; j++) { // 행 순회
                ac += visible[j][i] + hidden[j][i];
                sc += visible[j][i];
            }
            allCol[i] = ac;
            sumCol[i] = sc;
        }
            
        visitR = new int[n]; // 뒤집었으면 1       
        DFS(visible, hidden, k, 0, 0); // 뒤집은 횟수, 깊이
        
        return answer;        
    }
    
    public void cal(int[][] visible, int[][] hidden, int cost, int k) { // 현재 sumCol에서 m번 순회해서 최적화된 값 도출 및 resultCol 업데이트
        int result = -cost;
        int[] resultCol = new int[m]; // 최적화된 열의 합 값을 저장하기
        for(int i=0; i<m; i++) {
            int max = Math.max(sumCol[i], allCol[i] - sumCol[i] - k);
            resultCol[i] = max;
            result += max;
        }
        
        if(n%2 != 0 || m%2 != 0) {
            answer = Math.max(answer, result);
            return;
        }
        
        // 짝짝인 경우   
        int best = 0;
        for(int i=0; i<n; i++) { // 행
            for(int j=0; j<m; j++) { // 열
                if((i+j)%2 == 0) continue;
                
                int origin = sumCol[j];
                int reverse = allCol[j] - sumCol[j];
                int value = 0;
                if(visitR[i] == 1) { // 뒤집힌 경우
                     value = Math.max(origin - hidden[i][j], reverse - visible[i][j]-k);
                }else {
                     value = Math.max(origin - visible[i][j], reverse - hidden[i][j]-k);
                }
                best = Math.max(best, result - resultCol[j] + value);             
            }
        }
        answer = Math.max(answer, best);   
    }
             
    public void DFS(int[][] visible, int[][] hidden, int k, int cost, int dep) {
        if(dep == n) {
            cal(visible, hidden, cost, k);
            return;
        }
        
        DFS(visible, hidden, k, cost, dep+1); // 안뒤집은 경우
        
        filp(visible, hidden, dep);
        visitR[dep] = 1;
        DFS(visible, hidden, k, cost + k, dep+1); // 뒤집은 경우
        
        visitR[dep] = 0;
        reverse(visible, hidden, dep);            
    }
    
    public void filp(int[][] visible, int[][] hidden, int idx) { // v -> h로 
        for(int i=0; i<m; i++) {
            sumCol[i] += (hidden[idx][i] - visible[idx][i]);
        }
    }
    
    public void reverse(int[][] visible, int[][] hidden, int idx) { // h -> v로
        for(int i=0; i<m; i++) {
            sumCol[i] += (visible[idx][i] - hidden[idx][i]);
        }
    }
}