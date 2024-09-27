FROM openjdk:8
EXPOSE 8080
ADD target/ipensho-github-actions.jar ipensho-github-actions.jar
ENTRYPOINT ["java", "-jar", "/ipensho-github-actions.jar"]