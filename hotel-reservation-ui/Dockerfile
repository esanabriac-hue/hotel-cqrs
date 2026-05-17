# Etapa 1: Build Angular
FROM node:20 AS build
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

# Etapa 2: Servir con Nginx
FROM nginx:alpine
COPY --from=build /app/dist/hotel-reservation-ui /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]

