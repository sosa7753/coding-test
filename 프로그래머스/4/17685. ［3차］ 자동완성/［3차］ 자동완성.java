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
            node.cnt += 1;
        }
        node.last = true;
    }
}

class Node {
    Map<Character, Node> map = new HashMap<>();
    int cnt = 0;
    boolean last = false;
}