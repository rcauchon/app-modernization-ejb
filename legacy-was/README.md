# legacy-was
Directory which contains all the legacy java code to run on Websphere Application Server v8.5.5 on java 1.6

# For the old people in the room. 
Start up the old WAS on you Windows desktop 
```
start First steps from you desktop tool box
```
 ![app-modernization-ejb](../images/firtstep-WAS.PNG)

Started the SERVER by default **AppSrv01**

 ![app-modernization-ejb](../images/WAS-home.PNG)
 
You see in the window > Applications > Application Types > WebSphere enterprise applications
we have **ConverterService war** and **TempEAR** running

 ![app-modernization-ejb](../images/WAS-Application.PNG)

For test purpose in the TempEAR I have a small frontend running and calling the ejb local bean ConverterBean

 ![app-modernization-ejb](../images/Old-Frontend-converter.PNG)
