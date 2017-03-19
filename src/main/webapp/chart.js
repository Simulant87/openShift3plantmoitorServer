var canvas = document.getElementById('chart');
new Chart(canvas, {
  type: 'line',
  data: {
    labels: ['1489922316000', '1489922436000', '1489922436000', '1489923035000'],
    datasets: [{
      label: 'temperature in Celsius',
      yAxisID: 'temperature',
      data: [18.8, null, 20.8, 20.2]
    }, {
      label: 'humidity in percentage',
      yAxisID: 'humidity',
      data: [60.99995559, 80.3456765, null, 35.23456]
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