FROM ubuntu:20.04
RUN apt-get update && apt-get install -y openjdk-17-jdk
COPY src/main .
CMD ["java", "-jar", "target/WalletApplication-1.0-SNAPSHOT.jar;"]