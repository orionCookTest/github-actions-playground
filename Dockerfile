FROM openjdk:8-jre-alpine
ENV APP_VERSION 1.0.0
RUN rm -rf /myapp && mkdir /myapp
WORKDIR /myapp
ADD build/libs/github-actions-playground.jar /myapp
ENTRYPOINT java -jar github-actions-playground.jar server
EXPOSE 8080 8081
