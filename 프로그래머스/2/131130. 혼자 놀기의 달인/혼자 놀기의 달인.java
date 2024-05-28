import java.util.*;
class Solution {
    public int solution(int[] cards) {
        int answer = 0;
        
        List<Integer> list = new ArrayList<>();
               
        boolean[] visited = new boolean[cards.length];
        
        // 부모배열이 같은지 체크, 아닐 경우 같게 찾기 

        for(int i=0; i<cards.length; i++) {
            if(visited[i]) {
                continue;
            }
            visited[i] = true;
            int start = i+1;
            int end = cards[i];
            int cnt = 1;

            // 종료 조건 : 부모와 값이 같을 때, 이미 방문 했을 때 
            while(start != end && visited[end-1] == false) {
                
                cnt++;
                visited[end-1] = true;
                start = end;
                end = cards[start-1];
            }
            
            list.add(cnt);                
            
        }
        
        Collections.sort(list, (x,y) ->(y-x));
        if(list.size() == 1) {
            return 0;
        }
        int a = list.remove(0);
        int b = list.remove(0);
        answer = a * b;
        return answer;       
    }
}    
//     public void union(int a, int b) {
//         int aP = find(a);
//         int bP = find(b);
        
//         if(aP != bP) {
//             parents[b] = aP;
//         }
//     }
    
//     public int find(int a) {
//         if(a == parents[a]) {
//             return a;
//         }
        
//         return parents[a] = find(parents[a]);
//     }
// }