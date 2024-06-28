FROM eclipse-temurin:17 as builder

WORKDIR /app

COPY . .

ARG SERVICE
RUN if [ -z "$SERVICE" ]; then echo "SERVICE build argument is required" && exit 1; fi

WORKDIR /app/${SERVICE}
RUN chmod +x ./gradlew
RUN ./gradlew bootJar

WORKDIR /app
RUN mkdir -p /app/build/libs && cp /app/${SERVICE}/build/libs/*.jar /app/build/libs/app.jar

FROM eclipse-temurin:17-jre as runtime

RUN addgroup --system --gid 1000 worker && \
    adduser --system --uid 1000 --ingroup worker --disabled-password worker

COPY --from=builder /app/build/libs/app.jar /app/app.jar

USER worker:worker

ENV PROFILE ${PROFILE}

ENTRYPOINT ["java", "-Dspring.profiles.active=${PROFILE}", "-jar", "/app/app.jar"]
