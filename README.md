Test task for RelatedData
===============================
Application analyzed and provides statistics of Netflow report data.

# Application information:
- Application parses a source data from a CSV file, and creates TrafficRecord objects from its fields.
- Application performs analysis and stores the results in the JSON format into the destination file.
- It contains the following methods with a path to a Source file as input parameter:
    * Find top 10 traffic receivers 
    * Find top 10 traffic transmitters 
    * Find top 10 used applications 
    * Find top 3 used protocols 
    * Find amount of traffic for applications and protocols between the stated start/end time.
      This method also uses Start and End date as the input parameters in addition to a Source file path.
