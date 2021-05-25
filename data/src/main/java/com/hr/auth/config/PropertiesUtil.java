package com.hr.auth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("propertiesUtil")
public class PropertiesUtil {
    @Value("${naverHost}")
    private String naverHost;
    @Value("${naverId}")
    private String naverId;
    @Value("${naverPass}")
    private String naverPass;
    @Value("${naverPort}")
    private String naverPort;

    public String getNaverId() {
        return naverId;
    }

    public void setNaverId(String naverId) {
        this.naverId = naverId;
    }

    public String getNaverHost() {
        return naverHost;
    }

    public void setNaverHost(String naverHost) {
        this.naverHost = naverHost;
    }

    public String getNaverPass() {
        return naverPass;
    }

    public void setNaverPass(String naverPass) {
        this.naverPass = naverPass;
    }

    public String getNaverPort() {
        return naverPort;
    }

    public void setNaverPort(String naverPort) {
        this.naverPort = naverPort;
    }
    
}