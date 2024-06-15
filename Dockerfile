
FROM openjdk:17

WORKDIR /app

COPY build/libs/demo-0.0.1-SNAPSHOT.jar app.jar
COPY ./Run.sh Run.sh
RUN chmod +x Run.sh

ENTRYPOINT ["./Run.sh"]