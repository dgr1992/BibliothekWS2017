package at.fhv.team05.server.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import at.fhv.team05.library.ObjectInterfaces.IConfigurationData;


@Entity
@Table(name = "ConfigurationData")
public class ConfigurationData implements IDomainObject, IConfigurationData {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int _id;

    @Column(name = "configName")
    private String _name;

    @Column(name = "configType")
    private String _type;

    @Column(name = "configValue")
    private String _value;

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public String getName() {
        return _name;
    }

    public void setName(String name){
        _name = name;
    }

    @Override
    public String getType() {
        return _type;
    }

    public void getType(String type){
        _type = type;
    }

    @Override
    public String getValue() {
        return _value;
    }

    public void setValue(String value) {
        _value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConfigurationData that = (ConfigurationData) o;

        if (_id != that._id) return false;
        if (_name != null ? !_name.equals(that._name) : that._name != null) return false;
        if (_type != null ? !_type.equals(that._type) : that._type != null) return false;
        return _value != null ? _value.equals(that._value) : that._value == null;
    }

    @Override
    public int hashCode() {
        int result = _id;
        result = 31 * result + (_name != null ? _name.hashCode() : 0);
        result = 31 * result + (_type != null ? _type.hashCode() : 0);
        result = 31 * result + (_value != null ? _value.hashCode() : 0);
        return result;
    }
}
