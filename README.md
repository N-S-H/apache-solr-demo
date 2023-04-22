# apache-solr-demo
Sample Apache Solr Application

Note: Before running this application ensure that Apache solr instance is running on port 8983 in localhost


# Start the Apache Solr using the following command (after navigating to apache solr folder)
./bin/solr start

# Create the Product collection (also called as core) using the following command
./bin/solr create -c product

# Command to delete the existing core
./bin/solr delete -c product
