# order-app

<h3 align="center">Backend Application for Orders</h3> <br>

The RESTful API allows users to order products and see the ordered products info.

<h3> Functional Requirements </h3><br>
● Create an order: <br>
    a. The order looks like this: {"productID": "1234", "email": "abc@def.com"}<br>
    b. The order is only valid when the "email" exists in https://regres.in/api/users <br>
    c. The order is only valid when the customer has not ordered this product already.<br>
    d. Store the order in a database of choice (order|D, email, first_name, last_name, productID)<br>
    e. Return the orderlD<br>
● Retrieve all orders: <br>
    Return a list of all orders (orderlD, email, first_name, last_name, productID)


<h3> Features:</h3>
Springboot<br>
H2<br>
Swagger<br>
Unit Test <br>
Docker <br>
FeignClient <br>
<br><br><br>

| Method | Url                        | Description                            |
| ------ |----------------------------|----------------------------------------| 
| POST   | /create-order              | Create order                           | |
| GET    | /orders                    | This method is used to find all orders |

<h3> Swagger Link:</h3>

http://localhost:8080/swagger-ui/index.html

●  <b>Create Order </b>

<img src="src/main/resources/create-order.png" alt="create-order">

localhost:8080/create-order

{
"productID" : 21,
"email" : "charles.morris@reqres.in"
}

The result in h2 db:

<img src="src/main/resources/h2db.png" alt="h2db">

Db link : http://localhost:8080/h2-console/


●  <b> Retrieve Orders </b>

<img src="src/main/resources/orders.png" alt="orders">

<h3> Run the application in docker:</h3>

First build the docker image with this command: <br>
docker build -t order-app .<br>

And then run the container with generated image: <br>
docker run -d -it  -p 8080:8080 --name order-app order-app <br>
