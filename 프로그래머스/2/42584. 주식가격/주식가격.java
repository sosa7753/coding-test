import java.util.*;
//int time = 0;
//맨 처음 stack에 Node(1,0) 넣기 
//그다음 2 > 1 Node(2, 1);
//그다음 3 > 2 Node(3, 2);
//그다음 2 > 3 ? -> Node(3,2) pop; // time - 2; -> 패스 
//그다음 3 > .....
//길이가 끝났으면 pop() - time - 
class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        
        Stack<Node> stack = new Stack<>();
        
        int time = 0;
        while(time < prices.length) {
            if(stack.isEmpty()) {
                stack.push(new Node(prices[time], time));
                time++;
                continue;
            }
                     
            if(stack.peek().price <= prices[time]) {  // peek값 보다 큰 경우
                stack.push(new Node(prices[time], time));
            }else if(stack.peek().price > prices[time]) { // peek값 보다 작은 경우
                while(!stack.isEmpty() && stack.peek().price > prices[time]) {
                    Node node = stack.pop();
                    answer[node.start] = time - node.start;
                }
                stack.push(new Node(prices[time], time));
            }
            time++;             
        }
        
        while(!stack.isEmpty()) {
            Node now = stack.pop();
            answer[now.start] = prices.length-1 - now.start;
        }
        return answer;
    }
}
class Node {
    int price;
    int start;
    Node(int price, int start) {
        this.price = price;
        this.start = start;
    }
}