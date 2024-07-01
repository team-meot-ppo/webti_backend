FROM eclipse-temurin:17 as builder

WORKDIR /app

COPY . .

ARG SERVICE
RUN if [ -z "$SERVICE" ]; then echo "SERVICE build argument is required" && exit 1; fi

WORKDIR /app/${SERVICE}
RUN chmod +x ./gradlew
RUN ./gradlew bootJar

RUN mkdir -p /app/build/libs && cp /app/${SERVICE}/build/libs/${SERVICE}-0.0.1-SNAPSHOT.jar /app/build/libs/app.jar

FROM eclipse-temurin:17-jre as runtime

RUN addgroup --system --gid 1000 worker && \
    adduser --system --uid 1000 --ingroup worker --disabled-password worker

WORKDIR /app

COPY --from=builder /app/build/libs/app.jar /app/app.jar

RUN mkdir -p /app/api/logs && \
    chown -R worker:worker /app/api/logs

USER worker

ENV PROFILE ${PROFILE}

ENTRYPOINT ["java", "-Dspring.profiles.active=${PROFILE}", "-jar", "/app/app.jar"]
