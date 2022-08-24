package io.github.axst.client;

import lombok.Getter;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Reference {

    @Getter
    public static final String name = "LuxRecode";
    @Getter
    public static final String version = "1.0";

    private Reference() {
    }

    @SneakyThrows
    public static String readCommit() {
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec("git rev-parse --verify --short HEAD");
        final BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        return reader.readLine();
    }
}
