FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY ./target/yoyomall.jar yoyomall.jar
ENTRYPOINT ["java","-jar","/yoyomall.jar", "&"]