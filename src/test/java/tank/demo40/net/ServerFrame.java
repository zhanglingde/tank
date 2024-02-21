package tank.demo40.net;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ServerFrame extends Frame {

    public static ServerFrame INSTANCE = new ServerFrame();

    Server server = new Server();

    Button btnStart = new Button("start");
    // 服务器消息
    TextArea taLeft = new TextArea();
    // 客户端消息
    TextArea taRight = new TextArea();

    public ServerFrame() {
        this.setSize(1600, 600);
        this.setLocation(300, 200);
        this.add(btnStart, BorderLayout.NORTH);
        Panel p = new Panel(new GridLayout(1, 2));
        p.add(taLeft);
        p.add(taRight);
        this.add(p);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        //this.btnStart.addActionListener((e) -> {
        //    server.serverStart();
        //});
        //this.setVisible(true);
    }

    public static void main(String[] args){

        ServerFrame.INSTANCE.setVisible(true);
        ServerFrame.INSTANCE.server.serverStart();
    }

    public void updateServerMsg(String s) {
        this.taLeft.setText(taLeft.getText() + System.getProperty("line.separator") + s);
    }

    public void updateClientMsg(String s) {
        this.taRight.setText(taRight.getText() + System.getProperty("line.separator") + s);
    }
}