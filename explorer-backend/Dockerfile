# Version 0.0.1
FROM java:8

MAINTAINER Bill Lv "bill@nebulas.io"

# 环境变量
ENV WORK_PATH /home/project/explorer
ENV APP_NAME explorer-0.0.1-SNAPSHOT.jar
ENV APP_VERSION 0.0.1-SNAPSHOT

EXPOSE 8080

#USER
#USER user:group

#VOLUME
VOLUME ["/home/project", "/tmp/data"]

#ADD

#COPY
COPY $APP_NAME $WORK_PATH/

#LABEL
#STOPSIGNAL
#ARG
#ONBUILD

# WORKDIR
WORKDIR $WORK_PATH

# ENTRYPOINT
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","--spring.profiles.active=dev","--spring.datasource.initialize=true"]

# CMD
CMD ["-jar", "explorer-0.0.1-SNAPSHOT.jar"]
