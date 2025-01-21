FROM  maven:3.9.8-eclipse-temurin-21 as builder
ENV HOME=/opt/app
RUN mkdir -p $HOME
WORKDIR $HOME

ADD pom.xml $HOME

RUN mvn dependency:go-offline
COPY . .
RUN mvn install -DskipTests

FROM eclipse-temurin:21-jre-alpine
WORKDIR /opt/app
COPY --from=builder /opt/app/target/*.jar /opt/app/*.jar
ENTRYPOINT ["java", "-jar", "/opt/app/*.jar"]