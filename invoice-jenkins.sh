#!/bin/sh

WRK_DIR=~/.jenkins/workspace/invoice-generator-1.0

MAVEN_HOME=/Volumes/MacDrive1/Tools/apache-maven-3.6.0/bin
DOCKER_HOME=~/Library/Group\ Containers/group.com.docker
DOCKER_APP=/Volumes/MacDrive2/Applications/Docker.app/Contents/Resources/bin/

PATH=$PATH:$MAVEN_HOME:$DOCKER_APP:$DOCKER_HOME

cd $WRK_DIR

mvn clean

mvn validate

mvn spring-boot:build-image

docker image push ezazulhaq02/haa-enterprises:invoice_generator_1.0

ssh mydocker "cd ~/ ; docker-compose up -d ; exit"
