FROM openjdk:18-alpine
WORKDIR /app
COPY domain/build/libs/domain-0.0.1-SNAPSHOT.jar .
COPY services-impl/build/libs/services-impl-0.0.1-SNAPSHOT.jar .
COPY user/build/libs/user-0.0.1-SNAPSHOT.jar .
COPY role/build/libs/role-0.0.1-SNAPSHOT.jar .
COPY team/build/libs/team-0.0.1-SNAPSHOT.jar .

EXPOSE 8081 8082 8083

CMD java -jar user-0.0.1-SNAPSHOT.jar && java -jar role-0.0.1-SNAPSHOT.jar && java -jar team-0.0.1-SNAPSHOT.jar

