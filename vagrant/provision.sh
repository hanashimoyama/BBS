
#!/bin/sh
  yum -y update
  yum -y install unzip
 

  yum -y install java-1.8.0-openjdk-devel

  groupadd tomcat
  useradd -M -s /bin/nologin -g tomcat -d /opt/tomcat tomcat
  yum -y install wget
  
  cd ~
  wget https://ftp.yz.yamagata-u.ac.jp/pub/network/apache/tomcat/tomcat-9/v9.0.37/bin/apache-tomcat-9.0.37.tar.gz
  mkdir /opt/tomcat
  tar xvf apache-tomcat-9*tar.gz -C /opt/tomcat --strip-components=1
  cd /opt/tomcat
  chgrp -R tomcat /opt/tomcat
  chmod -R g+r conf
  chmod g+x conf
  chown -R tomcat /opt/tomcat/webapps/ work/ temp/ logs/
  cp -f /vagrant/workspace/tomcat.service /etc/systemd/system
  echo 'JAVA_HOME="/usr/java/default/"' >> /etc/sysconfig/tomcat
  chmod 755 /etc/systemd/system/tomcat.service
  systemctl daemon-reload
  systemctl enable tomcat
  systemctl start tomcat
  systemctl enable firewalld
  systemctl start firewalld
  firewall-cmd --zone=public --add-port=8080/tcp --permanent
  firewall-cmd --reload
  cp -f /vagrant/workspace/tomcat-users.xml /opt/tomcat/conf/tomcat-users.xml
  cp -f /vagrant/workspace/context.xml /opt/tomcat/webapps/manager/META-INF/context.xml
  cp -f /vagrant/workspace/context.xml /opt/tomcat/webapps/host-manager/META-INF/context.xml
  systemctl restart tomcat

  firewall-cmd --zone=public --add-port=3306/tcp --permanent
  firewall-cmd --reload
  rpm -ivh https://dev.mysql.com/get/mysql80-community-release-el7-3.noarch.rpm
  yum -y install mysql-community-server
  mysql --version
  systemctl enable mysqld
  systemctl start mysqld

