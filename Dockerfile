FROM openjdk:17
WORKDIR /IPRWC_Backend
COPY target/ipsenho-github-actions.jar /ipsenho-github-actions.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/ipsenho-github-actions.jar"]