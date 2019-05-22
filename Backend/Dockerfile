FROM ubuntu:18.04

COPY ./target/Backend-1.0-SNAPSHOT.war /opt
RUN apt update -y \
    && apt install -y openjdk-8-jre

ENTRYPOINT java -jar /opt/Backend-1.0-SNAPSHOT.war -Djava.net.preferIPv4Stack=true
