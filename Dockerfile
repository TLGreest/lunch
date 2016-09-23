FROM openjdk:8
RUN apt-get update \
  && apt-get install apt-transport-https
RUN echo "deb https://dl.bintray.com/sbt/debian /" |  tee -a /etc/apt/sources.list.d/sbt.list
RUN apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 642AC823 \
  && apt-get install apt-transport-https \
  && apt-get update \
  && apt-get install sbt

WORKDIR /opt
RUN wget https://downloads.typesafe.com/typesafe-activator/1.3.10/typesafe-activator-1.3.10-minimal.zip \
    && unzip typesafe-activator-1.3.10-minimal.zip \
    &&  rm -f /opt/typesafe-activator-1.3.10-minimal.zip \
    &&    mv /opt/activator-1.3.10-minimal /opt/activator

WORKDIR /

ENV PATH=$PATH:/opt/activator/bin

RUN mkdir -p  /root/.ivy2
VOLUME /root/.ivy2

RUN mkdir  -p /root/olly
VOLUME /root/olly

WORKDIR /root/olly

EXPOSE 9000

WORKDIR /root/olly/breakfast

CMD ["sbt", "-mem",  "4444", "~test-quick"]
