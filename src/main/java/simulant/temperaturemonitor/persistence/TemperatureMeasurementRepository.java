package simulant.temperaturemonitor.persistence;

import org.springframework.data.repository.CrudRepository;
import simulant.temperaturemonitor.persistence.model.TemperatureMeasurement;

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
}
