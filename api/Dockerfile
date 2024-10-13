FROM gradle:7.4.0-jdk11-alpine as cache
RUN mkdir -p /home/gradle/cache_home
ENV GRADLE_USER_HOME /home/gradle/cache_home
COPY build.gradle /home/gradle/src/
WORKDIR /home/gradle/src
RUN gradle build -i --stacktrace

FROM gradle:7.4.0-jdk11-alpine AS build
COPY --from=cache /home/gradle/cache_home /home/gradle/.gradle
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle bootJar -i --stacktrace

FROM azul/zulu-openjdk-alpine:11-jre
RUN mkdir -p /app
RUN addgroup -S spring && adduser -S spring -G spring
RUN chown spring:spring /app
USER spring:spring
EXPOSE 8080
COPY --from=build /home/gradle/src/build/libs/*.jar /app/api.jar
COPY --from=build /home/gradle/src/start.sh /app/start.sh
ENTRYPOINT ["app/start.sh"]