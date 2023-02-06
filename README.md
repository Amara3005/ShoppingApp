# ShoppingApp
I have built this application as per the given details in the word file.
In order to run this application download the ShoppingApp, open in the Eclipse and run as java application.
Server Port is http://localhost:8080/ (Java version should be greater than 8.0).

Some of the Screenshots were attached along the file for the proof . Please have a look at those screen shots.
Documention is mentioned in their respective classes.


There are 3 main controller classes where all 6 methods were done.

Controller_1 class
a.) /inventory this will return the details of the product in json format. ex:- no:- of ordered products, price of products, remaining products;

b.) /fetchCoupons this will return the coupon's and their discount percentage in json format.

c.) /{userId}/order this will return the details of order and it's id if coupon and qty is valid.

ControllerPayement class
/{userId}/{orderId}/pay
This has a single method which is used for Payement. If there is no error in the amount or order Id, then transaction will be done based on the last digit of the orderId.
If last digit of OrderId -> less than or equal to 3 transaction is success.
-> 4 or 5 payement failed from bank.
-> else No response from payement server.

3.) ControllerDataReturn class
1.) /{userId}/orders
Displays all orders of a particular userId. -> If the userId is valid, then it display's all basic details of orders by running a loop through the list. And sending the data in the form of a Json Format. -> else send the message "Invalid UserId"
2.) /{userId}/orders/{orderId}
Displaying the details of a Particular orderId -> If the orderId and userId is valid then send the data of that order. -> Else send the message "Invalid OrderId".
