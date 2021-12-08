# TempConvUi

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 12.2.12.

## Development server
Before you start the server make sure you execute `ng install` in the project directory

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory.

## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via a platform of your choice. To use this command, you need to first add a package that implements end-to-end testing capabilities.

## Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI Overview and Command Reference](https://angular.io/cli) page.

## The Web application on port 4200
 ![app-modernization-ejb](../../images/frontend-temp-converter-1.PNG)


## Step TWO 
Build the Docker images

Put the URL of the client-ejb service in the file environment.prod.ts
```
 apiUrl: 'http://client-ejb-route-converter-ejb.itzroks-6630025ezu-fcstbe-6ccd7f378ae819553d37d5f2ee142bd6-0000.us-south.containers.appdomain.cloud/ConverterService/rest/converter/'
```

```
docker build -t temp-conv-ui .
```
Run the docker images locally 
```
 docker run -d --name temp-ui -p 4200:80 temp-conv-ui
```
Tag the image
```
 docker tag temp-conv-ui quay.io/remi_cauchon_ibm/temp-ui:v1.0
```
Push the image on Quay.io
```
 docker push quay.io/remi_cauchon_ibm/temp-ui:v1.0
```

Deploy the image temp-ui on the OpenShift cluster
```
 oc new-app quay.io/remi_cauchon_ibm/temp-ui:v1.0 --as-deployment-configmap=true --name temp-ui
```
Expose the route on port 4200 just like on windows
```
oc expose service temp-ui --port=8080 --name temp-ui-route
```

Create the configmap, config-temp from the local file assets/config/config.json
```
 oc create cm config-temp --from-file ./src/assets/config/config.json
```
Set the volume to dc/temp-ui to mount /usr/share/nginx/html/assets/config to the config-temp configmap
```
oc set volume dc/temp-ui --add -t configmap  --mount-path=/usr/share/nginx/html/assets/config --name myvol --configmap-name config-temp
```

Edit the configmap in the Openshift console copy/paste the fulle URl of the client-ejb route
```
{
    "API_URL" : "http://client-ejb-route-converter-ejb.itzroks-6630025ezu-fcstbe-6ccd7f378ae819553d37d5f2ee142bd6-0000.us-south.containers.appdomain.cloud/ConverterService/rest/converter/",
    "POGO" : "Another variable POGO"
}
```

### BRAVO you are done

Command to clean up the OpenShift projet from temp-ui
```
 oc delete imagestream.image.openshift.io/temp-ui
 oc delete deployment.apps/temp-ui
 oc delete service/temp-ui
 oc delete route/temp-ui-route
 ```

