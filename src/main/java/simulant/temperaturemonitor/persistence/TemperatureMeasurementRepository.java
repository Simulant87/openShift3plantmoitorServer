package simulant.temperaturemonitor.persistence;

import org.springframework.data.repository.CrudRepository;
import simulant.temperaturemonitor.persistence.model.TemperatureMeasurement;

import java.util.List;

/**
 * Created by Nils on 18.03.2017.
 */
public interface TemperatureMeasurementRepository extends CrudRepository<TemperatureMeasurement, Long> {

    /**
     * returns the latest measurement.
     *
     * @return the latest measurement.
     */
    TemperatureMeasurement findFirstByOrderByDateDesc();

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
}
