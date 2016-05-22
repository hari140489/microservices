FROM frolvlad/alpine-oraclejdk8:slim
ADD hystrix-dashboard-0.0.1-SNAPSHOT.jar hystrix-dashboard.jar
RUN sh -c 'touch /hystrix-dashboard.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/hystrix-dashboard.jar"]
