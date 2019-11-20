package ink.baojie.jiaju.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SaveScenesInDTO implements Serializable {
    private Integer id;
    /**
     * 标题
     */
    private String title;

    /**
     * 图片url,多个用逗号隔开
     */
    private List<String> imgUrlList;

    private String remake;
}
