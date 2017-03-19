package simulant.temperaturemonitor.api;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import simulant.temperaturemonitor.persistence.TemperatureMeasurementRepository;
import simulant.temperaturemonitor.persistence.model.TemperatureMeasurement;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Nils on 18.03.2017.
 */
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
    public List<TemperatureMeasurement> getTemperatureClearList() {
        List<TemperatureMeasurement> measurements = temperatureMeasurementRepository.findAllByOrderByDateAsc();
        for (Iterator<TemperatureMeasurement> iterator = measurements.iterator(); iterator.hasNext(); ) {
            TemperatureMeasurement measurement = iterator.next();
            if (measurement.getHumidityValue() == null || measurement.getTemperatureValue() == null) {
                iterator.remove();
            }
        }
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
