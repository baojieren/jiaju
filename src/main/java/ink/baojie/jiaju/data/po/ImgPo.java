package ink.baojie.jiaju.data.po;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ImgPo implements Serializable {
    private Integer id;

    private String title;

    private String url;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}