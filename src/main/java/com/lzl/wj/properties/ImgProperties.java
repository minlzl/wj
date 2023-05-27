package com.lzl.wj.properties;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
public class ImgProperties {

    @Value("${img.folder}")
    String folder;

    @Value("${img.baseUrl}")
    String baseUrl;

}
