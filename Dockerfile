FROM ubuntu:18.04

COPY ./Backend/target/Backend-1.0-SNAPSHOT.jar /opt
RUN apt update -y \
    && apt install -y openjdk-8-jre

ENTRYPOINT java -jar /opt/Backend-1.0-SNAPSHOT.jar -Djava.net.preferIPv4Stack=true
