Установка Jenkinsa локально https://www.jenkins.io/doc/book/installing/docker/ 

* sudo docker exec 169d46637348 cat /var/jenkins_home/secrets/initialAdminPassword
* sudo docker exec -it c638008edc94 bash
0 
```
docker network create jenkins
```

1 
```
docker run
--name jenkins-docker
--rm
--detach
--privileged
--network jenkins
--network-alias docker
--env DOCKER_TLS_CERTDIR=/certs
--volume jenkins-docker-certs:/certs/client
--volume jenkins-data:/var/jenkins_home
--publish 2376:2376
docker:dind
--storage-driver overlay2
```
2
```
docker build -t myjenkins-blueocean:2.440.1-1 .
```
3
```
docker run \
  --name jenkins-blueocean \
  --restart=on-failure \
  --detach \
  --network jenkins \
  --env DOCKER_HOST=tcp://docker:2376 \
  --env DOCKER_CERT_PATH=/certs/client \
  --env DOCKER_TLS_VERIFY=1 \
  --publish 8080:8080 \
  --publish 50000:50000 \
  --volume jenkins-data:/var/jenkins_home \
  --volume jenkins-docker-certs:/certs/client:ro \
  myjenkins-blueocean:2.440.1-1
```