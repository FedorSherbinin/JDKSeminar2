package server.server.ui;

import server.client.domain.ClientController;
import server.server.domain.ServerController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс ServerWindow представляет графический интерфейс (GUI) сервера.
 * Включает функции для управления подключениями клиентов и обработки сообщений.
 */
public class ServerWindow extends JFrame {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;
//    public static final String LOG_PATH = "src/server/history.txt"; // Путь к файлу для хранения истории чата

    private List<ClientController> clientControllers;  // Список всех подключенных клиентов
    private JButton btnStart, btnStop;
    private JTextArea log;
    private boolean work;  // Указывает, запущен ли сервер
    private ServerController serverController;  // Контроллер для управления сервером

    /**
     * Конструктор ServerWindow создает GUI сервера и инициализирует основные компоненты.
     */
    public ServerWindow() {
        clientControllers = new ArrayList<>();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setLocationRelativeTo(null); // Центрирует окно на экране

        createPanel();

        setVisible(true);
    }

    /**
     * Устанавливает контроллер сервера.
     * @param serverController Контроллер для управления сервером.
     */
    public void setServerController(ServerController serverController) {
        this.serverController = serverController;
    }

    /**
     * Метод подключения клиента к серверу.
     * @param clientController Клиент, который пытается подключиться.
     * @return true, если подключение успешно, иначе false.
     */
    public boolean connectUser(ClientController clientController) {
        if (!work) { // Если сервер не работает, подключение не разрешено
            return false;
        }
        clientControllers.add(clientController);  // Добавляем клиента в список подключенных клиентов
        return true;
    }

    /**
     * Получение логов сервера (истории сообщений).
     * @return История сообщений в виде строки.
     */
//    public String getLog() {
//        return readLog();
//    }

    /**
     * Отключение клиента от сервера.
     * @param clientController Клиент, который отключается.
     */
    public void disconnectUser(ClientController clientController) {
        clientControllers.remove(clientController);  // Удаляем клиента из списка
        if (clientController != null) {
            clientController.disconnectedFromServer();  // Уведомляем клиента об отключении
        }
    }

    /**
     * Метод для отправки сообщения от клиента на сервер и всем подключенным клиентам.
     * @param text Текст сообщения.
     */
    public void message(String text) {
        if (!work) { // Если сервер не работает, сообщения не обрабатываются
            return;
        }
        appendLog(text); // Добавляем сообщение в лог (на GUI)
        answerAll(text); // Рассылаем сообщение всем клиентам
//        saveInLog(text); // Сохраняем сообщение в файл
    }

    /**
     * Метод для отправки сообщения всем подключенным клиентам.
     * @param text Текст сообщения.
     */
    private void answerAll(String text) {
        for (ClientController clientController : clientControllers) {
            clientController.answerFromServer(text); // Отправляем сообщение каждому клиенту
        }
    }

//    /**
//     * Сохранение сообщения в лог-файл.
//     * @param text Текст сообщения.
//     */
//    private void saveInLog(String text) {
//        try (FileWriter writer = new FileWriter(LOG_PATH, true)) {
//            writer.write(text + "\n");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * Чтение истории сообщений из лог-файла.
//     * @return История сообщений в виде строки.
//     */
//    private String readLog() {
//        StringBuilder stringBuilder = new StringBuilder();
//        try (FileReader reader = new FileReader(LOG_PATH)) {
//            int c;
//            while ((c = reader.read()) != -1) {
//                stringBuilder.append((char) c);
//            }
//            return stringBuilder.toString();
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    /**
     * Добавление сообщения в лог на GUI.
     * @param text Текст сообщения.
     */
    private void appendLog(String text) {
        log.append(text + "\n");
    }

    /**
     * Создание панели с текстовым полем и кнопками.
     */
    private void createPanel() {
        log = new JTextArea();
        add(new JScrollPane(log)); // Добавляем скроллбар для текстового поля
        add(createButtons(), BorderLayout.SOUTH); // Добавляем панель с кнопками внизу
    }

    /**
     * Создание панели с кнопками "Start" и "Stop".
     * @return Компонент панели с кнопками.
     */
    private Component createButtons() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        btnStart = new JButton("Start");
        btnStop = new JButton("Stop");

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (work) {
                    appendLog("Сервер уже был запущен");
                } else {
                    work = true;
                    serverController.setRunning(true);

                    // Загрузка истории чата из репозитория
                    serverController.loadHistoryFromRepository();

                    appendLog("Сервер запущен!");
                }
            }
        });

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!work) {
                    appendLog("Сервер уже был остановлен");
                } else {
                    work = false;
                    serverController.setRunning(false);
                    while (!clientControllers.isEmpty()) {
                        disconnectUser(clientControllers.get(clientControllers.size() - 1));
                    }
                    appendLog("Сервер остановлен!");
                }
            }
        });

        panel.add(btnStart);
        panel.add(btnStop);
        return panel;
    }
}
