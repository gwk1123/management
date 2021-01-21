FROM openjdk:8
VOLUME /date/management
ADD management-0.0.1-SNAPSHOT.jar management.jar
EXPOSE 8085
ENTRYPOINT ["java","-jar","/management.jar"]
