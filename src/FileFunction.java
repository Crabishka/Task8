import util.*;

import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

public class FileFunction {
    public int[][] readArray2FromFile(String file) {
        return ArrayUtils.readIntArray2FromFile(file);
    }

    public void writeIntoFile(String Outputfile, String value) throws IOException {
        FileWriter writer = new FileWriter(Outputfile, false);
        writer.write(value);
        writer.close();
    }
/*
    public void writeIntoFile(File Outputfile, String value) throws IOException {
        FileWriter writer = new FileWriter(Outputfile, false);
        writer.write(value);
        writer.close();
    }
*/
}
