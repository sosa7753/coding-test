import java.io.*;
import java.util.*;
class Main {
    static class Node {
        Map<Character, Node> map = new HashMap<>();
        boolean isLast = false;
    }
    static int T;
    static Node root;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        T = Integer.parseInt(br.readLine());
        
        for(int i=0; i<T; i++) {
            int N = Integer.parseInt(br.readLine());
            root = new Node();
            List<String> list = new ArrayList<>();
            boolean check = true;
            for(int j=0; j<N; j++) {
                String number = br.readLine();
                list.add(number);
                insert(number);      
            }
            
            for(int j=0; j<N; j++) {
                String number = list.get(j);
                if(!search(number)) {
                    check = false;
                    break;
                }
            }
            
            if(check) {
                sb.append("YES").append("\n");
            }else {
                sb.append("NO").append("\n");
            }
        }
        System.out.print(sb);
    }
    
    public static void insert(String number) {
        Node node = root;
        for(char num : number.toCharArray()) {
            node = node.map.computeIfAbsent(num, c -> new Node()); // 내 마지막 value = true;
        }
        node.isLast = true;
    }
    
    public static boolean search(String number) {
        Node node = root;
        for(int i=0; i<number.length(); i++) {
            char num = number.charAt(i);
            node = node.map.get(num);
           if(node.isLast) {
              if(i == number.length() - 1) {
                  return true;
              }else {
                  return false;
              }
           }
        }
        return true;
    }  
}