# Getting started with Axon Framework

## Simple Ordering food service using Axon Framework

#### Start Axon Server with:
docker run -d --name axonserver -p 8024:8024 -p 8124:8124 axoniq/axonserver


#### Place an order and copy the order id:

curl -vvv -X POST http://localhost:8080/orders/place

#### Confirm the order sending the order id:
Example:  curl -vvv -X PUT http://localhost:8080/orders/6bb69b55-c133-4148-85d6-c3054ee98d39/confirm


#### Ship the order sending the order id:
Example:  curl -vvv -X PUT http://localhost:8080/orders/6bb69b55-c133-4148-85d6-c3054ee98d39/ship


#### Get all orders:
Example:  curl -vvv -X GET http://localhost:8080/orders


#### To do:
- Try to implement Saga to track order