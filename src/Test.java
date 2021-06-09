/**
 * @Author donglin06
 * @Date 2021/6/9
 **/

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Test extends JFrame implements ActionListener {
    JMenuBar mb;
    JMenu jm1,jm2;
    JMenuItem mi11,mi12,mi13,mi21,mi22,mi23;
    JTextField field;
    JButton[] b=new JButton[20];
    /**
     * 声明button上面的label
     */
    String[] s = new String[]{"(",")","CE","←","7","8","9","/","4","5","6","*","1","2","3","-",".","0","=","+"};
    public Test(int x,int y ,int w, int h){
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
        jm1 = new JMenu("Edit");
        jm2 = new JMenu("look");
        mi11 = new JMenuItem("copy");
        mi12 = new JMenuItem("paste");
        mi13 = new JMenuItem("close");
        mi21 = new JMenuItem("standerd");
        mi22 = new JMenuItem("scientific");
        mi23 = new JMenuItem("math");

        jm1.add(mi11);
        jm1.add(mi12);
        jm1.add(mi13);
        jm2.add(mi21);
        jm2.add(mi22);
        jm2.addSeparator();   //在菜单之间增加分隔线
        jm2.add(mi23);

        mb.add(jm1);
        mb.add(jm2);
        setJMenuBar(mb);

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
//        Font f=new Font("",Font.BOLD,36);

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
            handleEqual();
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
        Double result = CalculatorUtils.calculator(field.getText());
        field.setText(result.toString());
    }

    private void handleClear() {
        field.setText("");
    }

    private void handleback() {
        if (field.getText().length() > 1) {
            field.setText(field.getText().substring(0, field.getText().length() - 1));
        }
    }

    public static void main(String[] args) {
        Test c =new Test(150,150,300,250);
    }
}