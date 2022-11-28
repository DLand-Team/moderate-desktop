package moderate.desktop.ui;

import moderate.desktop.common.StaticVar;
import lombok.Getter;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serial;
import java.util.Objects;
import javax.swing.border.TitledBorder;

public class StartupWindow extends JWindow {

    @Serial
    private static final long serialVersionUID = 2631323817836401702L;

    private static volatile StartupWindow instance;
    @Getter
    private JProgressBar progressBar;
    @Getter
    private JLabel progressLabel;
    @Getter
    private JLabel beanNameLabel;

    private Color borderColor = new Color(112, 112, 112);

    public StartupWindow() {

        this.getContentPane().setLayout(null);
        ImageIcon backgroundIconImage = new ImageIcon(
                Objects.requireNonNull(StartupWindow.class.getResource("/moderate/desktop/assets/img/loading-background.png")));

        JPanel backgroundPanel = new JPanel();
        backgroundPanel
                .setBorder(new TitledBorder(null, "Moderate Desktop", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        backgroundPanel.setBounds(0, 0, 400, 225);
        backgroundPanel.setLayout(null);
        getContentPane().add(backgroundPanel);

        progressLabel = new JLabel("正在加载");
        progressLabel.setForeground(Color.BLACK);
        progressLabel.setBounds(10, 200, 380, 15);
        progressLabel.setFont(StaticVar.FONT_SourceHanSansCNNormal_13);
        backgroundPanel.add(progressLabel);

        JLabel backgroundLabel = new JLabel("");
        backgroundLabel.setIcon(backgroundIconImage);
        backgroundLabel.setBounds(10, 17, 380, 198);
        backgroundIconImage.setImage(backgroundIconImage.getImage().getScaledInstance(380, 198, Image.SCALE_SMOOTH));
        backgroundPanel.add(backgroundLabel);

        JPanel beanNamePanel = new JPanel();
        beanNamePanel.setBorder(new LineBorder(borderColor));
        beanNamePanel.setBounds(0, 226, 400, 26);
        beanNamePanel.setLayout(null);
        getContentPane().add(beanNamePanel);

        beanNameLabel = new JLabel("org.springframework.boot");
        beanNameLabel.setBounds(10, 0, 380, 25);
        beanNameLabel.setFont(StaticVar.FONT_SourceHanSansCNNormal_13);
        beanNamePanel.add(beanNameLabel);

        JPanel progressBarPanel = new JPanel();
        progressBarPanel.setBorder(new LineBorder(borderColor));
        progressBarPanel.setBounds(0, 252, 400, 15);
        getContentPane().add(progressBarPanel);
        progressBarPanel.setLayout(new BorderLayout());

        progressBar = new JProgressBar();
        progressBarPanel.add(progressBar, BorderLayout.CENTER);

        this.setSize(400, 268);
        this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - this.getWidth()) / 2,
                (Toolkit.getDefaultToolkit().getScreenSize().height - this.getHeight()) / 2);
        this.setVisible(true);
        this.setAlwaysOnTop(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                instance = null;
            }
        });

    }

    public void close() {
        this.dispose();
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    public static StartupWindow getIstance() {
        if (instance == null) {
            synchronized (StartupWindow.class) {
                if (instance == null) {
                    instance = new StartupWindow();
                }
            }
        }
        return instance;
    }
}
