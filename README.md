# app-modernization-ejb
Project to test communication with old Websphere(WAS) project convert to Liberty (WLP) and deploy on Container soon

## The team
[David Vanderpol](mailto:vandepol@ca.ibm.com)

[Hena Alpona](mailto:hena.alpona@ibm.com)

[Juan Pablo Panesso](mailto:jp.panesso@ibm.com)

[Remi Cauchon](mailto:REMI.CAUCHON@ibm.com)

Basically we have an old EJB communication architecture running on Websphere (WAS) and we want to migrate this application running on WAS to Container and OpenShift

1. The legacy-was directory we have the old code running under Websphere (WAS) - Eclipse project only
2. The modernize-liberty directory contains the code migrate to Open Liberty with Maven and Dockerfile
3. The document directory contains reference link to achieve the project
4. The Temperature project contains drawing
5. The images directory contains screenshot to show in the github Readme.md file

# Going from the old to the new
Old frontend

http://localhost:9080/TestConverterWeb/index.jsp

![app-modernization-ejb](images/Old-Frontend-converter.PNG)
 
New frontend

http://localhost:4200

![app-modernization-ejb](images/frontend-temp-converter-1.PNG)
  
