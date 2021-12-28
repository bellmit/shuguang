#!/bin/bash
#while getopts ":m:" opt
#do
#    case $opt in
#        m)
#        echo "参数m的值$OPTARG"
#        mount=$OPTARG
#        ;;
#        ?)
#        echo "未知参数"
#        exit 1;;
#    esac
#done

echo "pull docker images and start application..."
registry_server="10.0.52.21:5000"
nb=("ens192" "ens33" "ens160" "eth0")

# 获取服务器hostname
HM=$(hostname)
if [ -z "$HM" ];then
   echo ":: not found ip hostname"; exit 1;
fi

# 获取服务器指定网卡列表中的ip
function findip() {
    for element in ${nb[@]}
    do
        host=$(ip addr show $element | awk '/^[0-9]+: / {}; /inet.*global/ {print gensub(/(.*)\/(.*)/, "\\1", "g", $2)}')
        if [ -n "$host" ];then
            echo $host
        fi
    done
}

HIP=$(findip)
if [ -z "$HIP" ];then
   echo ":: not found ip address"; exit 1;
fi

# docker登录
sudo docker login http://${registry_server} -u admin -p Harbor12345
# 拉取镜像
sudo docker pull ${registry_server}/sofn/$1-service:latest
#sudo docker pull 10.0.52.21:5000/sofn/sys-service:latest

# 停止和移除之前的容器
if sudo docker ps |grep -i $1-01;then
   sudo docker stop $1-01
fi

sleep 1

if sudo docker ps -a|grep -i $1-01;then
   sudo docker rm -f $1-01
fi

sleep 1

# 创建容器并启动
sudo docker run -d -i $3 --name $1-01 -e PROFILES="$2" -e APPID="$1" --network host --add-host ${HM}:${HIP} --restart=unless-stopped ${registry_server}/sofn/$1-service:latest

sleep 10

sudo docker logs $1-01

# 清理
docker system prune -f
