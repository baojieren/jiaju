package ink.baojie.jiaju.base.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 异常处理
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomException extends RuntimeException {
    private CustomError error;
    protected Throwable throwable;

    public CustomException(CustomError error) {
        this.error = error;
    }
}
