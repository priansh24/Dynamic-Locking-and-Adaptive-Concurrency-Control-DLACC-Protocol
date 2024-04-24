public class DLACCTest {

  public static void main(String[] args) {
    DLACC dlacc = new DLACC();
    long startTime = 0, endTime = 0, elapsedTime;
    long totalElapsedTime = 0;
    int numIterations = 10;
    int numTransactions = 100000;
    int load = 1;

    // Warm-up phase
    for (int i = 0; i < numTransactions; i++) {
      dlacc.acquireLock("A" + i);
      dlacc.releaseLock("A" + i);
    }

    // Performance testing phase
    for (int iter = 1; iter <= numIterations; iter++) {
      startTime = System.currentTimeMillis();
      for (int i = 0; i < numTransactions; i++) {
        dlacc.acquireLock("A" + i);
        dlacc.releaseLock("A" + i);
      }
      endTime = System.currentTimeMillis();
      elapsedTime = endTime - startTime;
      totalElapsedTime += elapsedTime;
      System.out.println(
        "Iteration " + iter + ": Time taken - " + elapsedTime + " milliseconds"
      );
    }

    // Throughput
    double throughput = (double) numTransactions *
    numIterations /
    totalElapsedTime *
    1000;
    System.out.println(
      "Throughput: " + throughput + " transactions per second"
    );

    // Latency
    double averageLatency = (double) totalElapsedTime / numIterations;
    System.out.println("Average latency: " + averageLatency + " milliseconds");

    // Scalability
    while (load <= 1000000) {
      startTime = System.currentTimeMillis();
      for (int i = 0; i < load; i++) {
        dlacc.acquireLock("A" + i);
        dlacc.releaseLock("A" + i);
      }
      endTime = System.currentTimeMillis();
      elapsedTime = endTime - startTime;
      System.out.println(
        "Load " + load + ": Time taken - " + elapsedTime + " milliseconds"
      );
      load *= 10;
    }

    // Resource utilization
    Runtime runtime = Runtime.getRuntime();
    long memoryUsed = runtime.totalMemory() - runtime.freeMemory();
    double cpuUsage = (double) (endTime - startTime) /
    (totalElapsedTime * runtime.availableProcessors());
    System.out.println("Memory used: " + memoryUsed + " bytes");
    System.out.println("CPU usage: " + cpuUsage);
  }
}
