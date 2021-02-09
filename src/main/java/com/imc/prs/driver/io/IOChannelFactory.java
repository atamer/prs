package com.imc.prs.driver.io;

import com.imc.prs.driver.io.console.IOChannelConsole;
import com.imc.prs.driver.io.file.IOChannelFile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class IOChannelFactory {

    public static final char CONNECTION_TYPE_FILE = 'F';
    public static final char CONNECTION_TYPE_CONSOLE = 'C';
    public static final String INPUT_FILE = "src/main/resources/input.txt";
    public static final String OUTPUT_FILE = "src/main/resources/output.txt";


    public static IOChannel createIOChannel(char connection) {
        if (connection == CONNECTION_TYPE_FILE) {
            return createIOChannelFile();
        } else if (connection == CONNECTION_TYPE_CONSOLE) {
            return createIOChannelConsole();
        } else {
            throw new IllegalArgumentException("Connection type " + connection + " undefined");
        }

    }

    public static IOChannel createIOChannelConsole() {
        return IOChannelConsole.newInstance(new BufferedWriter(new OutputStreamWriter(System.out)),
                new BufferedReader(new InputStreamReader(System.in)));
    }

    public static IOChannel createIOChannelFile() {
        return IOChannelFile.newInstance(OUTPUT_FILE, INPUT_FILE);

    }
}
