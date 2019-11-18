package ink.baojie.jiaju;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ServletComponentScan
@MapperScan({"ink.baojie.jiaju.data.dao"})
@EnableScheduling
@EnableAsync
public class JiajuApplication {

    public static void main(String[] args) {
        SpringApplication.run(JiajuApplication.class, args);
    }

}
