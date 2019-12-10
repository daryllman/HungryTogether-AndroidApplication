# HungryTogether-AndroidApplication

To install:

```
https://github.com/daryllman/HungryTogether-AndroidApplication.git
```

1) Git clone above link to local machine  
2) Import to android studio (File -> New... -> Import --> <directory of file above>)

|Page|Description|
|----|-----------|
|Google Authentication Page|Hungry Together is user-specific and each user’s experience is different according to their activities. Therefore, our group decided to integrate Google’s built-in sign-in portal to identify users and keep track of their activities.|
|Overall Navigation using OpenOrdersFragment|This Open Orders fragment serves as the homepage for the user when they log in using their Google account. Here, the user can view all the available open orders created by other users (Food Captains), updated in real-time.As a bottom navigation bar was concluded to be the most user-friendly, Hungry Together’s foundation uses a Fragment concept. This allows the user to easily access key features of the app in an instant.| 
|Ordering Activity|Once a user decides to enrol in an open order, he/she is referred to as a Hungry Sailor. The Hungry Sailor is redirected to the Ordering Activity page, where the Sailor can customise the order. Once ‘DONE’ is pressed, the order is sent to the Food Captain, which is updated live to Cloud Firestore.|
|New Order Activity Fragment|If the users do not see orders that appeal to them in the Ordering Activity fragment, they can start their own open orders as a Food Captain. On this screen, the user can select their desired restaurant, meetup location, as well as the time that they are going to open their order until.|
|My Orders Fragment |In the My Orders Fragment shown in Figure 5 above, Hungry Sailors can view a snapshot of their current order. This snapshot contains relevant information such as the Food Captain’s name, and meetup location. status to know of the progress of their food delivery. More importantly, the status of the food delivery is also provided, presented in a compact visual display that indicates the delivery progress. This status is updated by the Food Captain, and will only be relayed to Hungry Sailors under him or her, and not shared to external users of the app that are uninvolved in this transaction.|
|Food Captain Update|If users are Food Captains (have created their own open order), they are able to check the orders of Hungry Sailors under them. The orders of each Hungry Sailor and their payment statuses can also be accessed here.|
