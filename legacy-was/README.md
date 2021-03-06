# legacy-was
Directory which contains all the legacy java code to run on Websphere Application Server v8.5.5 on java 1.6

To run the transformation Advisor on this websphere configuration
```
.\bin\transformationadvisor.bat -w C:\dev\IBM\WebSphere\AppServer -p AppSrv01
```


# For this we have old eclipse project in a workspace
* TempEAR
* TempEJB
* TempEJBClient
* TestConverterWeb
* ConverterService

On my machine I create the workspace *eclipse-workspace-temp*
This is also base on the chapter on EJB [IBM Red book](http://www.redbooks.ibm.com/redbooks/pdfs/sg248076.pdf)


 ![app-modernization-ejb](../images/Eclipse-WS-WAS.PNG)

Here we have the TempEJB project which contains the EJB implementation, we have the TempEJBClient which contains the interface local and remote of the ConverterBean, then we have the TestConverterWeb which call the local EJB. All of the above project are package in the TempEAR project.

We also have the ConverterService which is the REST API calling the Remote EJB from the TempEAR file.

* CtoF - Celsius to Farenheit
* CtoK - Celsius to Kelvin
* FtoC - Farenheith to Celsius
* FtoK - Farenheith to Kelvin
* KtoC - Kelvin to Celsius
* KtoF - Kelvin to Farenheith

You can call the REST API with a curl command
```
curl http://localhost:9080/Converter/rest/converter/FtoC/0
```
Each Services can be call with the name and degree


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

Simple servlet accessing the local EJB with annotation - This is the java project **TestConverterWeb**
```
@WebServlet( urlPatterns = "/TestServlet", loadOnStartup=1)
public class TestServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = Logger.getLogger(TestServlet.class.getName());
	   
	
    @EJB 
    ConverterLocal local;
     ...
```
```
http://localhost:9080/TestConverterWeb/index.jsp
```
 ![app-modernization-ejb](../images/Old-Frontend-converter.PNG)
 
 
We have the ConverterService.war application which call the REMOTE EJB ConverterBean. This Service contains 6 methods 

* CtoF - Celsius to Farenheit
* CtoK - Celsius to Kelvin
* FtoC - Farenheith to Celsius
* FtoK - Farenheith to Kelvin
* KtoC - Kelvin to Celsius
* KtoF - Kelvin to Farenheith

```
private ConverterRemote lookupEJB(Temperature temp) {
		Context ctx;
		
		try {
			Properties props = new Properties();
			props.setProperty(Context.INITIAL_CONTEXT_FACTORY, "com.ibm.websphere.naming.WsnInitialContextFactory");
			props.setProperty(Context.PROVIDER_URL, "corbaloc:iiop:DESKTOP-JR1BRQA:2809");
			
			ctx = new InitialContext(props);
			
			Object homeObject = ctx.lookup("ejb/ConverterRemote");
			ConverterRemote myRemoteEJB = (ConverterRemote) PortableRemoteObject.narrow(homeObject, ConverterRemote.class);

			return myRemoteEJB;
			
		} catch (NamingException ex) {
			System.out.println("Error with Remote EJB: " + ex.getMessage());
			temp.setMessage("Error: " + ex.getMessage());
		}
		return null;
```

 ![app-modernization-ejb](../images/Converter-WAS.PNG)
