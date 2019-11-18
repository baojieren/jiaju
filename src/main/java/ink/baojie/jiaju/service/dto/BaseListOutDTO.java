package ink.baojie.jiaju.service.dto;

import ink.baojie.jiaju.base.exception.CustomError;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author baojieren
 */
@Data
public class BaseListOutDTO implements Serializable {
    private Integer code;
    private String msg;
    public Data data;

    public BaseListOutDTO fail(int code, String msg) {
        this.code = code;
        this.msg = msg;
        return this;
    }

    public BaseListOutDTO fail(CustomError customError) {
        this.code = customError.getCode();
        this.msg = customError.getMsg();
        return this;
    }

    public BaseListOutDTO() {
        this.code = 0;
        this.msg = "成功";
        Data data = new Data();
        data.total = 0;
        data.list = new ArrayList();
        this.data = data;
    }

    public BaseListOutDTO setData(List list) {
        this.getData().setList(list);
        return this;
    }

    public BaseListOutDTO setTotal(int total) {
        this.getData().setTotal(total);
        return this;
    }

    @lombok.Data
    public class Data {
        public Integer total;
        public List list;
    }
}
