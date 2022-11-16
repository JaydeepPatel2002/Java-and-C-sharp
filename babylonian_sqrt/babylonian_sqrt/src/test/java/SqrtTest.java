import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class SqrtTest {
    private final InputStream sysInBackup = System.in;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;


    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
        System.setIn(sysInBackup);
    }

    @Test
    void sqrt() {
        assertTrue(Sqrt.sqrt(2.0,1E-10)>1.414);
        assertTrue(Sqrt.sqrt(2.0,1E-10)<1.415);
    }

    @Test
    void main(){
        InputStream in = new ByteArrayInputStream("3".getBytes());
        System.setIn(in);
        Sqrt.main(null);
        String val = outContent.toString();
        assertTrue(val.startsWith("1.732"));
    }
}