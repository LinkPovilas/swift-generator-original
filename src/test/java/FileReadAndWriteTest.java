import enums.Bic;
import paymentDocument.FileCreator;
import org.junit.Test;

import java.time.LocalDate;

public class FileReadAndWriteTest {

    @Test
    public void testFileWrite() {
        FileCreator file = new FileCreator();
        file.writeToFile(
                "FLV",
                LocalDate.now().toString(),
                "EUR",
                "10",
                Bic.UNLALV2X,
                "LV101000",
                "Test Test",
                1,
                false);
    }
}
