FROM sdaschner/open-liberty:javaee7-jdk8-b1

ADD https://repo1.maven.org/maven2/net/wasdev/wlp/tracer/liberty-opentracing-zipkintracer/1.0/liberty-opentracing-zipkintracer-1.0-sample.zip /
RUN unzip liberty-opentracing-zipkintracer-1.0-sample.zip -d $INSTALL_DIR/usr/

COPY openliberty/server.xml $CONFIG_DIR

COPY target/instrument-craft-shop.war $DEPLOYMENT_DIR
