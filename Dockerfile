FROM docker.sebastian-daschner.com/open-liberty-jdk8:1

ADD https://repo1.maven.org/maven2/net/wasdev/wlp/tracer/liberty-opentracing-zipkintracer/1.0/liberty-opentracing-zipkintracer-1.0-sample.zip /
RUN unzip liberty-opentracing-zipkintracer-1.0-sample.zip -d /opt/wlp/usr/

COPY openliberty/server.xml $CONFIG_DIR

COPY target/instrument-craft-shop.war $DEPLOYMENT_DIR
