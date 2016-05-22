FROM frolvlad/alpine-oraclejdk8:slim
ADD config-server-0.0.1-SNAPSHOT.jar config-server.jar
RUN sh -c 'touch /config-server.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/config-server.jar"]
