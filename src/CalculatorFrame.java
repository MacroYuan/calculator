import javax.swing.*;
import java.awt.*;

/**
 * @Author donglin06
 * @Date 2021/6/9
 **/
public class CalculatorFrame {
    private JFrame jf;
    private JTextField display;
    private JButton equalBtn;
    private JButton backBtn;
    private JButton clearBtn;

    public CalculatorFrame() {
        //得到工具类
        Toolkit tk = Toolkit.getDefaultToolkit();
        this.jf = new JFrame();
        this.jf.setSize(500,400);
        this.jf.setLocation((int)(tk.getScreenSize().getWidth()-500)/2, (int)(tk.getScreenSize().getHeight()-400)/2);
        this.jf.setTitle("Macro的计算器");
        this.jf.setIconImage(tk.createImage(""));
        this.jf.setResizable(false);
        this.jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.addActionButton();
        this.addDisplay();
        this.jf.setVisible(true);
    }

    private void addActionButton() {
        JPanel panel = new JPanel(null);

        equalBtn = new JButton("=");
        equalBtn.setSize(50, 50);
        equalBtn.setLocation(250, 400);
//        equalBtn.addActionListener();
        panel.add(equalBtn);

        backBtn = new JButton("Back");
        backBtn.setSize(50, 50);
        backBtn.setLocation(200,400);
        panel.add(backBtn);

        clearBtn = new JButton("Clear");
        clearBtn.setSize(50, 50);
        clearBtn.setLocation(150, 400);

        jf.setContentPane(panel);
    }

    private void addDisplay() {
        JPanel panel = new JPanel(null);

        display = new JTextField("");
        display.setEnabled(true);
        panel.add(display, BorderLayout.NORTH);

        jf.setContentPane(panel);
    }

    public static void main(String[] args) {
        CalculatorFrame calculatorFrame = new CalculatorFrame();
    }
}
