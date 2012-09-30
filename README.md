SimMon - simple-monitor
=======================

* A simple monitoring tool which is store timestamp and value in file, and make hourly/daily chart images.
* It has a simple html/js gui, for browse the chart images, it is jquery based, and count on Apache autoindex modul (it has to be li not [fancy htmlTable](http://httpd.apache.org/docs/2.2/mod/mod_autoindex.html#IndexOptions)!).
* Has an SMPT/POP3 transport for sending data to the central place, if it needed.
* Using quartz scheduling, jfreechart, and hyperic sigar.

**Elements**

[Samplers] (https://github.com/godzzo/simple-monitor/tree/master/src/main/java/org/godzzo/simmon/sampler) which acquire the values (free memory, cpu usage, jmx counters...)
* hyperic sigar based: Cpu, Mem, Proc, Swap, FileSystem, 
* other: Jmx, Jvm

Outputs which can save the values of the samplers 
* [DurationFileOutput](https://github.com/godzzo/simple-monitor/blob/master/src/main/java/org/godzzo/simmon/sampler/output/DurationFileOutput.java) make new data file about joda duration (seconds/minutes)
* [SizedFileOutput](https://github.com/godzzo/simple-monitor/blob/master/src/main/java/org/godzzo/simmon/sampler/output/SizedFileOutput.java) make new data file about size of file (it is good for disk block size)

Data file mergers for make hourly data files
* [HourFileMerge](https://github.com/godzzo/simple-monitor/blob/master/src/main/java/org/godzzo/simmon/merge/HourFileMerge.java) Make hour data files

Make Chart Images
* [MakeHourChart](https://github.com/godzzo/simple-monitor/blob/master/src/main/java/org/godzzo/simmon/chart/MakeHourChart.java) Generate chart images for hour data files
* [MakeYesterdayChart](https://github.com/godzzo/simple-monitor/blob/master/src/main/java/org/godzzo/simmon/chart/MakeYesterdayChart.java) Merge hourly data files to day data file, and make daily chart image


