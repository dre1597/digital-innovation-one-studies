FROM gradle:8.7-jdk17

RUN apt update && apt install -qq -y --no-install-recommends

ENV INSTALL_PATH /reactive-flashcards

RUN mkdir $INSTALL_PATH

WORKDIR $INSTALL_PATH

COPY . .
