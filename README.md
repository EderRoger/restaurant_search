
#Overview

Searching the best restaurant based on the search params criteria

#Solution

The solution uses an object called SearchCriteria to work

with the Controller and a Service and that one have an injection from the 

Repository (loading and parse the CSV files from restaurants and cuisines) to make the

search and filters.

#Requirements
The solution was build under the java 11 and libraries to test like Mockito and to parse

the CSV files OpenCSV.

#Tests

Was created tests to make sure the solution are working as expected

To run the tests you can just run ``gradle test`` in the main directory.

We don't have an entrypoint/api to test because I'm didn't use the Spring features to create and endpoint etc.

All the features can be tested by unit and integration tests

#IMPROVEMENTS

We can implement like a cache to our API get requests. We should have a higher number of GET requests and would be 

great having a cache.