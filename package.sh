echo "cp antx"
 cp antx.production.properties  antx.properties
echo "package"
mvn clean install -Dmaven.test.skip=true
echo "done!" 

