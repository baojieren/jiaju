package ink.baojie.jiaju.data.bo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QiNiuTokenBO {
    private String domain;
    private String token;
    private String fileKey;
}
