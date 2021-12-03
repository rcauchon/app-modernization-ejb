<<<<<<< HEAD
FROM nginxinc/nginx-unprivileged
COPY /dist/temp-conv-ui /usr/share/nginx/html
EXPOSE 80
=======
### STAGE 1: Build ###
FROM node:16.13-alpine AS build
WORKDIR /usr/src/app
COPY package.json package-lock.json ./
RUN npm install
COPY . .
RUN npm run build

### STAGE 2: Run ###
FROM nginx:1.17.1-alpine
COPY nginx.conf /etc/nginx/nginx.conf
COPY --from=build usr/src/app/dist/temp-conv-ui /usr/share/nginx/html
>>>>>>> a16aa08caa5e768d492e35e012b5c6691a6375ec
