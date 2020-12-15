# Getting started with Axon Framework

## Simple Food Ordering service using Axon Framework

#### Start Axon Server with:
docker run -d --name axonserver -p 8024:8024 -p 8124:8124 axoniq/axonserver

### Console Axon Server
http://localhost:8024/

#### Place an order and copy the order id:

curl -vvv -X POST http://localhost:8080/orders/place

#### Confirm the order sending the order id:
Example:  curl -vvv -X PUT http://localhost:8080/orders/6bb69b55-c133-4148-85d6-c3054ee98d39/confirm


#### Ship the order sending the order id:
Example:  curl -vvv -X PUT http://localhost:8080/orders/6bb69b55-c133-4148-85d6-c3054ee98d39/ship


#### Get all orders:
Example:  curl -vvv -X GET http://localhost:8080/orders


#### To do:
- Try to implement Hexagonal Architecture;
- Try to implement Saga to track order;


#### References:

- https://www.baeldung.com/hexagonal-architecture-ddd-spring
- https://www.baeldung.com/axon-cqrs-event-sourcing
- https://sgitario.github.io/axon-by-example/
- https://github.com/eugenp/tutorials/blob/master/axon/pom.xml
- https://github.com/reaktika/Blog-Axon-FoodOrderingDemo
- https://wkrzywiec.medium.com/ports-adapters-architecture-on-example-19cab9e93be7
- https://docs.axoniq.io/reference-guide/v/3.3/part-ii-domain-logic/sagas#:~:text=In%20Axon%2C%20each%20instance%20of,managing%20a%20single%20business%20transaction.&text=Unlike%20regular%20Event%20Handlers%2C%20multiple,for%20that%20specific%20Saga%20type.https://docs.axoniq.io/reference-guide/axon-framework/messaging-concepts/message-intercepting#exceptionhandler
