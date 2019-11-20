package ink.baojie.jiaju.service.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class SelectScenesListInDTO implements Serializable {
    public String title;
    public Integer valid;
    public Integer pageIndex;
    public Integer pageSize;
}
