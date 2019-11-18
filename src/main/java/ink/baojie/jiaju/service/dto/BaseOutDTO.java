package ink.baojie.jiaju.service.dto;

import ink.baojie.jiaju.base.exception.CustomError;
import lombok.Data;

import java.io.Serializable;

@Data
public class BaseOutDTO implements Serializable {
    private Integer code;
    private String msg;
    private Object data;

    public BaseOutDTO() {
        this.code = 0;
        this.msg = "成功";
    }

    public BaseOutDTO(String msg) {
        this.msg = msg;
    }

    public BaseOutDTO fail(int code, String msg) {
        this.code = code;
        this.msg = msg;
        return this;
    }

    public BaseOutDTO fail(CustomError customError) {
        this.code = customError.getCode();
        this.msg = customError.getMsg();
        return this;
    }
}
