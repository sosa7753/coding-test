class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int now = health;
        int preTime = 1;
        for(int[] attack: attacks) {
            int time = attack[0];
            int damage = attack[1];
            
            int gap =  time - preTime; 
            now = Math.min(health, now + heal(bandage, gap));
            now -= damage;
            if(now <= 0) {
                return -1;
            }
            preTime = time + 1;
        }
        return now;
    }
    
    public int heal(int[] bandage, int t) {
        if(bandage[0] > t) {
            return bandage[1] * t;
        }
           
        return t/bandage[0] * (bandage[0] * bandage[1] + bandage[2]) + 
               (t%bandage[0]) * bandage[1];  
    }
}