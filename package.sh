echo "cp antx"
 cp antx.production.properties  antx.properties
echo "package"
mvn clean install -Dmaven.test.skip=true
echo "scp" 
scp deploy/target/novel.war root@115.28.171.73:/mnt/backup/


