FROM maven:3-openjdk-17-slim AS builder

RUN apt update && \
	apt install -y

WORKDIR /opt
COPY SpringBackups/. .
RUN mvn clean package

FROM eclipse-temurin:17-jre-jammy

RUN apt update && \
	apt install -y

EXPOSE 8080
WORKDIR /opt
COPY --from=builder /opt/target/SpringBackups.jar .

ENTRYPOINT ["java"]
CMD ["-jar", "SpringBackups.jar"]