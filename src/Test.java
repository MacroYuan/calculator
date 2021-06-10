import java.io.IOException;

public class Test {
    public static void main(String[] args) {
        CalculatorRecordFile calculatorRecordFile = new CalculatorRecordFile("src/test.txt");
        try {
            calculatorRecordFile.write("ccc");
            calculatorRecordFile.write("ddd");
            calculatorRecordFile.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}