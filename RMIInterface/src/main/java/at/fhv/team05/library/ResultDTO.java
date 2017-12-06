package at.fhv.team05.library;

import java.io.Serializable;

public class ResultDTO<ObjectDTO> implements Serializable {
    private ObjectDTO dto;
    private Exception exception;

    public ResultDTO() {
    }

    public ResultDTO(ObjectDTO dto, Exception exception) {
        this.dto = dto;
        this.exception = exception;
    }

    public ObjectDTO getDto() {
        return dto;
    }

    public void setDto(ObjectDTO dto) {
        this.dto = dto;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
}
