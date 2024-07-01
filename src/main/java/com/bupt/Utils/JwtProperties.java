package com.bupt.Utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "bupt.jwt")
@Data
public class JwtProperties {

    /**
     * 管理员生成jwt令牌相关配置
     */
    private String adminSecretKey;
    private long adminTtl;
    private String adminTokenName;

    /**
     * 医生生成jwt令牌相关配置
     */
    private String doctorSecretKey;
    private long doctorTtl;
    private String doctorTokenName;

    /**
     * 患者生成jwt令牌相关配置
     */
    private String patientSecretKey;
    private long patientTtl;
    private String patientTokenName;

}
