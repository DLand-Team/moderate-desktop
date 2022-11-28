package moderate.desktop.ui;

import jakarta.annotation.PostConstruct;
import moderate.desktop.common.StaticVar;
import moderate.desktop.core.ChromiumEmbeddedCefBrowserInst;
import moderate.desktop.rx.SharedService;
import moderate.desktop.ui.panel.BrowserPanel;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@Component
public class MainFrame extends JFrame {

    private final ChromiumEmbeddedCefBrowserInst browserInst = ChromiumEmbeddedCefBrowserInst.getInstance();
    private final SharedService sharedService = SharedService.getInstance();

    @PostConstruct
    private void init() {

        /* 布局 */
        this.getContentPane().setLayout(new GridLayout(1, 2, 0, 0));

//        BrowserPanel leftBrowser = new BrowserPanel("https://intelyes.club/");
//        JPanel leftPanel = new JPanel();
//        leftPanel.setBorder(new TitledBorder(null, "左边", TitledBorder.LEADING, TitledBorder.TOP,
//                StaticVar.FONT_SourceHanSansCNNormal_12, null));
//        leftPanel.setLayout(new BorderLayout());
//        leftPanel.add(leftBrowser, BorderLayout.CENTER);
//        this.add(leftPanel);
//
//        BrowserPanel rightBrowser = new BrowserPanel("http://moderate.run/");
//        JPanel rightPanel = new JPanel();
//        rightPanel.setBorder(new TitledBorder(null, "右边", TitledBorder.LEADING, TitledBorder.TOP,
//                StaticVar.FONT_SourceHanSansCNNormal_12, null));
//        rightPanel.setLayout(new BorderLayout());
//        rightPanel.add(rightBrowser, BorderLayout.CENTER);
//        this.add(rightPanel);

        this.add( new BrowserPanel("https://angular.cn/"));
        this.add(new BrowserPanel("https://cn.vuejs.org/"));

        this.setTitle("Moderate Desktop");
        this.setSize(new Dimension(1280, 720));
        this.setMinimumSize(new Dimension(1150, 650));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - this.getWidth()) / 2,
                (Toolkit.getDefaultToolkit().getScreenSize().height - this.getHeight()) / 2);
        this.setVisible(false);
        this.setResizable(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                processExit();
            }
        });

    }

    private void processExit() {
        this.setVisible(false);
        this.browserInst.dispose(0);
        System.exit(0);
    }

}
