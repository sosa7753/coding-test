import java.util.*;
class Solution {
    public int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> genre = new HashMap<>();
        Map<String, PriorityQueue<int[]>> number = new HashMap<>(); // 고유번호 재생횟수
        
        for(int i=0; i<genres.length; i++) {
            genre.put(genres[i], genre.getOrDefault(genres[i], 0) + plays[i]);
            if(!number.containsKey(genres[i])) {
                number.put(genres[i], new PriorityQueue<>((x,y) -> {
                           if(x[1] == y[1]) {
                               return x[0] - y[0];
                           }
                           return y[1] - x[1];
                       }));
            }
            
            number.get(genres[i]).offer(new int[] {i, plays[i]});
        }
        
        PriorityQueue<Sing> singQ = new PriorityQueue<>((x,y) -> (y.count - x.count));
        for(Map.Entry<String, Integer> map : genre.entrySet()) {
            singQ.add(new Sing(map.getKey(), map.getValue()));
        }
        
        
        List<Integer> list = new ArrayList<>();
        while(!singQ.isEmpty()) {
            Sing s = singQ.poll();
            PriorityQueue<int[]> pp = number.get(s.gen);
            
            int cnt = 2;
            while(!pp.isEmpty() && cnt > 0) {
                int[] now = pp.poll();
                list.add(now[0]);
                cnt--;
            }
        }
        
        int[] answer = new int[list.size()];
        for(int i=0; i<answer.length; i++) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}

class Sing {
    String gen;
    int count;
    Sing(String gen, int count) {
        this.gen = gen;
        this.count = count;
    }
}