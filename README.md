#Resource Based Scheduling and Caching in Hetrogeneous Hadoop Environment

##PROBLEM STATEMENT

Hadoop supports heterogeneity of the nodes among the cluster. Existing scheduling algorithms havent touched upon the fact of applying scheduling of the jobs with the awareness of the diverse resources of varying capability to take the advantage and design scheduling al- gorithm to make best use of the resources available in the cluster. For big data analysis, scheduling is worthy while for small data processing,scheduling is an added hindrance.

The improvement of file access performance is a great challenge in realtime jobs. There is no way to query where things are cached on the cluster. HDFS reads are cached in each datanodes OS buffer cache, but this is not exposed to higher level frameworks making task placement decisions. This means a potential waste of up to 3x memory as tasks are placed on all three block replicas.

###Issues
1 Lack of Resource aware scheduler in Heterogeneous environment
2 Data Locality
3 Near Maximum utilisation and allocation of best resources available in the cluster
4 Tasks that require global synchronization or sharing of mutable data are not a good fit
5 Workload characterization from various Hadoop sites.A framework for capturing workload statistics and replaying workload simulations to allow the assessment of framework improvements
6 Finer level of Job and Node classification( taking network traffic to a node etc into account)

###OBJECTIVES
Resource aware scheduling by finer classification of jobs and nodes by taking various factors into account.

Developing efficient Distributed Cache Architecture for the hadoop environment.

Tools used
Apache Hadoop,Java

Amazon Public Dataset:
Data sets are taken from http://aws.amazon.com/publicdatasets/ where in we run the Mapreduce jobs using various datasets to test the performance of the scheduler and cache developed by exposing it under different kinds of datasets.

Linux Distribution, preferably Ubuntu.

Hardware Specifications
Each cluster will be totally heterogeneous with totally varied capabilities in terms of RAM,processor,etc.

Job types

Production jobs: load data, compute statistics, detect spam, etc. Long experiments: machine learning, etc.

Small ad-hoc queries: Hive jobs, sampling.

Additional Materials,Resources

####DETAILED ANALYSIS

Initially Multi Node Hadoop cluster is setup depending on the various hardware configurations based on our needs. With existing schedulers, the sample jobs are run to analyse the performance of the existing scheduling process.

For the development of scheduler we need to develop node classifier,job classifier,job scheduler.Once the Scheduler is designed we need to analyse how the scheduler works i.e analysing the performance of the scheduler. Then an algorithm would be developed for cache and that algorithm should be deployed.Once the cache algorithm is deployed we need to analyse the cache performance. Testing Final phase of the project is Testing where we will test the scheduler and check whether any improvements can be done.

Similarly we will test the cache algorithm and make some improvements. Finally overall performance will be analysed using benchmarking tools and stress-test which would help us asses the performance of our newly developed scheduling

Job classifier Algorithm:

Algorithm 1: Job Classification
for each job in the job batch-jobs log 
do calculate average map rate = tmavg /Bmap average reduce rate = travg/Breduce
if average map rate < disk rate then
map task is CPU bound; 
end
else map task is IO bound;
if average reduce rate < disk rate then
reduce task is CPU bound ; 
end
else reduce task is IO bound; 
end

<Add IMAGES at APPROPRIARTE PLACES>