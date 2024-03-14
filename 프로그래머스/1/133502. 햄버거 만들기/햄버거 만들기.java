import java.util.*;
class Solution {
    public int solution(int[] ingredient) {
                
        int answer = 0;
        
        Stack<Node> stack = new Stack<>(); // 원본 스택
        
        for(int i=0; i<ingredient.length; i++) {
            if(stack.isEmpty()) {
                if(ingredient[i] == 1) {
                    stack.push(new Node(1,1));
                    continue;
                } 
                stack.push(new Node(ingredient[i],0));
            }
            
            if(burger(ingredient[i], stack)) {
                Node node = stack.peek();
                stack.push(new Node(ingredient[i], node.cnt + 1));
                
                if(ingredient[i] == 1 && node.cnt == 3) {
                    stack.pop();
                    stack.pop();
                    stack.pop();
                    stack.pop();
                    answer++;
                }
            }else {
                if(ingredient[i] == 1) {
                    stack.push(new Node(1,1));
                    continue;
                } 
                stack.push(new Node(ingredient[i],0));
            }
        }
        
        return answer;
    }
    
    public boolean burger(int value, Stack<Node> stack) {
        
        boolean answer = false;
        // 3가지 경우에 true
        
        Node pre = stack.peek();
        
        if(value == 2) {
            if(pre.value == 1 && pre.cnt == 1) {
                answer = true;
            }
        }else if(value == 3) {
            if(pre.value == 2 && pre.cnt == 2) {
                answer = true;
            }
        }else if(value == 1) {
            if(pre.value == 3 && pre.cnt == 3) {
                answer = true;
            }
        }       
        return answer;
    }
}

class Node {
    int value;
    int cnt;
    
    Node(int value, int cnt) {
        this.value = value;
        this.cnt = cnt;
    }
}
