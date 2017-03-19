var xhttp = new XMLHttpRequest();
xhttp.open("GET", "http://webproject-simulant.rhcloud.com/temperature", false);
xhttp.send();
var json = $.parseJSON(xhttp.responseText);
var labelsData = [];
var humidityData = [];
var temperatureData = [];
for(int i = 0; i < json.length; i++) {
    var measurement = json[i];
    document.getElementById("demo").innerHTML = measurement;
}

var canvas = document.getElementById('chart');
new Chart(canvas, {
  type: 'line',
  data: {
    labels: ['1489922316000', '1489922436000', '1489922436000', '1489923035000'],
    datasets: [{
      label: 'temperature in Celsius',
      yAxisID: 'temperature',
      data: [18.8, null, 20.8, 20.2],
      backgroundColor: "rgba(255, 153, 0, 0.6)"
    }, {
      label: 'humidity in percentage',
      yAxisID: 'humidity',
      data: [60.99995559, 80.3456765, null, 35.23456],
      backgroundColor: "rgba(153, 255, 51, 0.6)"
    }]
  },
  options: {
    scales: {
      yAxes: [{
        id: 'temperature',
        type: 'linear',
        position: 'left',
      }, {
        id: 'humidity',
        type: 'linear',
        position: 'right'
      }]
    }
  }
});