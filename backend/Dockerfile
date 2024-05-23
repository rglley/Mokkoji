FROM openjdk:17-alpine
WORKDIR /usr/src/app
ARG JAR_FILE=./build/libs/mokkoji-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} /usr/src/app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "./app.jar"]
# opencv library config
#ENTRYPOINT ["java", -Djava.library.path=./opencv, "./app.jar"]
