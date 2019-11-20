package ink.baojie.jiaju.data.bo;

import lombok.Data;

@Data
public class LoginBO {
    private Integer id;

    private String userName;

    private String phone;

    private String password;

    private String token;

}
