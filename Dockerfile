FROM maven:3-openjdk-17 as build
COPY . /var/spring/source
WORKDIR /var/spring/source
RUN mvn package

# production phase
FROM openjdk:17 as production
COPY --from=build /var/spring/source/target/demo-0.0.1-SNAPSHOT.jar /var/website/demo.jar
CMD ["java", "-jar", "/var/website/demo.jar"]