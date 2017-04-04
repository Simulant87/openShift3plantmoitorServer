package simulant.temperaturemonitor.api;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import simulant.temperaturemonitor.persistence.TemperatureMeasurementRepository;
import simulant.temperaturemonitor.persistence.model.TemperatureMeasurement;

import java.util.Date;
import java.util.List;

/**
 * Created by Nils on 18.03.2017.
 */
@CrossOrigin
@Controller
public class TemperatureMonitor {

    @Autowired
    private TemperatureMeasurementRepository temperatureMeasurementRepository;

    @RequestMapping(value = "/temperature", method = RequestMethod.GET)
    @ResponseBody
    public List<TemperatureMeasurement> getTemperatureList() {
        List<TemperatureMeasurement> measurements = temperatureMeasurementRepository.findAllByOrderByDateAsc();
        return measurements;
    }

    @RequestMapping(value = "/temperature/clear", method = RequestMethod.GET)
    @ResponseBody
    public List<TemperatureMeasurement> getTemperatureClearList(
            @RequestParam(value="fromDate", required = false) @DateTimeFormat(pattern="MM-dd-yyyy") Date fromDate,
            @RequestParam(value="toDate", required = false) @DateTimeFormat(pattern="MM-dd-yyyy") Date toDate) {
        if(fromDate == null) {
            fromDate = new Date(0L);
        }
        if(toDate == null) {
            toDate = new Date();
        }
        List<TemperatureMeasurement> measurements = temperatureMeasurementRepository
                .findByDateBetweenAndHumidityValueIsNotNullAndTemperatureValueIsNotNullOrderByDateAsc(fromDate, toDate);
        return measurements;
    }

    @RequestMapping(value = "/temperature/{id}", method = RequestMethod.GET)
    @ResponseBody
    public TemperatureMeasurement getTemperature(@RequestParam("id") long id) {
        TemperatureMeasurement lastMeasurement = temperatureMeasurementRepository.findOneById(id);
        return lastMeasurement;
    }

    @RequestMapping(value = "/temperature", method = RequestMethod.POST)
    @ResponseBody
    public String postTemperature(@RequestBody TemperatureMeasurement temperatureMeasurement) {
        System.out.println("received: " + ToStringBuilder.reflectionToString(temperatureMeasurement));
        temperatureMeasurementRepository.save(temperatureMeasurement);
        return null;
    }

}
