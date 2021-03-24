package batch;

import batch.exception.NotSupportedParamException;

import java.util.Arrays;

public class ContainerFiller {

    private final int[] volumes;
    private final int k;

    private Worker[] workers;
    private int workTime;


    public ContainerFiller(int[] volumes, int k) throws NotSupportedParamException {
        if (volumes == null || volumes.length == 0) {
            throw new NotSupportedParamException("volumes table is empty or null");
        }
        this.volumes = volumes;
        if (k < 1) {
            throw new NotSupportedParamException("param k should b at least 1");
        }
        this.k = k;
    }

    /*
    initialize workers table
    put single container in each worker
    last worker will have all other containers
     */
    public void init() {
        workers = new Worker[k];
        int i = 0;
        for (; i < k; i++) {
            workers[i] = new Worker();
            workers[i].addLast(volumes[i]);
        }
        for (int j = i; j < volumes.length; j++) {
            workers[i - 1].addLast(volumes[j]);
        }
    }

    /*
    optimize container repartition between workers and return optimal work time
    loop goes throughout all workers and check if moving a container from one to the other will
    reduce maximum work time between the 2 workers
    the loop ends when no optimization has been performed means repartition is optimized
    return final work time
     */
    public int getMinimumWorkTime() {
        boolean updated = true;
        while (updated) {
            printStatus();
            updated = false;
            for (int i = 1; i < workers.length; i++) {
                //check for move to right
                if (workers[i - 1].getSize() > 1 && checkMoveToRightWillReduceTime(workers[i - 1], workers[i])) {
                    moveToRight(workers[i - 1], workers[i]);
                    updated = true;
                    continue;
                }
                //check for move to left
                if (workers[i].getSize() > 1 && checkMoveToLeftWillReduceTime(workers[i - 1], workers[i])) {
                    moveToLeft(workers[i - 1], workers[i]);
                    updated = true;
                }
            }
        }
        calculateWorkTime();
        System.out.println("::::::::::::::::::::::::::::::::::::::   minimum work time : " + workTime + "   :::::::::::::::::::::::::::::::::::::::::::::");
        return workTime;
    }

    /*
    prints all workers to console so that you can see algorithm progress
     */
    public void printStatus() {
        System.out.println("----------------------------------------------------------------");
        System.out.println(Arrays.toString(workers));
    }

    /*
    calculate work time for all workers
     */
    private void calculateWorkTime() {
        workTime = Integer.MIN_VALUE;
        for (int i = 0; i < workers.length; i++) {
            if (workTime < workers[i].getTotal()) {
                workTime = workers[i].getTotal();
            }
        }
    }

    /*
    max of 2 integers
     */
    private int max(int a, int b) {
        if (a > b) return a;
        return b;
    }

    /*
    move container from worker in the left to worker in the right
     */
    private void moveToRight(Worker worker1, Worker worker2) {
        int element = worker1.getLast();
        worker1.removeLast();
        worker2.addFirst(element);
    }

    /*
    move container from worker in the right to worker in the left
     */
    private void moveToLeft(Worker worker1, Worker worker2) {
        int element = worker2.getFirst();
        worker2.removeFirst();
        worker1.addLast(element);
    }

    /*
    check if moving container to right will reduce work time between 2 workers
    by comparing max time before and after moving the container
    the container is not yet moved it is just a simulation
     */
    private boolean checkMoveToRightWillReduceTime(Worker worker1, Worker worker2) {
        int element = worker1.getLast();
        int oldMax = max(worker1.getTotal(), worker2.getTotal());
        int newMax = max(worker1.getTotal() - element, worker2.getTotal() + element);
        return newMax < oldMax;
    }

    /*
    check if moving container to left will reduce work time between 2 workers
    by comparing max time before and after moving the container
    the container is not yet moved it is just a simulation
     */
    private boolean checkMoveToLeftWillReduceTime(Worker worker1, Worker worker2) {
        int element = worker2.getFirst();
        int oldMax = max(worker1.getTotal(), worker2.getTotal());
        int newMax = max(worker2.getTotal() - element, worker1.getTotal() + element);
        return newMax < oldMax;
    }
}
