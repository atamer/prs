package com.imc.prs.driver.io;

import com.imc.prs.driver.io.console.IOChannelConsole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IOChannelTest {

    @Mock
    BufferedWriter writer;
    @Mock
    BufferedReader reader;


    @Test
    void newInstanceConsole_emptyReader_ThrowException() {
        Assertions.assertThrows(IOChannelException.class, () -> IOChannelConsole.newInstance(writer, null));
    }

    @Test
    void newInstanceConsole_emptyWriter_ThrowException() {
        Assertions.assertThrows(IOChannelException.class, () -> IOChannelConsole.newInstance(null, reader));
    }

    @Test
    void channelConsole_promptAndReadCharacter() throws IOException {
        //prepare
        IOChannel ioConsole = spy(IOChannelConsole.newInstance(writer, reader));
        String test = "test";
        when(reader.readLine()).thenReturn(test);
        //call
        ioConsole.promptAndReadCharacter(test);
        //verify
        verify(reader).readLine();
    }

}
