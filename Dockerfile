FROM frolvlad/alpine-oraclejre8:slim
#FROM openjdk:8u232-jre
#FROM java:8

#维护者信息
MAINTAINER baojieren

VOLUME /tmp

ADD build/libs/jiaju-1.jar d-jiaju-1.jar

ENV JAVA_OPTS="-Xms256m -Xmx1024m"

EXPOSE 8080

ENTRYPOINT ["java","-Xms256m","-Xmx1024m","-jar","/d-jiaju-1.jar","--spring.profiles.active=test","&"]
