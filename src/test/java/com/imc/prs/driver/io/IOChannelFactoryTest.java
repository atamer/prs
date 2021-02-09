package com.imc.prs.driver.io;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class IOChannelFactoryTest {

    @Test
    void createIOChannel_invalidChar_throwException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> IOChannelFactory.createIOChannel('A'));
    }

    @Test
    void createIOChannel_validCharC_returnConnection() {
        Assertions.assertNotNull(IOChannelFactory.createIOChannel(IOChannelFactory.CONNECTION_TYPE_CONSOLE));
    }

    @Test
    void createIOChannel_validCharF_returnConnection() {
        Assertions.assertNotNull(IOChannelFactory.createIOChannel(IOChannelFactory.CONNECTION_TYPE_FILE));
    }

}
