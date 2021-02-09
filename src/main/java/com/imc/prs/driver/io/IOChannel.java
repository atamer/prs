package com.imc.prs.driver.io;

public interface IOChannel {
    char promptAndReadCharacter(String prompt);

    void println(String line);
}
