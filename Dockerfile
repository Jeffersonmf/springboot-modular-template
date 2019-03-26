ARG BUILD_ARGS=""
ARG APP_PATH="/app"

########################
# Maven & Dependencias #
########################
FROM registry.cmpn.paas.gsnetcloud.corp/produban-br/javase-8:3.0.1.RELEASE as DEPENDENCIES

ARG USER_HOME_DIR="/root"
ARG SHA=707b1f6e390a65bde4af4cdaf2a24d45fc19a6ded00fff02e91626e3e42ceaff
ARG BASE_URL=http://nexus.produbanbr.corp/repository/raw-downloads

ARG APP_PATH
WORKDIR $APP_PATH

USER root
RUN mkdir -p /usr/share/maven /usr/share/maven/ref \
  && curl -fsSL -o /tmp/apache-maven.tar.gz ${BASE_URL}/apache-maven-3.5.2-bin.tar.gz \
  && echo "${SHA}  /tmp/apache-maven.tar.gz" | sha256sum -c - \
  && tar -xzf /tmp/apache-maven.tar.gz -C /usr/share/maven --strip-components=1 \
  && rm -f /tmp/apache-maven.tar.gz \
  && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn \
  && chown -R java:java /usr/share/maven \
  && chown -R java:java /app

ENV MAVEN_HOME /usr/share/maven
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"

#///////////////////////////
# Start Copia do workspace /
#///////////////////////////
USER java
COPY .ci/files/settings.xml $MAVEN_HOME/conf
COPY . $APP_PATH/
RUN mvn -B -Dmaven.test.skip=true clean dependency:go-offline dependency:resolve install \
  && rm -rf target/

##########################
# Analise Estatica SONAR #
##########################
FROM DEPENDENCIES as SONAR

ARG APP_PATH
ARG JOB_NAME
WORKDIR $APP_PATH
# Somente execucoes via Pipeline
ENV MAVEN_STAGE_SONAR="mvn org.jacoco:jacoco-maven-plugin:prepare-agent -Djacoco.destFile=target/jacoco.exec -Djacoco.append=true -Dsonar.projectName=$JOB_NAME -Dsonar.projectKey=$JOB_NAME -Dsonar.exclusions=**/*.js -U -B clean package sonar:sonar"
RUN if [ ! -z "$JOB_NAME" ] ; then $MAVEN_STAGE_SONAR ; fi

#################
# BUILD/PACKAGE #
#################
FROM DEPENDENCIES as BUILD

ARG BUILD_ARGS
ARG APP_PATH
WORKDIR $APP_PATH
# Copia do projeto foi realizada no Stage DEPENDENCIES
RUN mvn -DskipTests package install

####################
# TESTES UNITARIOS #
####################
FROM DEPENDENCIES as UNIT_TESTS

ARG APP_PATH
WORKDIR $APP_PATH
# Copia do projeto foi realizada no Stage DEPENDENCIES
RUN mvn compile test

###############
## APLICACAO ##
###############
FROM registry.cmpn.paas.gsnetcloud.corp/produban-br/javase-8:3.0.1.RELEASE
ARG APP_PATH
ARG NAME
ARG VERSION
LABEL VENDOR=Santander
LABEL NAME=$NAME
LABEL VERSION=$VERSION
LABEL MAINTAINER="developer@santander.com.br"

USER root
# Mude para sua extensao .jar ou .war
ENV APP *.jar
WORKDIR $APP_PATH
# Copia os artefatos gerados na Stage BUILD
COPY .ci/ ./.ci
COPY --from=BUILD $APP_PATH/target/$APP $APP_PATH/entrypoint.sh ./
RUN chown -R java:java .
USER java

ENTRYPOINT [ "/bin/sh", "entrypoint.sh" ]