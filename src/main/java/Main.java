import com.imc.prs.driver.DriverImpl;
import com.imc.prs.driver.io.IOChannel;
import com.imc.prs.driver.io.IOChannelFactory;

public class Main {

    public static void main(String[] args) {

        IOChannel channel = IOChannelFactory.createIOChannelConsole();
        channel.println("How will players be communicated \n");
        channel.println("1) Console Mode(C)");
        channel.println("2) File Mode(F) - You need to create resource/input.txt file , each line one hand before start ");
        char selection = channel.promptAndReadCharacter("Please Press " + IOChannelFactory.CONNECTION_TYPE_CONSOLE
                + " or " + IOChannelFactory.CONNECTION_TYPE_FILE);

        DriverImpl.createDriver().startNewGameWithPlayer(1, selection);

    }
}
