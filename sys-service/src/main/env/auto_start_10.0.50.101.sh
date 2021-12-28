#!/bin/bash
echo "auto start service ..."

#启动fastdfs文件服务器
echo "start fastdfs ..."
/usr/bin/fdfs_trackerd /etc/fdfs/tracker.conf  restart
/usr/bin/fdfs_storaged /etc/fdfs/storage.conf restart

#启动nginx
echo "start nginx ..."
/usr/local/nginx/sbin/nginx

#启动redis
echo "start redis ..."
/usr/local/redis/bin/redis-server /usr/local/redis/etc/redis.conf

#启动Jenkins
echo "start jenkins ..."
nohup java -jar /usr/local/jinkens/jenkins.war >> /usr/local/jinkens/jenkins.log 2>&1 &

