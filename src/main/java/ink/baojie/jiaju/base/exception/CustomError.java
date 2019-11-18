package ink.baojie.jiaju.base.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomError {
    private int code;
    private String msg;

    private static final int AUTH_ERR_CODE_START = 100;

    public static final int SYS_ERR_CODE_START = 10000;

    public static int NEXT_ERR_CODE_START = 20000;


    public static final CustomError AUTH_OK = new CustomError(0, "鉴权成功");
    public static final CustomError ERR_REQUEST = new CustomError(400, "请求错误");
    public static final CustomError AUTH_FAILED = new CustomError(401, "鉴权失败");
    public static final CustomError LOGIN_FAILED = new CustomError(402, "登录失败");
    public static final CustomError AUTH_USER_DENIED = new CustomError(403, "用户无权限访问");

    public static final CustomError ERR_USER_NOT_CREATED = new CustomError(AUTH_ERR_CODE_START + 1, "创建用户失败");
    public static final CustomError ERR_USER_NOT_DELETED = new CustomError(AUTH_ERR_CODE_START + 2, "删除用户失败");
    public static final CustomError ERR_PW_NOT_UPDATED = new CustomError(AUTH_ERR_CODE_START + 3, "修改密码失败");
    public static final CustomError ERR_USER_NOT_EXIST = new CustomError(AUTH_ERR_CODE_START + 4, "未找到该用户");
    public static final CustomError ERR_USER_ALREADY_EXIST = new CustomError(AUTH_ERR_CODE_START + 5, "用户已存在");
    public static final CustomError AUTH_F_USER_NOT_MATCH = new CustomError(AUTH_ERR_CODE_START + 6, "用户不匹配");

    public static final CustomError ERR_LOGIN_WRONG_ACC_OR_PW = new CustomError(AUTH_ERR_CODE_START + 11, "账户/密码错误");
    public static final CustomError ERR_LOGIN_ATTEMPTS_EX = new CustomError(AUTH_ERR_CODE_START + 12, "登录失败次数过多");
    public static final CustomError ERR_LOGIN_USER_LOCKED = new CustomError(AUTH_ERR_CODE_START + 13, "账号已被锁定");
    public static final CustomError ERR_LOGIN_AUTH_FAILED = new CustomError(AUTH_ERR_CODE_START + 14, "没有访问权限");
    public static final CustomError ERR_LOGIN_AUTH_EXPIRED = new CustomError(AUTH_ERR_CODE_START + 15, "凭证已过期,请重新登录");
    public static final CustomError ERR_LOGIN_CODE_INVALID = new CustomError(AUTH_ERR_CODE_START + 16, "短信验证码异常");

    public static final CustomError ERR_QR_CODE_EXPIRED = new CustomError(AUTH_ERR_CODE_START + 21, "二维码已失效");
    public static final CustomError ERR_QR_INVALID_DATA = new CustomError(AUTH_ERR_CODE_START + 22, "登录信息错误");

    public static final CustomError SYS_ERR = new CustomError(SYS_ERR_CODE_START, "服务器异常");

    public static final CustomError IP_ERR = new CustomError(SYS_ERR_CODE_START + 1, "请求IP异常");

    public static final CustomError DB_ERR = new CustomError(SYS_ERR_CODE_START + 2, "数据库异常");

    public static final CustomError LOGIN_ERR = new CustomError(SYS_ERR_CODE_START + 3, "登录异常，请重新登录");

    public static final CustomError RETRY = new CustomError(AUTH_ERR_CODE_START + 4, "请重试");

    // 项目异常


    public CustomError nextError(String msg) {
        this.code = NEXT_ERR_CODE_START++;
        this.msg = msg;
        return this;
    }
}
