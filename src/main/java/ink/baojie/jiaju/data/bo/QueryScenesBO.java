package ink.baojie.jiaju.data.bo;

import lombok.Data;

import java.util.List;

@Data
public class QueryScenesBO {
    private Integer id;

    /**
     * 标题
     */
    private String title;

    private List<String> img;

    private String remake;

    /**
     * 0:无效,1:有效
     */
    private Integer valid;

    private String createTime;

    private String updateTime;
}
