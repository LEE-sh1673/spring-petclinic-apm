FROM openjdk:17
ARG JAR_FILE="target/spring-petclinic-3.3.0-SNAPSHOT.jar"

COPY ${JAR_FILE} app.jar
COPY pinpoint-agent /pinpoint-agent

EXPOSE 8080

ENTRYPOINT ["java", "-jar", \
  "-javaagent:/pinpoint-agent/pinpoint-bootstrap-2.5.3.jar", \
  "-Dpinpoint.agentId=nhn-ins-local", \
  "-Dpinpoint.applicationName=PetClinic", \
  "-Dspring.profiles.active=mysql", \
  "/app.jar"]
