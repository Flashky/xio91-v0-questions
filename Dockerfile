  FROM openjdk:8-jre-alpine

# copy application JAR
COPY target/xio91-v0-questions-*.jar /app/xio91-v0-questions.jar

ENV mongodb.host ""
ENV mongodb.port "27017"
ENV mongodb.database ""
ENV mongodb.username ""
ENV mongodb.password ""
ENV oauth.issuer-uri ""
ENV eureka.host ""

# specify default command
CMD ["java", "-jar", "/app/xio91-v0-questions.jar"]