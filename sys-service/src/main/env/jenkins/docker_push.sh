#!/bin/bash
echo "push docker images to target server..."
registry_server="10.0.52.21:5000"
sudo docker login http://${registry_server} -u admin -p Harbor12345
sudo docker tag sofn/$1-service ${registry_server}/sofn/$1-service:latest
sudo docker push ${registry_server}/sofn/$1-service:latest
