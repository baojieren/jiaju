package ink.baojie.jiaju.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "settings")
@EnableConfigurationProperties
@Data
@Component
public class Settings {
    private boolean debug;

    public String getQnAccesskey() {
        return debug ? "7cZ_8aJnAv_kd1OczYns8bvPpjty1M7zJRD_757h" : "Ki7rB01Vf6T_dyiqFkV5a7ybfXFfrTnCaiQG33rH";
    }

    public String getQnSecretkey() {
        return debug ? "r4g6HmdsVMdHjeFGyvhrglqaZaAacheem_kOqEWw" : "yej8zBH7D7xebMZzSQSj2BOyxZN4TEtOrP5YkjJd";
    }

    public String getQnBucket() {
        return debug ? "baojie" : "xmg_img";
    }

    public String getQnDomain() {
        return debug ? "http://upload-z2.qiniup.com" : "http://upload-z2.qiniup.com";
    }

    /**
     * 域名
     */
    public String baseDomain() {
        return debug ? "https://api.baojie.ink" : "https://api.xminge.com";
    }

    /**
     * 微信支付结果通知地址
     */
    public String wxPayNotifyUrl() {
        return baseDomain() + "/xmg/notify/wxPay";
    }
}
