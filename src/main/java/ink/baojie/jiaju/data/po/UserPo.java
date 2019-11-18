package ink.baojie.jiaju.data.po;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class UserPo implements Serializable {
    private Integer id;

    private String userName;

    private String phone;

    private String password;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}