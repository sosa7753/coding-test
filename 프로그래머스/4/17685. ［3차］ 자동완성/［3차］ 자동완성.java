import java.util.*;
class Solution {
    Node root;
    public int solution(String[] words) {
        root = new Node();
        
        for(String word : words) {
            insert(word);
        }
        
        int answer = 0;
        for(String word : words) {
            answer += search(word);
        }
        
        return answer;
    }
    
    public int search(String s) {
        int result = 0;
        Node node = root;
        char[] c = s.toCharArray();
        for(int i=0; i<c.length; i++) {
            node = node.map.get(c[i]);
            result++;
            if(node.cnt == 1) {
                break;
            }
        }
        return result;
    }
    
    public void insert(String s) {
        Node node = root;
        char[] c = s.toCharArray();
        for(int i=0; i<c.length; i++) {      
            node = node.map.computeIfAbsent(c[i], key -> new Node());
            node.cnt += 1; // 해당 노드에 방문한 횟수로 인식
        }
    }
}

class Node {
    Map<Character, Node> map = new HashMap<>();
    int cnt = 0;
}