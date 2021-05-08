FROM maven:3.6.0-jdk-13

RUN mkdir -p /test

WORKDIR /test

COPY . /test

CMD ["mvn", "test"] 