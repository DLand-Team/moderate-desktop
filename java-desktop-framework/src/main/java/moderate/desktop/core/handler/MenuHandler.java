package moderate.desktop.core.handler;

import moderate.desktop.rx.SharedService;
import moderate.desktop.rx.SharedType;
import org.cef.browser.CefBrowser;
import org.cef.browser.CefFrame;
import org.cef.callback.CefContextMenuParams;
import org.cef.callback.CefMenuModel;
import org.cef.callback.CefMenuModel.MenuId;
import org.cef.handler.CefContextMenuHandlerAdapter;

public class MenuHandler extends CefContextMenuHandlerAdapter {

    private final SharedService sharedService = SharedService.getInstance();
    private final static int MENU_ID_DEV_TOOL = 1000001;

    @Override
    public void onBeforeContextMenu(CefBrowser browser, CefFrame frame, CefContextMenuParams params, CefMenuModel model) {
        //清除菜单项
        model.clear();
        //剪切、复制、粘贴
        model.addItem(MenuId.MENU_ID_COPY, "复制");
        model.addItem(MenuId.MENU_ID_CUT, "剪切");
        model.addItem(MenuId.MENU_ID_PASTE, "粘贴");
        model.addSeparator(); // 分割线
        model.addItem(MenuId.MENU_ID_BACK, "返回");
        model.setEnabled(MenuId.MENU_ID_BACK, browser.canGoBack());
        model.addItem(MenuId.MENU_ID_FORWARD, "前进");
        model.setEnabled(MenuId.MENU_ID_FORWARD, browser.canGoForward());
        model.addItem(MenuId.MENU_ID_RELOAD, "刷新");
        model.addSeparator(); // 分割线
        model.addItem(MenuId.MENU_ID_VIEW_SOURCE, "查看源码");
        model.addItem(MENU_ID_DEV_TOOL, "开发者工具");
        //创建子菜单
//        CefMenuModel cmodel = model.addSubMenu(MENU_ID_INJECTION, "脚本注入");
//        cmodel.addItem(MENU_ID_ADDTEXT, "添加一段文本");
    }

    /*
     * @see org.cef.handler.CefContextMenuHandler#onContextMenuCommand(org.cef.browser.CefBrowser, org.cef.browser.CefFrame, org.cef.callback.CefContextMenuParams, int, int)
     */
    @Override
    public boolean onContextMenuCommand(CefBrowser browser, CefFrame frame, CefContextMenuParams params, int commandId, int eventFlags) {
        switch (commandId) {
            case MenuId.MENU_ID_RELOAD -> {
                browser.reload();
                return true;
            }
            case MENU_ID_DEV_TOOL -> {
                sharedService.pub(SharedType.OPEN_DEV_TOOL, browser.getIdentifier());
                return true;
            }
        }
        return false;
    }

}
