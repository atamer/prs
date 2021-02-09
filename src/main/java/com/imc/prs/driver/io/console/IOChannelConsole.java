package com.imc.prs.driver.io.console;

import com.imc.prs.driver.io.IOChannel;
import com.imc.prs.driver.io.IOChannelException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class IOChannelConsole implements IOChannel {


    private final BufferedWriter writer;
    private final BufferedReader reader;

    private IOChannelConsole(BufferedWriter writer, BufferedReader reader) {
        if (writer == null) {
            throw new IOChannelException("writer is null");
        }
        if (reader == null) {
            throw new IOChannelException("reader is null");
        }
        this.writer = writer;
        this.reader = reader;
    }

    public static IOChannel newInstance(BufferedWriter writer, BufferedReader reader) {
        return new IOChannelConsole(writer, reader);
    }

    @Override
    public char promptAndReadCharacter(String prompt) {
        try {
            println(prompt);
            return (char) reader.readLine().toUpperCase().chars().findFirst().orElse(64);
        } catch (IOException exception) {
            throw new IOChannelException(exception.getMessage());
        }
    }

    @Override
    public void println(String line) {
        try {
            writer.write(line + '\n');
            writer.flush();
        } catch (IOException exception) {
            throw new IOChannelException(exception.getMessage());
        }
    }
}
