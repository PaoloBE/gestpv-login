FROM openjdk:17-alpine

EXPOSE 8581

COPY ./login-api-*.jar ROOT.jar

ENTRYPOINT ["java", "-jar", "ROOT.jar"]