<b>Assignment 1</b>
<pre>
a. Write a Pig Script to find the frequency of word occurrences in a given text file.
lines = LOAD '/user/hadoop/text_file.txt' AS (line:chararray);
words = FOREACH lines GENERATE FLATTEN(TOKENIZE(line)) as word;
grouped = GROUP words BY word;
wordcount = FOREACH grouped GENERATE group, COUNT(words);
DUMP wordcount;

b. Execute the script in local and map reduce mode
pig -x local text_file_word_count.pig
pig -x mapreduce text_file_word_count.pig
</pre>
<hr/>
<b>Assignment 2</b>
<pre>
a. Copy EmpProj.txt and Employee.txt to hdfs
hdfs dfs -copyFromLocal /home/hadoop/EmpProj.txt /user/hadoop/EmpProj.txt
hdfs dfs -copyFromLocal /home/hadoop/Employee.txt /user/hadoop/Employee.txt

b. Write a Pig Script to get total effort spent by all employees by projectID.
Employee = LOAD 'Employee.txt' USING PigStorage(',')  AS  (EmpID:chararray,Name:chararray,Band:chararray,DepartmentID:chararray,Salary:float);
EmpProj = LOAD 'EmpProj.txt' USING PigStorage(',')  AS  (EmpID:chararray,projectID:chararray,year-week:int,EffortHrs:chararray);
EmpProj_groupBy_Pid = GROUP EmpProj BY projectID;
Emp_Effort_Sum = foreach EmpProj_groupBy_Pid Generate 
   (EmpProj.EmpID,EmpProj.projectID),SUM(EmpProj.EffortHrs);
pre_final_data = JOIN Emp_Effort_Sum BY EmpID LEFT OUTER, Employee BY EmpID;

c. Required Output columns: projectID, empID, empName, totalEffort
final_data = foreach pre_final_data generate $2, $1, $4, $3;
d. Save the output to a folder in your home directory in hdfs
STORE final_data INTO '/user/hadoop/Emp_Effort_Sum/' USING PigStorage (',');
</pre>
<hr/>
