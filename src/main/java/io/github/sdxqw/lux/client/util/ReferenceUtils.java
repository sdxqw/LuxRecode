package io.github.sdxqw.lux.client.util;

import lombok.Getter;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ReferenceUtils {

    @Getter
    public static final String name = "Lux Recode";
    @Getter
    public static final String version = "1.0";

    private ReferenceUtils() {
    }

    @SneakyThrows
    public static String readCommit() {
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec("git rev-parse --verify --short HEAD");
        final BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        return reader.readLine();
    }
}
