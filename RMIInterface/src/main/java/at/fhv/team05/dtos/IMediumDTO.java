package at.fhv.team05.dtos;

import java.util.Map;

public interface IMediumDTO {
    int getId();
    String getTitle();
    Map<String, Object> getAttributeMap();
}
