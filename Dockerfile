FROM corretto:17.0.8.1-jdk-slim
WORKDIR /app
COPY build/libs/weather-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/app.jar"]

#FROM corretto:17.0.8.1-jdk-slim
#ARG DEPLOY_ENV
#WORKDIR /app
#COPY *.*ar .
#RUN ln -sfn *.*ar app
#ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-XX:MaxRAMPercentage=75", "-jar", "-Dspring.profiles.active=${DEPLOY_ENV}","./app" ]
#CMD [""]