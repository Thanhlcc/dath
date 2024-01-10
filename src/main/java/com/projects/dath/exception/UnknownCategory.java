package com.projects.dath.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.spi.ObjectThreadContextMap;

import java.util.Map;
import java.util.regex.Pattern;

@Getter
public class UnknownCategory extends ResourceNotFound{
    String id;
    String name;
    public UnknownCategory(String msg, String name, String id) {
        super(msg);
        this.name = name;
        this.id = id;
    }

    public UnknownCategory(String msg, Map<String, Object> moreInfo) {
        super(msg);
        if (moreInfo.containsKey("name")) {
            this.name = (String) moreInfo.get("name");
        }
        if (moreInfo.containsKey("id")) {
            this.id = (String) moreInfo.get("id");
        } else {
            throw new RuntimeException("Unknown moreInfo key");
        }
    }
}
