package com.imc.prs.driver.io.file;

import com.imc.prs.domain.hands.Hands;
import com.imc.prs.driver.io.IOChannel;
import com.imc.prs.driver.io.IOChannelException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class IOChannelFile implements IOChannel {


    private final String outputFile;
    private final List<String> inputLines;


    private IOChannelFile(String outputFile, String inputFile) {
        try {
            this.outputFile = outputFile;
            this.inputLines = Files.readAllLines(Paths.get(inputFile));
        } catch (IOException exception) {
            throw new IOChannelException(exception.getMessage());
        }
    }

    public static IOChannel newInstance(String outputFile, String inputFile) {
        return new IOChannelFile(outputFile, inputFile);
    }

    @Override
    public char promptAndReadCharacter(String prompt) {
        // skip prompt
        if (!inputLines.isEmpty()) {
            return inputLines.remove(0).charAt(0);
        }
        return Hands.QUIT.synonym;
    }

    @Override
    public void println(String line) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.outputFile, true))) {
            bw.append(line).append("\n");
            bw.flush();
        } catch (IOException exception) {
            throw new IOChannelException(exception.getMessage());
        }
    }
}
