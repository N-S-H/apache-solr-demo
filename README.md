# apache-solr-demo
Sample Apache Solr Application

Note: Before running this application ensure that Apache solr instance is running on port 8983 in localhost


# <h3>Start the Apache Solr using the following command (after navigating to apache solr folder)</h3>
./bin/solr start

# <h3>Create the Product collection (also called as core) using the following command</h3>
./bin/solr create -c product

# <h3>Command to delete the existing core</h3>
./bin/solr delete -c product
