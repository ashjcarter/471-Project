package cpusched;

import java.text.DecimalFormat;

/**
 * This class represents a Calculations utility for generating statistics.
 */
public class Calculations 
{
    /**
     * Generates a string containing statistics for the run.
     * 
     * @param currTime              The current time.
     * @param numProcess            The number of processes.
     * @param totalWaitTime         The total wait time for all processes.
     * @param totalTurnaroundTime   The total turnaround time for all processes.
     * @param totalResponseTime     The total response time for al processes.
     * @param totalProcessingTime   The total processing time for all processes.
     * @return                      A string containing the calculated statistics.
     */
    public String generateStats(int currTime, int numProcess, int totalWaitTime, 
                                int totalTurnaroundTime, int totalResponseTime, int totalProcessingTime) 
    {
        // Calculations
        double throughput = (double)numProcess / currTime;
        double cpuUtilization = ((double)totalProcessingTime / currTime) * 100;
        double avgWaitTime = (double)totalWaitTime / numProcess;
        double avgTurnaroundTime = (double)totalTurnaroundTime / numProcess;
        double avgResponseTime = (double)totalResponseTime / numProcess;
        DecimalFormat df = new DecimalFormat("#.##");

        // StringBuilder for string concatenation
        StringBuilder result = new StringBuilder();

        // Append the statistics to the string
        result.append("Statistics for the Run\n\n");
        result.append("Number of processes: ").append(numProcess).append("\n");
        result.append("Total elapsed time: ").append(df.format(currTime)).append("\n");
        result.append("Throughput: ").append(df.format(throughput)).append("\n");
        result.append("CPU utilization: ").append(df.format(cpuUtilization)).append("%\n");
        result.append("Average wait time (in CPU burst times): ").append(df.format(avgWaitTime)).append("\n");
        result.append("Average turnaround time (in CPU burst times): ").append(df.format(avgTurnaroundTime)).append("\n");
        result.append("Average response time (in CPU burst times): ").append(df.format(avgResponseTime));

        // Converting the StringBuilder to a string and returning the result 
        return result.toString();
    }
    
}
