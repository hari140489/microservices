FROM frolvlad/alpine-oraclejdk8:slim
ADD pricing-service-0.0.1-SNAPSHOT.jar pricing-service.jar
RUN sh -c 'touch /pricing-service.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/pricing-service.jar"]
