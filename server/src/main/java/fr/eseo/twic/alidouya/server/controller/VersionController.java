package main.java.fr.eseo.twic.alidouya.server.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {

    @Value("${project.version}")
    private String version;

    @GetMapping("/version")
    public String getVersion() {
        return version;
    }
}
