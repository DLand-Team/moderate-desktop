package moderate.desktop.callback;

import moderate.desktop.core.ChromiumEmbeddedCefBrowserInst;

public interface BrowserInitCallback {

    void execute(ChromiumEmbeddedCefBrowserInst browser);

}
