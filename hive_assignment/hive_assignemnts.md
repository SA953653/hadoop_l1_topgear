(any 2 out of 3)
<b>Assignment 1: Create a Managed internal table</b></br>
<pre>
a. Connect to Hive CLI
hive

b. Create a database called projectdb
Sensitivity: Internal & Restricted
create database projectdb;

c. Create a table employee to store data in employee.txt under projectdb

create table projectdb.employee (EmpID string, Name string, Band string, DepartmentID string, Salary float)Id int, Name string , Salary float)  
row format delimited  
fields terminated by ',' ;  

d. List the structure of employee table
Write the command used here:
describe projectdb.employee;
 
e. List all storage parameters of employee table using describe command.
Write the command used here:
describe projectdb.employee;

f. List data in employee table using select statement (make sure that the output has column header s)
Write the commands used here:
load data local inpath '/home/hadoop/Employee.txt' into table projectdb.employee;
set hive.cli.print.header=true;
select * from projectdb.employee;
</pre>
<hr/>
<br>
<b>Assignment 2: Create External Table and execute Join query</b><br/>
<pre>
a. Copy file Department.txt to folder dept under your home directory in HDFS
hdfs dfs -copyFromLocal Department.txt /user/hadoop/dept/

b. Create an external Hive table department to read data from dept folder in hdfs
create external table department (DepartmentID string, DepartmentName string)  
row format delimited  
 fields terminated by ','   
location '/user/hadoop/dept';  

c. Write a join query to join department and employee tables and get the following output:
DepartmentName, TotalSalary
select d.DepartmentName, SUM(e.salary) from employee e left outer join department d on e.DepartmentID=d.DepartmentID group by DepartmentID;

d. How many mappers and reducers are executed in the map reduce job executed by Hive?
Number of Mappers: 1
Number of reducers: 1

e. Display the explain plan for the join query using explain statement
EXPLAIN select d.DepartmentName, SUM(e.salary) from employee e left outer join department d on e.DepartmentID=d.DepartmentID group by DepartmentID;
</pre>
<hr/>
<pre>
<b>Assignment 3: Create a Partitioned Table and load data</b><br/>
<pre>
a. Create a table project_details partitioned by project ID and having following columns:
EmpID, year-week, EffortHrs
create table projectdb.project_details (EmpID string, projectID string, year-week int, EffortHrs string)
partitioned by (projectID string) 
row format delimited  
fields terminated by ',' ;  

b. Load data from file empProj.txt into table project_details
load data local inpath '/home/hadoop/empProj.txt' into table projectdb.project_details;

c. Write a select query to get total effort spent by empID = A1002 by project name.
Required Output columns: projectID, empID, empName, totalEffort
select p.projectID, p.empID, e.Name, SUM(p.EffortHrs) from employee e left outer join project_details p on p.empID=e.EmpID where e.empID=A1002;
</pre>
