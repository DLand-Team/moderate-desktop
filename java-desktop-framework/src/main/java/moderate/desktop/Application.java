package moderate.desktop;

import moderate.desktop.ui.StartupWindow;
import lombok.Getter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;

@SpringBootApplication
public class Application {

    @Getter
    private static boolean startFinish = false;

    public static void main(String[] args) {

        // Print
        Application.print();

        try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatIntelliJLaf");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        // 加载启动界面
        SwingUtilities.invokeLater(() -> StartupWindow.getIstance().setVisible(true));
        ProgressBeanPostProcessor.observe().subscribe((result) -> {
            // 监听springboot启动进度
            SwingUtilities.invokeLater(() -> {
                StartupWindow.getIstance().getProgressLabel().setText("正在加载：" + result.getPerc() + "%");
                StartupWindow.getIstance().getBeanNameLabel().setText(result.getBeanName());
                StartupWindow.getIstance().getProgressBar().setValue(result.getPerc());
            });
        }, (e) -> {
        }, () -> {
            // springboot启动完成
            StartupWindow.getIstance().getProgressLabel().setText("加载完成：100%");
            StartupWindow.getIstance().getProgressBar().setValue(100);
            new Thread(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                startFinish = true;
            }).start();
        });

        // 异步启动springboot核心
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() {
                SpringApplication.run(Application.class, args);
                return null;
            }
        }.execute();

    }

    private static void print() {
        System.out.println("Springboot Version: " + SpringBootVersion.getVersion());
        System.out.println("User OS: " + System.getProperty("os.name"));
        System.out.println("Java Name: " + System.getProperty("java.vm.name"));
        System.out.println("Java Version: " + System.getProperty("java.vm.version"));
    }

}
