import java.io.*;
import java.util.*;
class Main {
    static class Node {
        Map<Character, Node> map = new HashMap<>();  
        boolean last = false;
    }
    static Node root;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        String line;
        while((line = br.readLine()) != null && !line.equals("")) {
            int T = Integer.parseInt(line);
            double answer = 0;
            root = new Node();
            List<String> list = new ArrayList<>();
            for(int i=0; i<T; i++) {
                String str = br.readLine();
                insert(str);
                list.add(str);
            }
            
            for(int i=0; i<T; i++) {
                String str= list.get(i);
                answer += search(str);
            }
            
           sb.append(String.format("%.2f", answer/(double)T)).append("\n");
        }
        System.out.print(sb);
    }
    
    public static void insert(String number) {
        Node node = root;
        for(char num : number.toCharArray()) {
            node = node.map.computeIfAbsent(num, c -> new Node());
        }
        node.last = true;
    }
    
    public static double search(String number) {
        double result = 1.0;
        Node node = root;
        for(int i=0; i<number.length(); i++) {
            char c = number.charAt(i);
            node = node.map.get(c); // 현재 나의 데이터 
          
            if(i == number.length() - 1) {
                break;
            }
                  
            int size = node.map.size(); // 다음 친구 값 더해주기
            if(size > 1 || node.last) {
               result++;
            }
        }
        return result;
    }
}