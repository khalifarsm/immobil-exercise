package service;

public class ContainerFiller {
    public int getMinimumWorkTime(int[] containers) {
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < containers.length - 2; i++) {
            for (int j = i + 1; j < containers.length - 1; j++) {
                int time = getWorkTime(containers,i,j);
                if(min>time){
                    min=time;
                }
            }
        }
        return min;
    }

    private int getWorkTime(int[] containers, int a, int b) {
        int[] sum = new int[3];
        for (int i = 0; i < a; i++) {
            sum[0] += containers[i];
        }
        for (int i = a; i < b; i++) {
            sum[1] += containers[i];
        }
        for (int i = b; i < containers.length; i++) {
            sum[2] += containers[i];
        }
        int max = -1;
        for (int i = 0; i < 3; i++) {
            if (max < sum[i]) {
                max = sum[i];
            }
        }
        return max;
    }
}
