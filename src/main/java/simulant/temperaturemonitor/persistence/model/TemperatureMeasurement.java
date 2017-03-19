package simulant.temperaturemonitor.persistence.model;

import org.hibernate.annotations.Index;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by Nils on 18.03.2017.
 */
@Entity
public class TemperatureMeasurement {

    @Id
    @GeneratedValue
    private long id;
    @Index(name = "tm_date")
    @Type(type = "timestamp")
    private Date date;
    private Double temperatureValue;
    private String temperatureUnit;
    private Double humidityValue;
    private String humidityUnit;
    private String location;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getTemperatureValue() {
        return temperatureValue;
    }

    public void setTemperatureValue(Double temperatureValue) {
        this.temperatureValue = temperatureValue;
    }

    public String getTemperatureUnit() {
        return temperatureUnit;
    }

    public void setTemperatureUnit(String temperatureUnit) {
        this.temperatureUnit = temperatureUnit;
    }

    public Double getHumidityValue() {
        return humidityValue;
    }

    public void setHumidityValue(Double humidityValue) {
        this.humidityValue = humidityValue;
    }

    public String getHumidityUnit() {
        return humidityUnit;
    }

    public void setHumidityUnit(String humidityUnit) {
        this.humidityUnit = humidityUnit;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
