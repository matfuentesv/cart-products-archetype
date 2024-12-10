FROM openjdk:21-ea-1-jdk

WORKDIR /app
COPY target/api-cart-products-archetype-1.0.0.jar app.jar
COPY Wallet_NKYTD6DF15M2NHAO /app/oracle_wallet
EXPOSE 8080

CMD [ "java", "-jar", "app.jar" ]
