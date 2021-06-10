import java.awt.*;
import java.io.*;
import java.util.Scanner;

/**
 * @Author donglin06
 * @Date 2021/6/10
 **/
public class CalculatorRecordFile {
    private String filePath;

    public CalculatorRecordFile(String filePath) {
        this.filePath = filePath;
    }

    public void write(String inputRecord) throws FileNotFoundException, IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(file, true);
        OutputStreamWriter out = new OutputStreamWriter(fos, "UTF-8");
        //写入记录
        out.write(inputRecord + "\r\n");
        out.flush();
        out.close();
    }

    public String readLastLine() throws FileNotFoundException {
        FileReader fileReader = new FileReader(filePath);
        Scanner sc = new Scanner(fileReader);
        String line = null;
        while (sc.hasNextLine() && (line = sc.nextLine()) != null) {
            if (!sc.hasNextLine()) {
                sc.close();
                return line;
            }
        }
        sc.close();
        return "";
    }

    public void read() throws IOException{
        File file = new File(filePath);

        // 首先检查平台是否支持Desktop
        if(!Desktop.isDesktopSupported()){
            System.out.println("Desktop is not supported");
            return;
        }

        Desktop desktop = Desktop.getDesktop();
        if(file.exists()) {
            desktop.open(file);
        } else {
            System.out.println("The record file is not exist");
        }
    }
}
