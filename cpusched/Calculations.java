package cpusched;

import java.text.DecimalFormat;

public class Calculations 
{
    public String printCalc(int currTime, int numProcess, int totalWaitTime, 
    int totalTurnaroundTime, int totalResponseTime, int totalProcessingTime)
    {
        double throughput = (double)numProcess / currTime;
        double cpuUtilization = ((double)totalProcessingTime / (currTime)) * 100;
        double avgWaitTime = (double)totalWaitTime / numProcess;
        double avgTurnaroundTime = (double)totalTurnaroundTime / numProcess;
        double avgResponseTime = (double)totalResponseTime / numProcess;
        DecimalFormat df = new DecimalFormat("#.##");

        String result = "";

        result+= "Statistics for the Run\n\n";
        result += "Number of processess: " + numProcess + "\n";
        result += "Total elaspted time: " + df.format(currTime) + "\n";
        result += "Throughput: " + df.format(throughput);
        result +="CPU utilization: "  + df.format(cpuUtilization) + "%\n";
        result += "Average wait time (in CPU burst times): " + df.format(avgWaitTime) + "\n";
        result +="Average turnaround time (in CPU burst times): " + df.format(avgTurnaroundTime) + "\n";
        result +="Average response time (in CPU burst times): " + df.format(avgResponseTime);

        return result;

    }
    
}
