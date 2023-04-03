# CapstoneDataCollector

This is a simple class demonstrating web sockets and their ability to change a webpage without refresh.

Bear with the random classes. I was following a tutorial and didn't change too much yet. I just wanted to verify that this was possible.

Going to http://localhost:8080/ and waiting is all that needs to be done once the application is running. 
To demonstrate being able to change what cesium is displaying, the application just generates a new set of pseudo-random 
coordinates and pushes that to the socket. The page will update on its own to the new coordinates received by websocket
