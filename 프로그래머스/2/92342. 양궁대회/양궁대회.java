import java.util.*;
class Solution {
    int result = 0;
    int[] now;
    public int[] solution(int n, int[] info) {
        int[] answer = {};
        
        int len = info.length;
        now = new int[len];
        
        
        int[] arr = new int[len];
        DFS(n, info, arr, 0);
        
        if(result == 0) {
            return new int[] {-1};
        }
        
        return now;
    }
    
    public void DFS(int arrow, int[] info, int[] arr, int cnt) {
        if(cnt == info.length || arrow == 0) {
            cal(info, arr);
            return;
        }
        
        for(int i=arrow; i>=0; i--) {
            arr[cnt] = i;
            DFS(arrow - i, info, arr, cnt+1);
        }
    }
    
    public void cal(int[] info, int[] arr) {
        int ap = 0;
        int lion = 0;
        for(int i=0; i<info.length; i++) {
            if(info[i] == 0 && arr[i] == 0) {
                continue;
            }
            
            if(info[i] >= arr[i]) {
                ap += 10 - i;
            }else {
                lion += 10 - i;
            }
        }
        
        int value = lion - ap;
        
        if(value < 0) {
            return;
        }
        
        if(result < value) {
            result = value;
            now = Arrays.copyOf(arr, arr.length);
        }else if(result == value) {
            for(int i=info.length-1; i>=0; i--) {
                if(arr[i] > now[i]) { 
                    now = Arrays.copyOf(arr, arr.length);
                    break;
                }else if(arr[i] < now[i]) {
                    break;
                }
            }
        }
    }
}