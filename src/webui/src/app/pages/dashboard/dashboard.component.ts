import { Component, ViewChild } from "@angular/core";

import {
  ChartComponent,
  ApexAxisChartSeries,
  ApexChart,
  ApexXAxis,
  ApexDataLabels,
  ApexTitleSubtitle,
  ApexStroke,
  ApexGrid
} from "ng-apexcharts";

export type ChartOptions = {
  series: ApexAxisChartSeries;
  chart: ApexChart;
  xaxis: ApexXAxis;
  dataLabels: ApexDataLabels;
  grid: ApexGrid;
  stroke: ApexStroke;
  title: ApexTitleSubtitle;
};
var yil=new Date().getFullYear();
var ay=(new Date().getMonth())+1;
var gun=new Date().getDate();
var url;
let liste=[];
let list=new Array();

var tarih=`${yil}-${ay}-${gun}`;

for (let i=7;i>0;i--) {
  tarih=`${yil}-${ay}-${gun-i}`;
  getApi(`https://api.ratesapi.io/api/${tarih}?base=USD&symbols=TRY`)
}
function getApi(url) {
  fetch(url).then(res=>res.json())
    .then(data=>{
      //console.log(data['rates']['TRY']);
      return data['rates']['TRY'];
    }).then(gel=>{
    console.log(gel);
    list.push(gel);
  })
}

@Component({
  selector: "app-root",
  templateUrl: "./dashboard.component.html",
  styleUrls: ["./dashboard.component.css"]
})
export class DashboardComponent {
  @ViewChild("chart") chart: ChartComponent;
  public chartOptions: Partial<ChartOptions>;

  constructor() {
    setTimeout(() => {
      console.log('GELEN 1. DEĞER:'+list[0]);
      this.chartOptions = {
        series: [
          {
            name: "Desktops",
            data: [list[0], list[1], list[2], list[3], list[4], list[5], list[6]]
          }
        ],
        chart: {
          height: 350,
          type: "line",
          zoom: {
            enabled: false
          }
        },
        dataLabels: {
          enabled: false
        },
        stroke: {
          curve: "straight"
        },
        title: {
          text: "USD-TRY",
          align: "left"
        },
        grid: {
          row: {
            colors: ["#f3f3f3", "transparent"], // takes an array which will be repeated on columns
            opacity: 0.5
          }
        },
        xaxis: {
          categories: [
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7"

          ]
        }
      };

    },100);
  }
}
