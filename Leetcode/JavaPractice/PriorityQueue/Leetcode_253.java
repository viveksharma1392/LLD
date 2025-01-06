import java.util.PriorityQueue;
import java.lang.Math;
class Solution {
    class CustomComprator implements Comparator<int[]>{
        @Override
        public int compare(int[] a, int[] b){
            return a[0]-b[0];
        }
    }
    class CustomComprator2 implements Comparator<Integer>{
        @Override
        public int compare(Integer a, Integer b){
            return a-b;
        }
    }
    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, new CustomComprator());
        // for(int[] interval : intervals){
        //     System.out.print(Arrays.toString(interval));
        // }
        int ans = 1;
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new CustomComprator2());
        pq.add(intervals[0][1]);
        int i =1;
        while(i<intervals.length){
            while(!pq.isEmpty() && pq.peek()<=intervals[i][0]){
                pq.poll();
            }
            pq.add(intervals[i][1]);
            ans = Math.max(ans, pq.size());
            i+=1;
        }
        return ans;
    }
}