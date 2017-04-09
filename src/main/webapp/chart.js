Number.prototype.padLeft = function(base, chr){
   var len = (String(base || 10).length - String(this).length) + 1;
   return len > 0 ? new Array(len).join(chr || '0') + this : this;
}

//formats a date to yyyy-mm-dd HH:MM:SS
function dateTimeFormat(date) {
    return [ date.getFullYear(),
    (date.getMonth()+1).padLeft(),
    date.getDate().padLeft(),
    ].join('-') +
    ' ' +
    [ date.getHours().padLeft(),
    date.getMinutes().padLeft(),
    date.getSeconds().padLeft()].join(':');
}

function updateChart(fromDate, toDate) {
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", "/temperature?fromDate=" + fromDate + "&toDate=" + toDate, false);
    xhttp.send();
    var json = $.parseJSON(xhttp.responseText);
    var labelsData = [];
    var humidityData = [];
    var temperatureData = [];
    var luxData = [];
    var moistureData = [];
    for(var i = 0; i < json.length; i++) {
        var measurement = json[i];
        labelsData.push(dateTimeFormat(new Date(measurement.date)));
        humidity = measurement.humidityValue;
        if(humidity == null) {
            humidity = 0.0;
        }
        humidityData.push(humidity);

        temperature = measurement.temperatureValue;
        if(temperature == null) {
            temperature = 0.0;
        }
        temperatureData.push(temperature);

        lux = measurement.luxValue;
        if(lux == null) {
            lux = 0;
        }
        luxData.push(lux);

        moisture = measurement.moistureValue;
        if(moisture == null) {
            moisture = 0;
        }
        moistureData.push(moisture);
    }

    var canvas = document.getElementById('temperatureChart');
    new Chart(canvas, {
        type: 'line',
        data: {
            labels: labelsData,
            datasets: [{
                label: 'temperature in Celsius',
                yAxisID: 'temperature',
                data: temperatureData,
                backgroundColor: "rgba(255, 153, 0, 0.6)"
            }, {
                label: 'humidity in percentage',
                yAxisID: 'humidity',
                data: humidityData,
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


    var canvas = document.getElementById('luxChart');
    new Chart(canvas, {
        type: 'line',
        data: {
            labels: labelsData,
            datasets: [{
                label: 'Brightness in Lux',
                yAxisID: 'lux',
                data: luxData,
                backgroundColor: "rgba(255, 255, 102, 0.6)"
            }, {
                label: 'moisture in percentage',
                yAxisID: 'moisture',
                data: moistureData,
                backgroundColor: "rgba(0, 191, 255, 0.6)"
            }]
        },
        options: {
            scales: {
                yAxes: [{
                    id: 'lux',
                    type: 'linear',
                    position: 'left',
                }, {
                    id: 'moisture',
                    type: 'linear',
                    position: 'right'
                }]
            }
        }
    });
}

//setup date picker script fromDate
$(function() {
    $("#fromDate").datepicker({
        dateFormat: "dd-mm-yy",
        onSelect: function(dateText, inst) {
            var date = $.datepicker.parseDate(inst.settings.dateFormat
                    || $.datepicker._defaults.dateFormat, dateText, inst.settings);
        }
    });
});

//setup date picker script toDate
$(function() {
    $("#toDate").datepicker({
        dateFormat: "dd-mm-yy",
        onSelect: function(dateText, inst) {
            var date = $.datepicker.parseDate(inst.settings.dateFormat
                    || $.datepicker._defaults.dateFormat, dateText, inst.settings);
        }
    });
});

