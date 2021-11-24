# Start with Open LIber runtime.
FROM openliberty/open-liberty:full-java8-openj9-ubi

ARG VERSION=1.0

LABEL \
  com.ibm.temp.image.authors="Remi Cauchon" \
  com.ibm.temp.image.vendor="IBM" \
  com.ibm.temp.image.url="local" \
  com.ibm.temp.mage.source="https://github.com/rcauchon/app-modernization-ejb.git" \
  com.ibm.temp.image.version="$VERSION" \
  vendor="Open Liberty" \
  name="ConverterService" \
  version="$VERSION" \
  summary="The Converter Service REST api from App Modernization EJB" \
  description="This image contains the Converter Service running with the Open Liberty runtime."

USER root

COPY --chown=1001:0 src/main/liberty/config/server.xml /config/
COPY --chown=1001:0 target/*.war /config/apps/

USER 1001

ENV LICENSE accept

EXPOSE 9085 9445