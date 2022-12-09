import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        Frame frame = new Frame("遥控器");
        frame.setLocation(100,100);
        frame.setSize(500,300);

        Panel p = new Panel();
        p.setLayout(new GridLayout(5,3,4,4));

        p.add(new Button());
        p.add(new AdbKeyButton(KEY.KEY_UP));
        p.add(new Button());
        p.add(new AdbKeyButton(KEY.KEY_LEFT));
        p.add(new AdbKeyButton(KEY.KEY_ENTER));
        p.add(new AdbKeyButton(KEY.KEY_RIGHT));
        p.add(new Button());
        p.add(new AdbKeyButton(KEY.KEY_DOWN));
        p.add(new Button());
        p.add(new AdbKeyButton(KEY.KEY_BACK));
        p.add(new Button());
        p.add(new AdbKeyButton(KEY.KEY_HOME));
        p.add(new AdbKeyButton(KEY.KEY_VOLUME_UP));
        p.add(new AdbKeyButton(KEY.KEY_VOLUME_MUTE));
        p.add(new AdbKeyButton(KEY.KEY_VOLUME_DOWN));



        frame.add(p);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //停止当前程序
                System.exit(0);
            }
        });
//        frame.pack();
        frame.setVisible(true);
    }
}

enum KEY{
    KEY_HOME,
    KEY_BACK,
    KEY_UP,
    KEY_DOWN,
    KEY_LEFT,
    KEY_RIGHT,
    KEY_ENTER,
    KEY_VOLUME_UP,
    KEY_VOLUME_DOWN,
    KEY_VOLUME_MUTE
}
class AdbKeyButton extends Button{
    public AdbKeyButton(KEY KEY) throws HeadlessException {
        super(KEY.toString());
        setAdbCommand(KEY);
    }

    public Component setAdbCommand(KEY KEY){
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Process process = Runtime.getRuntime().exec("cmd /c adb shell input keyevent " + KEYToString(KEY));
                    Thread.sleep(100);
                    process.destroy();
                } catch (IOException | InterruptedException exception) {
                    // TODO Auto-generated catch block
                    exception.printStackTrace();
                }
            }
        });

        return this;
    }
    private int KEYToString(KEY KEY){
        switch (KEY){
            case KEY_HOME:
                return 3;
            case KEY_BACK:
                return 4;
            case KEY_UP:
                return 19;
            case KEY_DOWN:
                return 20;
            case KEY_LEFT:
                return 21;
            case KEY_RIGHT:
                return 22;
            case KEY_ENTER:
                return 66;
            case KEY_VOLUME_UP:
                return 24;
            case KEY_VOLUME_DOWN:
                return 25;
            case KEY_VOLUME_MUTE:
                return 164;
        }
        return 0;
    }
}