package moderate.desktop;

import moderate.desktop.ui.MainFrame;
import moderate.desktop.ui.StartupWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
@Order(1)
public class MainInit implements ApplicationRunner {

    @Autowired
    private MainFrame mainFrame;

    @Override
    public void run(ApplicationArguments args) {
        SwingUtilities.invokeLater(() -> {
            mainFrame.setVisible(true);
            StartupWindow.getIstance().close();
        });
    }

}
