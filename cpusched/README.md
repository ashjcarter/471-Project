# CS 471 Project Fall 2023 - Problem 1

## Author
Ashley Carter

## Description 
This program will simulate a CPU scheduler and handle scheduling 500 simulated processes. The user will choose which scheduling algorithm to handle the processes: FIFO, SJF without preemption and priority with preemption.

## Running the program
While in the CS471-Project directory:

javac cpusched/*.java
java cpusched.Main

## Input file
An input text filed named contains 513 processes with three columns: Arrival time, CPU burst length and priorty. The program only parses the first 500 processes from the file.

Datafile1-txt.txt

## Output file
Three output files can be created and have the structure of:
"[Scheduler]-Output.txt" 

Fifo-Output.txt
Sjf-Output.txt
Priority-Output.txt

These files print the various statistics of the selected scheduling algorithm such as:

Number of processes
Total elapsed time
Throughput
CPU utilization
Average wait time (in CPU burst times)
Average turnaround time (in CPU burst times)
Average response time (in CPU burst times)

## Summary of results
The scheduling algorithms have identical average wait and response values. The total elasped between all three algorithms are identical. The throughput and CPU utilizaiton for all three are very similar due to the following equations used:

Throughput = total number of processes / total elasped time
CPU utilization = total burst time / total elasped time

The most notable difference is the burst time for each algorithm. Fifo has the highest at 39, then priority is 2nd with 38.42 and Sjf being the shortest at 35.36