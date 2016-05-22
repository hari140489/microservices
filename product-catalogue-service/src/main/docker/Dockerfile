FROM frolvlad/alpine-oraclejdk8:slim
ADD product-catalogue-service-0.0.1-SNAPSHOT.jar product-catalogue-service.jar
RUN sh -c 'touch /product-catalogue-service.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/product-catalogue-service.jar"]
