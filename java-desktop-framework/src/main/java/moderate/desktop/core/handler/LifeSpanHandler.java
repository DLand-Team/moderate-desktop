package moderate.desktop.core.handler;

import moderate.desktop.rx.SharedService;
import moderate.desktop.rx.SharedType;
import org.cef.browser.CefBrowser;
import org.cef.browser.CefFrame;
import org.cef.handler.CefLifeSpanHandlerAdapter;

public class LifeSpanHandler extends CefLifeSpanHandlerAdapter {

    private final SharedService sharedService = SharedService.getInstance();

    @Override
    public boolean onBeforePopup(CefBrowser browser, CefFrame frame, String targetUrl, String targetFrameName) {
        // 打开新窗口
        sharedService.pub(SharedType.OPEN_NEW_PAGE, targetUrl);
        // 返回true表示取消弹出窗口
        return true;
    }

}
