FROM ubuntu:latest

RUN apt-get update && apt-get install -y openjdk-17-jdk maven
WORKDIR /app
COPY . /app
CMD ./mvnw clean package -DuberJar=true -DskipTests -X
ENV JAVA_HOME /usr/lib/jvm/java-17-openjdk-amd64

CMD ["java", "-jar", "target/quarkus-app/quarkus-run.jar"]