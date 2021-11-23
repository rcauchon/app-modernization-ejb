import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Component, isDevMode } from '@angular/core';
import { throwError } from 'rxjs';
import {  catchError } from 'rxjs/operators';
import { environment } from '../environments/environment';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'temp-conv-ui';

  tempUnitsIn = [ 
    { value: "C", description: "\xB0C", selected: true },
    { value: "F", description: "\xB0F" },
    { value: "K", description: "K" },    
  ]

  tempUnitsOut = [ 
    { value: "C", description: "Celcius (\xB0C)" },
    { value: "F", description: "Fahrenheit (\xB0F)", selected: true },
    { value: "K", description: "Kelvin (K)" },    
  ]


  inputDegree = 0;
  inputUnit = 'C';
  outputDegree = 32;
  outputUnit = "F";

  constructor(private http:HttpClient) {
  }

  ngOnInit() {
    if (isDevMode())
      console.log('DEV');
    else 
      console.log('NON-DEV');
  }

  onSubmit() {
    this.onChangeInUnit();
    this.onChangeOutUnit();

    this.convert()
  }

  onChangeInUnit() {
    this.tempUnitsIn.forEach((t) => { t.selected = (t.value == this.inputUnit) })
  }
  onChangeOutUnit() {
    this.tempUnitsOut.forEach((t) => { t.selected = (t.value == this.outputUnit) })
  }
  convert() {
  
    // this.http.post('http://localhost:3030/convert', 
    //   JSON.stringify({
    //     "inputDegree": this.inputDegree, 
    //     "inputDegreeUnit": this.inputUnit, 
    //     "outputDegreeUnit": this.outputUnit
    //   }), { 
    //     headers: new HttpHeaders({
    //       'Content-Type': 'application/json',
    //       'Access-Control-Allow-Origin': '*'
    //     })
    //   }

    // ).subscribe(response => {
    //   let result: any  = response;
    //   this.outputDegree = result.resultDegree.toString().includes('.') ? result.resultDegree.toFixed(2): result.resultDegree;

    // });


    console.log(environment.apiUrl);


    // http://localhost:9080/ConverterService/rest/converter/FtoC/32
    
// this.http.options()
   // this.http.get('http://localhost:9080/ConverterService/rest/converter/FtoC/34', 
  // this.http.get('https://localhost:9443/ConverterService/rest/converter/FtoC/34',
  //   { observe: "response"}
  //   //  { 
  //   //     headers: new HttpHeaders({
  //   //       'Access-Control-Allow-Origin': 'http://localhost:',
  //   //       'Content-Type': 'application/json'
  //   //     })
  //   //   }

  //   ).pipe(catchError(this.helloWorld)).subscribe((response: HttpResponse<any>) => {
  //     console.log("Hello World Respopnse");
  //   });

    //this.http.get('https://localhost:9443/ConverterService/rest/converter/' + 
    this.http.get(environment.apiUrl + 
      this.inputUnit + 'to' + this.outputUnit + '/' + this.inputDegree)
      .pipe(catchError(this.handleError))
      .subscribe((response => {
        let result: any = response;
        this.outputDegree = result.degree.toString().includes('.') ? result.degree.toFixed(2): result.degree;
        // console.log(result.degree);
        // console.log(result.type);
        // console.log(result.message);
      }));


    // ).subscribe(response => {
    //   let result: any  = response;
    //   //this.outputDegree = result.resultDegree.toString().includes('.') ? result.resultDegree.toFixed(2): result.resultDegree;

    //   console.log(result);
    //   console.log(result.data);
    // });

}

handleError() {
  console.log("Handling Error!")
  return throwError('Error caught');
}


}
