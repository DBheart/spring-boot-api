package kr.deity.springboot3.sample;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Value;

@Value
public class LombokBean {
    int red;
    int green;
    int blue;

    public String getHexString() {
        return String.format("#%02X%02X%02X", red, green, blue);
    }
}
