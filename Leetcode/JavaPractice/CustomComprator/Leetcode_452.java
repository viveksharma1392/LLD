import java.lang.Math;
class Solution {
    public class CustomComprator implements Comparator<int[]>{
        @Override
        public int compare(int[] a, int[] b){
            //a[0]-b[0] wont work because of integer overflow issue
            //return a[0]-b[0];
            if(a[0]<b[0]) return -1;
            if(a[0]>b[0]) return 1;
            return 0;
        }
    }
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, new CustomComprator());
        //Way to print 2-d array
        // for(int[] point :points){
        //     System.out.print(Arrays.toString(point));
        // }
        int t = points[0][1];
        int count = 1;
        int i = 1;
        while(i<points.length){
            if(t<points[i][0]){
                t = points[i][1];
                count+=1;
            }
            else{
                t = Math.min(t, points[i][1]);
            }
            i+=1;
        }
        return count;
    }
}