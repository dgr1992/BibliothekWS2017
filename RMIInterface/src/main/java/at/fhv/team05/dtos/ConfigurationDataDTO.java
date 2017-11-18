package at.fhv.team05.dtos;

import java.io.Serializable;

/**
 * Created by Daniel on 18.11.2017.
 */
public class ConfigurationDataDTO implements Serializable{
    private String _name;
    private String _type;
    private String _value;

    public ConfigurationDataDTO (String name, String type, String value)  {
        _name = name;
        _type = type;
        _value = value;
    }

    public String getName(){
        return _name;
    }

    public void setName(String name){
        _name = name;
    }

    public String getType(){
        return _type;
    }

    public void setType(String type){
        _type = type;
    }

    public String getValue(){
        return _value;
    }

    public void setValue(String value){
        _value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConfigurationDataDTO that = (ConfigurationDataDTO) o;

        if (_name != null ? !_name.equals(that._name) : that._name != null) return false;
        if (_type != null ? !_type.equals(that._type) : that._type != null) return false;
        return _value != null ? _value.equals(that._value) : that._value == null;
    }

    @Override
    public int hashCode() {
        int result = _name != null ? _name.hashCode() : 0;
        result = 31 * result + (_type != null ? _type.hashCode() : 0);
        result = 31 * result + (_value != null ? _value.hashCode() : 0);
        return result;
    }
}
