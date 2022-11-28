package moderate.desktop.core;

import com.jetbrains.cef.JCefAppConfig;
import moderate.desktop.core.handler.LifeSpanHandler;
import moderate.desktop.core.handler.MenuHandler;
import org.cef.CefApp;
import org.cef.CefClient;
import org.cef.CefSettings;
import org.cef.browser.CefBrowser;
import org.cef.browser.CefRendering;

import java.awt.*;

public class ChromiumEmbeddedCefBrowserInst {

    private static volatile ChromiumEmbeddedCefBrowserInst instance;

    private CefApp cefApp = null;
    private CefClient cefClient = null;
    private CefBrowser browser = null;

    public ChromiumEmbeddedCefBrowserInst() {
        String[] args = JCefAppConfig.getInstance().getAppArgs();
        CefSettings settings = JCefAppConfig.getInstance().getCefSettings();
        settings.cache_path = System.getProperty("user.dir") + "/context/jcef/data";
        // 获取CefApp实例
        CefApp.startup(args);
        cefApp = CefApp.getInstance(args, settings);
        cefClient = cefApp.createClient();
        cefClient.addLifeSpanHandler(new LifeSpanHandler());
        cefClient.addContextMenuHandler(new MenuHandler());
    }

    public CefBrowser create(String url) {
        browser = cefClient.createBrowser(url, CefRendering.DEFAULT, true);
        return browser;
    }

    public CefBrowser getBrowser() {
        return this.browser;
    }

    public Component getBrowserContainer() {
        return this.browser.getUIComponent();
    }

    public Object getDevTools() {
        return this.browser.getDevTools();
    }

    public Component getDevToolsContainer() {
        return this.browser.getDevTools().getUIComponent();
    }

    public String getVersion() {
        return "Chromium Embedded Framework (CEF), " + "ChromeVersion: " + cefApp.getVersion().getChromeVersion();
    }

    public void reload() {
        this.browser.reload();
    }

    public void loadURL(String url) {
        this.browser.loadURL(url);
    }

    public void dispose(int a) {
        if (a == 0) {
            cefApp.dispose();
        } else {
            cefClient.dispose();
        }
    }

    public static ChromiumEmbeddedCefBrowserInst getInstance() {
        if (instance == null) {
            synchronized (ChromiumEmbeddedCefBrowserInst.class) {
                if (instance == null) {
                    instance = new ChromiumEmbeddedCefBrowserInst();
                }
            }
        }
        return instance;
    }

}
