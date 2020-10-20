<h2>HDFS Assignments</h2>
<b>a. Create a text file userdata.txt in your home directory having following lines using a text editor:</b>
I am working on a Linux Server.
I am learning to work on Hadoop ecosystem.

<b>b. Create a folder called training under your home directory in HDFS</b>
Write the command used here:
hdfs dfs -mkdir /user/hadoop/training

<b>c. Create a folder called SampleData under training</b>
Write the command used here:
hdfs dfs -mkdir /user/hadoop/training/SampleData

<b>d. Copy file userdata.txt to SampleData folder in HDFS</b>
Write the command used here:
hdfs dfs -copyFromLocal /home/hadoop/userdata.txt /user/hadoop/training/SampleData/userdata.txt

<b>e. Display the content of userdata.txt in hdfs using cat command</b>
Write the command used here:
hdfs dfs -cat /user/hadoop/training/SampleData/userdata.txt

<b>f. Create another directory called SampleDataBak under training directory in HDFS</b>
Write the command used here:
hdfs dfs -mkdir /user/hadoop/training/SampleDataBak

<b>g. Copy the file userdata.txt from SampleData folder to SampleDataBak:</b>
Write the command used here:
hdfs dfs -cp /user/hadoop/training/SampleData/userdata.txt /user/hadoop/training/SampleDataBak/userdata.txt

<b>h. List all sub-folders and files under training folder in HDFS:</b>
Write the command used here:
hdfs dfs -ls -R /user/hadoop/training/

<b>i. Display the total disk space used by training directory</b>
Write the command used here:
hdfs dfs -du -s /user/hadoop/training

<b>j. Display the disk space used by each folder under training directory</b></b>
Write the command used here:
hdfs dfs -du /user/hadoop/training

<b>k. Change the Replication factor of training/SampleData/userdata.txt to 2</b>
Write the command used here:
hdfs dfs -setrep -R -w 2 /user/hadoop/training/SampleData/userdata.txt

<b>l. Browse the training folder in HDFS using Web UI (localhost:50070) and note down the following:</b>
File / Folder Name Size Replication Block Size
training 150 3 128
userdata.txt (under SampleData) 75 2 128
userdata.txt (under SampleDataBak) 75 3 128
<i>Output from the UI is pasted EOF</i>

<b>m. Delete training folder along with all sub folders and files:</b>
Write the command used here:
hdfs dfs -rm -f -R /user/hadoop/training

<pre>
[root@ip-10-154-6-58 hadoop]# hdfs dfs -mkdir /user/hadoop/training
[root@ip-10-154-6-58 hadoop]# hdfs dfs -mkdir /user/hadoop/training/SampleData
[root@ip-10-154-6-58 hadoop]# hdfs dfs -copyFromLocal /home/hadoop/userdata.txt /user/hadoop/training/SampleData/userdata.txt
[root@ip-10-154-6-58 hadoop]# hdfs dfs -cat /user/hadoop/training/SampleData/userdata.txt
I am working on a Linux Server.
I am learning to work on Hadoop ecosystem.
[root@ip-10-154-6-58 hadoop]# hdfs dfs -mkdir /user/hadoop/training/SampleDataBak
[root@ip-10-154-6-58 hadoop]# hdfs dfs -cp /user/hadoop/training/SampleData/userdata.txt /user/hadoop/training/SampleDataBak/userdata.txt
[root@ip-10-154-6-58 hadoop]# hdfs dfs -ls -R /user/hadoop/training/
drwxr-xr-x   - root hadoop          0 2020-10-18 04:35 /user/hadoop/training/SampleData
-rw-r--r--   1 root hadoop         75 2020-10-18 04:35 /user/hadoop/training/SampleData/userdata.txt
drwxr-xr-x   - root hadoop          0 2020-10-18 04:36 /user/hadoop/training/SampleDataBak
-rw-r--r--   1 root hadoop         75 2020-10-18 04:36 /user/hadoop/training/SampleDataBak/userdata.txt
[root@ip-10-154-6-58 hadoop]# hdfs dfs -dus /user/hadoop/training
dus: DEPRECATED: Please use 'du -s' instead.
150  /user/hadoop/training
[root@ip-10-154-6-58 hadoop]# hdfs dfs -du -s /user/hadoop/training
150  /user/hadoop/training
[root@ip-10-154-6-58 hadoop]# hdfs dfs -du /user/hadoop/training
75  /user/hadoop/training/SampleData
75  /user/hadoop/training/SampleDataBak
[root@ip-10-154-6-58 hadoop]# hdfs dfs -setrep -R -w 2 /user/hadoop/training/SampleData/userdata.txt
Replication 2 set: /user/hadoop/training/SampleData/userdata.txt
Waiting for /user/hadoop/training/SampleData/userdata.txt .... done

Output from
File / Folder Name		Size		Replication		Block Size
training				      150			3				      128
userdata.txt(SampleData)75		2 				    128
userdata.txt(SampleDataBak)75 3				      128
</pre>
