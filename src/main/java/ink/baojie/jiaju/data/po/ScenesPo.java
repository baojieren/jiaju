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

    /**
     * 0:无效,1:有效
     */
    private Integer valid;

    private String createTime;

    private String updateTime;

    private static final long serialVersionUID = 1L;
}
