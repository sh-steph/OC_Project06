# This defines our starting point
FROM node:18.15.0
RUN mkdir /usr/src/app 
 
WORKDIR /usr/src/app

RUN npm cache clean --force
RUN npm install -g npm@9.8.1
RUN npm install -g @angular/cli@14.1.3

COPY . . 
