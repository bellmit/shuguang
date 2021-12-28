#!/usr/bin/env bash
echo "start filebeat now ..."
# 启动filebeat
nohup /opt/filebeat-7.6.2-linux-x86_64/filebeat -e -c /opt/filebeat-7.6.2-linux-x86_64/filebeat.yml >/dev/filebeat.log 2>&1 &
echo "start springboot jar now ..."
# 启动web应用
if [ -n "$APP_OPTS" ]; then
  echo "APP_OPTS: " $APP_OPTS
  java -jar  $1 $APP_OPTS
else
  echo "profiles are active: " $PROFILES
  java -jar  $1 --spring.profiles.active=$PROFILES
fi

