FROM openjdk:8-jdk-alpine
WORKDIR /root
COPY target/app.jar app.jar
RUN ["apk", "--no-cache", "add", "curl"]
ENV JAVA_OPTS=""
ENTRYPOINT ["java","-jar","app.jar"]