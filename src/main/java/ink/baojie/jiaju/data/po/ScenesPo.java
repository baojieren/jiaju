package ink.baojie.jiaju.data.po;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ScenesPo implements Serializable {
    private Integer id;

    /**
    * 标题
    */
    private String title;

    /**
    * 图片id,多个用逗号隔开
    */
    private String img;

    private String remake;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}