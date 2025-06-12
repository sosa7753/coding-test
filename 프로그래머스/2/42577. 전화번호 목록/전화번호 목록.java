import java.util.*;
class Solution {
    Tree root;
    public boolean solution(String[] phone_book) {
        Arrays.sort(phone_book);     
        root = new Tree();
        
        for(String s : phone_book) {
            if(!insert(s)) {
                return false;
            }
        }       
        return true;
    }
    
    public boolean insert(String s) {
        Tree now = root;
        for(int i=0; i<s.length(); i++) {
            now = now.map.computeIfAbsent(s.charAt(i), c -> new Tree()); 
            if(now.last == true) {
                return false;
            }
        }
        now.last = true;
        return true;
    }
    
}

class Tree {
    Map<Character, Tree> map = new HashMap<>();
    boolean last = false; 
}