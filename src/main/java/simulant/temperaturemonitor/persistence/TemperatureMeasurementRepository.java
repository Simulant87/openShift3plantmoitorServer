package simulant.temperaturemonitor.persistence;

import org.springframework.data.repository.CrudRepository;
import simulant.temperaturemonitor.persistence.model.TemperatureMeasurement;

import java.util.List;

/**
 * Created by Nils on 18.03.2017.
 */
public interface TemperatureMeasurementRepository extends CrudRepository<TemperatureMeasurement, Long> {

    /**
     * returns the measurement by id.
     *
     * @return the measurement.
     */
    TemperatureMeasurement findOneById(long id);

    /**
     * returns all measurements.
     *
     * @return all measurements.
     */
    List<TemperatureMeasurement> findAllByOrderByDateAsc();

    /**
     * returns all measurements with valid values.
     *
     * @return all measurements with complete values.
     */
    List<TemperatureMeasurement> findAllAndHumidityValueIsNotNullAndTemperatureValueIsNotNullByOrderByDateAsc();
}
