<b>Assignment 1</b>
<pre>
a. Create files (having columns: EmpID, Name, band, subject, marks) and copy them to folder employee/input under your home directory in HDFS
hdfs dfs -copyFromLocal /home/hadoop/Hadoop_data.txt /user/hadoop/employee/input/
hdfs dfs -copyFromLocal /home/hadoop/OtherAssessment_data.txt /user/hadoop/employee/input/

b. Create and execute a Java Map Reduce job to find total marks scored by employees in all assessments using employee/input as input folder and employee/totScore as output folder
</pre>
