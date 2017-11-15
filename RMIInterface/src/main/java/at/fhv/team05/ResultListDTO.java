package at.fhv.team05;

import java.io.Serializable;
import java.util.List;

public class ResultListDTO<ObjectDTO> implements Serializable {
    private List<ObjectDTO> listDTO;
    private Exception exception;

    public ResultListDTO() {
    }

    public ResultListDTO(List<ObjectDTO> listDTO, Exception exception) {
        this.listDTO = listDTO;
        this.exception = exception;
    }

    public List<ObjectDTO> getListDTO() {
        return listDTO;
    }

    public void setListDTO(List<ObjectDTO> listDTO) {
        this.listDTO = listDTO;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
}
