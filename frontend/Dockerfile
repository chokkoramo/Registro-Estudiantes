# 1. Build
FROM node:20-alpine AS build

WORKDIR /app

COPY package*.json ./
RUN npm install --ignore-scripts

ARG REACT_APP_API_URL=http://web-api:8080
ENV REACT_APP_API_URL=$REACT_APP_API_URL

COPY . .
RUN npm run build

# 2. Serve con Nginx
FROM nginx:alpine

COPY --from=build /app/build /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
