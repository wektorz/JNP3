# base image
FROM node:16.13-alpine

# set working directory
WORKDIR /app

# add `/app/node_modules/.bin` to $PATH
ENV PATH /app/node_modules/.bin:$PATH

# install and cache app dependencies
COPY package*.json ./
RUN npm install
RUN npm install @vue/cli@3.7.0 -g

COPY public public
COPY src src
COPY .editorconfig .editorconfig
COPY babel.config.js babel.config.js

# start app
CMD ["npm", "run", "serve"]