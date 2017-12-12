package at.fhv.team05.server.Application;

import at.fhv.team05.library.ObjectInterfaces.IConfigurationData;
import at.fhv.team05.library.ResultDTO;
import at.fhv.team05.server.domain.ConfigurationData;
import at.fhv.team05.library.dtos.ConfigurationDataDTO;

import java.util.Objects;

/**
 * Created by Daniel on 18.11.2017.
 */
public class ConfigurationDataController extends BaseController<ConfigurationData, ConfigurationDataDTO> {
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
        return new ConfigurationDataDTO(object.getName(), object.getType(), object.getValue());
    }

    @Override
    protected boolean compareInput(ConfigurationData object, ConfigurationDataDTO configurationDataDTO) {
        boolean nameMatch = object.getName() != null && configurationDataDTO.getName() != null && Objects.equals(object.getName(), configurationDataDTO.getName());
        boolean typMatch = object.getType() != null && configurationDataDTO.getType() != null && Objects.equals(object.getType(), configurationDataDTO.getName());
        boolean valueMatch = object.getValue() != null && configurationDataDTO.getValue() != null && Objects.equals(object.getValue(), configurationDataDTO.getValue());
        return nameMatch && typMatch && valueMatch;
    }

    /**
     * Searches for a configuration value by the config name
     *
     * @param name
     * @return
     */
    public IConfigurationData getConfigFor(String name) {
        IConfigurationData configData = null;
        for (ConfigurationData configurationData : _mapDomainToDto.keySet()) {
            if (configurationData.getName().compareTo(name) == 0) {
                configData = configurationData;
                break;
            }
        }
        return configData;
    }

    /**
     * Searches for a configuration value by the config name
     *
     * @param name
     * @return
     */
    public ResultDTO<ConfigurationDataDTO> getConfigDTOFor(String name) {
        for (ConfigurationData configurationData : _mapDomainToDto.keySet()) {
            if (configurationData.getName().compareTo(name) == 0) {
                return new ResultDTO<>(_mapDomainToDto.get(configurationData), null);
            }
        }
        return null;
    }
}
