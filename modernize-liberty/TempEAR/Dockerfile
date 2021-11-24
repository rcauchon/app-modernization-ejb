# Start with Open LIber runtime.
FROM openliberty/open-liberty:full-java8-openj9-ubi

ARG VERSION=0.0.1

LABEL \
  com.ibm.temp.image.authors="Remi Cauchon" \
  com.ibm.temp.image.vendor="IBM" \
  com.ibm.temp.image.url="local" \
  com.ibm.temp.mage.source="https://github.com/rcauchon/app-modernization-ejb.git" \
  com.ibm.temp.image.version="$VERSION" \
  vendor="Open Liberty" \
  name="TempEAR" \
  version="$VERSION" \
  summary="The Temperature EJB Serverfrom App Modernization EJB" \
  description="This image contains the Temperature EJB Server running with the Open Liberty runtime."

USER root

COPY --chown=1001:0 liberty/config/server.xml /config/
COPY --chown=1001:0 target/*.ear /config/apps/
COPY --chown=1001:0 target/*.ear /config/dropins/
USER 1001

ENV LICENSE accept

EXPOSE 9082 9442