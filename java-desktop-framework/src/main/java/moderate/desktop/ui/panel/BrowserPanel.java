package moderate.desktop.ui.panel;

import moderate.desktop.core.ChromiumEmbeddedCefBrowserInst;
import moderate.desktop.rx.SharedService;
import moderate.desktop.rx.SharedType;
import org.cef.browser.CefBrowser;

import javax.swing.*;
import java.awt.*;

public class BrowserPanel extends JPanel {

    private final ChromiumEmbeddedCefBrowserInst browserInst = ChromiumEmbeddedCefBrowserInst.getInstance();
    private final SharedService sharedService = SharedService.getInstance();

    private final CefBrowser browser;
    private final JSplitPane splitPane;
    private final JPanel framePanel;
    private final JPanel devToolPanel;

    private boolean devToolOpen = false;

    public BrowserPanel(String url) {

        this.setLayout(new BorderLayout());
        this.setFocusable(true);

        this.splitPane = new JSplitPane();
        this.splitPane.setBorder(null);
        this.splitPane.setDividerSize(0);
        this.splitPane.setOneTouchExpandable(false);
        this.splitPane.setContinuousLayout(false);
        this.splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        this.add(this.splitPane, BorderLayout.CENTER);

        // 浏览器界面
        this.browser = browserInst.create(url);
        this.framePanel = new JPanel();
        this.framePanel.setLayout(new BorderLayout());
        this.framePanel.add(this.browser.getUIComponent(), BorderLayout.CENTER);
        this.splitPane.setLeftComponent(this.framePanel);
        this.framePanel.setBorder(null);

        // 开发者工具
        this.devToolPanel = new JPanel();
        this.devToolPanel.setLayout(new BorderLayout());
        this.splitPane.setRightComponent(null);

        this.subShared();
        // this.openDevTools();

    }

    private void subShared() {
        this.sharedService.sub(SharedType.OPEN_DEV_TOOL).subscribe((res) -> {
            if (this.browser.getIdentifier() == (int) res) {
                this.toggleDevTools();
            }
        });
    }

    private void toggleDevTools() {
        if (this.devToolOpen) {
            this.closeDevTools();
            this.devToolOpen = false;
        } else {
            this.openDevTools();
            this.devToolOpen = true;
        }
    }

    private void openDevTools() {
        devToolPanel.add(this.browser.getDevTools().getUIComponent(), BorderLayout.CENTER);
        splitPane.setRightComponent(devToolPanel);
        splitPane.setContinuousLayout(true);
        splitPane.setDividerLocation(this.getSize().width - 500);
    }

    private void closeDevTools() {
        devToolPanel.removeAll();
        splitPane.remove(devToolPanel);
        splitPane.setRightComponent(null);
        splitPane.setContinuousLayout(false);
        splitPane.setDividerLocation(this.getSize().width);
    }

}
