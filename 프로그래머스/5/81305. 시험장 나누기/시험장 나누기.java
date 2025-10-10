import java.util.*;
class Solution {
    int n, size, answer;
    int root = -1;
    int[] num;
    int[][] links;
    public int solution(int k, int[] num, int[][] links) {
        n = num.length;
        this.num = num;
        this.links = links;

        // 루트 찾기
        boolean[] visited = new boolean[n];
        for(int i=0; i<n; i++) {
            if(links[i][0] != -1) {
                visited[links[i][0]] = true;
            }
            
            if(links[i][1] != -1) {
                visited[links[i][1]] = true;
            }
        }
        
        for(int i=0; i<n; i++) {
            if(!visited[i]) {
                root = i;
                break;
            }
        }
        
        int L = 0;
        int R = 0;
        for(int i=0; i<num.length; i++) {
            L = Math.max(L, num[i]);
            R += num[i];
        }
        
        answer = 0;
        while(L <= R) {
            int mid = (L + R)/2;
            if(check(mid) <= k) {
                answer = mid;
                R = mid - 1;
            }else {
                L = mid + 1;
            }
        }
        
        return answer;
    }
    
    public int check(int max) {
        size = 0;
        DFS(root, max);
        return size + 1;
    }
    
    public int DFS(int node, int max) {
        int left = 0;
        int right = 0;
        if(links[node][0] != -1) {
            left = DFS(links[node][0], max);
        } 
        
        if(links[node][1] != -1) {
            right = DFS(links[node][1], max);
        }
        
        if(num[node] + left + right <= max) { // 안 잘라도됨.
            return num[node] + left + right;
        }
        
        if(num[node] + Math.min(left, right) <= max) { // 큰쪽을 자름
            size +=1;
            return num[node] + Math.min(left, right);
        }
        
        size +=2;
        return num[node];
    }
}