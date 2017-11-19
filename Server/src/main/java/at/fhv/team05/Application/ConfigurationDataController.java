package at.fhv.team05.Application;

import at.fhv.team05.ObjectInterfaces.IConfigurationData;
import at.fhv.team05.ResultDTO;
import at.fhv.team05.domain.ConfigurationData;
import at.fhv.team05.dtos.ConfigurationDataDTO;

/**
 * Created by Daniel on 18.11.2017.
 */
public class ConfigurationDataController extends BaseController<ConfigurationData,ConfigurationDataDTO>{
    private static ConfigurationDataController _theInstance;

    private ConfigurationDataController() {
        super(ConfigurationData.class);
    }

    public static ConfigurationDataController getInstance() {
        if (_theInstance == null) {
            _theInstance = new ConfigurationDataController();
        }
        return _theInstance;
    }

    @Override
    protected ConfigurationDataDTO createDTO(ConfigurationData object) {
        return new ConfigurationDataDTO(object.getName(),object.getType(),object.getValue());
    }

    @Override
    protected boolean compareInput(ConfigurationData object, ConfigurationDataDTO configurationDataDTO) {
        boolean nameMatch = object.getName() != null && configurationDataDTO.getName() != null  && object.getName() == configurationDataDTO.getName();
        boolean typMatch = object.getType() != null && configurationDataDTO.getType() != null && object.getType() == configurationDataDTO.getName();
        boolean valueMatch = object.getValue() != null && configurationDataDTO.getValue() != null && object.getValue() == configurationDataDTO.getValue();
        return nameMatch && typMatch && valueMatch;
    }

    public IConfigurationData getConfigFor(String name){
        IConfigurationData configData = null;
        for (ConfigurationData configurationData : _mapDomainToDto.keySet()) {
            if(configurationData.getName().compareTo(name) == 0){
                configData = configurationData;
                break;
            }
        }
        return configData;
    }

    public ResultDTO<ConfigurationDataDTO> getConfigDTOFor(String name){
        for (ConfigurationData configurationData : _mapDomainToDto.keySet()) {
            if(configurationData.getName().compareTo(name) == 0){
                return new ResultDTO<>(_mapDomainToDto.get(configurationData),null);
            }
        }
        return null;
    }
}
