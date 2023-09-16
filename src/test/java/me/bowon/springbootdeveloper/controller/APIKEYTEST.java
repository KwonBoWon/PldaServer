package me.bowon.springbootdeveloper.controller;

import org.springframework.beans.factory.annotation.Value;

public class APIKEYTEST {
    @Value("${youtube.key}")
    private String DEVELOPER_KEY;
    @Value("${youtube.application-name}")
    private String APPLICATION_NAME;
    public void sysout(){
        System.out.println(DEVELOPER_KEY);
        System.out.println(APPLICATION_NAME);
    }
    public static void main(String[] args) {
        APIKEYTEST apikeytest = new APIKEYTEST();
        apikeytest.sysout();
    }
}
