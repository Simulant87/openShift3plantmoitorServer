package simulant.temperaturemonitor.api;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import simulant.temperaturemonitor.persistence.TemperatureMeasurementRepository;
import simulant.temperaturemonitor.persistence.model.TemperatureMeasurement;

/**
 * Created by Nils on 18.03.2017.
 */
@Controller
public class TemperatureMonitor {

    @Autowired
    private TemperatureMeasurementRepository temperatureMeasurementRepository;

    @RequestMapping(value = "/temperature", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody TemperatureMeasurement getTemperature() {
        TemperatureMeasurement lastMeasurement = temperatureMeasurementRepository.findFirstByOrderByDateDesc();
        return lastMeasurement;
    }

    @RequestMapping(value = "/temperature", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody String postTemperature(@RequestBody TemperatureMeasurement temperatureMeasurement) {
        System.out.println("received: " + ToStringBuilder.reflectionToString(temperatureMeasurement));
        temperatureMeasurementRepository.save(temperatureMeasurement);
        return null;
    }

}
