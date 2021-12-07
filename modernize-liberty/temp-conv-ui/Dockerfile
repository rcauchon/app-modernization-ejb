### STAGE 1: Build ###
FROM node:16.13-alpine AS build
WORKDIR /usr/src/app
COPY package.json package-lock.json ./
RUN npm install
COPY . .
RUN npm run build-prod

### STAGE 2: Run ###
FROM nginxinc/nginx-unprivileged

COPY --from=build usr/src/app/dist/temp-conv-ui /usr/share/nginx/html

