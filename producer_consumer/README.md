# CS 471 Project Fall 2023 - Problem 2

## Author
Ashley Carter

## Description 
This program is a simulation of the producer-consumer problem written in Java. It takes an
input file which has a number of arguments and outputs those arguments along with the 
turnaroundtime to an output file. 

## Running the program
While in the CS471-Project directory:  

javac producer_consumer/*.java  
java producer_consumer.Main

## Input file
Three input files are included and are populated with sleep time, number of producers, 
and number of consumers.  

Input-4sec-Wait.txt  
Input-6sec-Wait.txt  
Input-8sec-Wait.txt  

## Output file
Three output files can be created and have the structure of: 
"Output-[wait time]sec-Wait.txt"  

Output-4sec-Wait.txt  
Output-6sec-Wait.txt  
Output-8sec-Wait.txt  

They have the following headers:  
Test Case  
Sleep Time  
Number of Producers  
Number of Consumers  
Average Turnaround Time  

## Summary of results
The turnaround times can vary due to the bottleneck of producers, consumers, and wait time. The cases with high producers and low producers and vice versa, had longer turnaround times compared to those with an equal amount of both. The longer the wait time
the longer the turnaround time was as well.  