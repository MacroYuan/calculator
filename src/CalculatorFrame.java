import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @Author donglin06
 * @Date 2021/6/9
 **/
public class CalculatorFrame extends JFrame implements ActionListener {
    JMenuBar mb;
    JMenu jm1,jm2;
    JMenuItem mi11,mi12,mi13,mi21,mi22,mi23;
    JTextField field;
    JButton[] b=new JButton[20];

    CalculatorRecordFile calculatorRecordFile = new CalculatorRecordFile("src/test.txt");
    /**
     * 声明button上面的label
     */
    String[] s = new String[]{"(",")","CE","←","7","8","9","/","4","5","6","*","1","2","3","-",".","0","=","+"};
    public CalculatorFrame(int x,int y ,int w, int h){
        createMenu();
        createPanel();
        setLocation(x,y);
        setSize(w,h);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        validate();
    }

    void createMenu() {			 //创建menu
        mb = new JMenuBar();
        jm1 = new JMenu("文件");
        mi11 = new JMenuItem("打开结果文件");
        mi12 = new JMenuItem("读取上一次的结果");

        jm1.add(mi11);
        jm1.addSeparator();   //在菜单之间增加分隔线
        jm1.add(mi12);


        mb.add(jm1);
        setJMenuBar(mb);

        mi11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    calculatorRecordFile.read();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        mi12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    field.setText(calculatorRecordFile.readLastLine());
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        });
    }


    public void createPanel(){		//创建menu下面的按钮面板

        //JButtonListener  jbl=new JButtonListener(this);//当前窗口做监视器

        Container container = this.getContentPane();
        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());
        field=new JTextField(25);
        field.setPreferredSize(new Dimension(500, 40));
        field.addActionListener(this);
        panel1.add(field);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(5,4));

        for(int i =0;i < 20 ; i++){
            b[i] = new JButton(s[i]);
            b[i].addActionListener(this);
            panel2.add(b[i]);
        }

        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(panel1);
        container.add(panel2);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        Object target = e.getSource();

        String label = e.getActionCommand();

        if ("0123456789.()+-*/".indexOf(label) > 0) {
            handleInput(label);
        } else if ("←".equals(label)) {
            handleback();
        } else if ("CE".equals(label)) {
            handleClear();
        } else if ("=".equals(label)) {
            try {
                handleEqual();
            } catch (Exception exception) {
                //弹窗
                JOptionPane.showMessageDialog(this, exception.getMessage());
                System.out.println("输入错误！");
            }
        } else {
            System.out.println("输入错误！");
        }
    }

    private void handleInput(String key) {
        if ((".".equals(key)) && (!field.getText().contains("."))) {
            field.setText(field.getText() + ".");
        } else if (!".".equals(key)) {
            field.setText(field.getText() + key);
        }
    }

    private void handleEqual() {
        String exp = field.getText();
        Double result = CalculatorUtil.calculator(field.getText());
        field.setText(result.toString());

        //写入文件
        try {
            calculatorRecordFile.write(exp + "=" + result.toString());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "结果写入错误！");
        }
    }

    private void handleClear() {
        field.setText("");
    }

    private void handleback() {
        if (field.getText().length() >= 1) {
            field.setText(field.getText().substring(0, field.getText().length() - 1));
        }
    }

    public static void main(String[] args) {
        CalculatorFrame c =new CalculatorFrame(150,150,300,250);
    }
}
