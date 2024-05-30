import java.util.*;
class Solution {
public int solution(String[][] book_time) {
              
    // 시작 시각 기준 오름차순, 같으면 종료시각 빠른순
    PriorityQueue<String[]> pq = new PriorityQueue<>((x,y) -> {
        if(x[0].equals(y[0])) {
            return Integer.compare(time(x[1]), time(y[1]));
        }else {
            return Integer.compare(time(x[0]), time(y[0]));
        }
    });

    // 우선순위 큐 초기화
    for(int i=0; i<book_time.length; i++) {
                pq.add(book_time[i]);
    }

    // room 종료시각 빠른 순
    PriorityQueue<String> room = new PriorityQueue<>(
        (x,y) -> (time(x) - time(y))
    );


    while(!pq.isEmpty()) {
        String[] now = pq.poll();

        // 첫 손님일 경우
        if(room.isEmpty()) {
                        room.add(now[1]);
            continue;
        }

        // 방이 있을 경우 가장 종료가 빠른 방 추출
        String roomLast = room.poll();

        // 종료시각+10 <= 시작시각 -> 해당 room에 배정
        if(time(roomLast) + 10 <= time(now[0])) {
                        room.add(now[1]);
            continue;
        }

        // 배정 불가능
                room.add(roomLast);
                room.add(now[1]);
    }

    return room.size();
}

// 시각을 숫자로 변환
public int time(String clock) {
    String[] str = clock.split(":");

    return Integer.parseInt(str[0]) * 60 + Integer.parseInt(str[1]);
}
}