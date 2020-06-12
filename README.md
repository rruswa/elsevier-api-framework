# Elsevier Technical Test Framework

API testing framework for Elsevier technical task completion.

## Requirements

### Software
* Java 11
* Maven 3

### Configuration

**elsevier.bitly.username**: This must be set to the value of the bitly username.
**elsevier.bitly.password**: This must be set to the password for the bitly username provided.

## How to
### Execute
The following command should be executed from the projects root to run all tests:

`mvn clean verify`
### Reporting
After executing tests with the above command, the html report can be generated by running the command below:

`mvn allure:report`

The report will be located in the directory: `target/results/allure/html`
Open the index file in that directory with a browser to view it.