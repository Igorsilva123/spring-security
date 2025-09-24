FROM ubuntu:latest
LABEL authors="Igors"

ENTRYPOINT ["top", "-b"]