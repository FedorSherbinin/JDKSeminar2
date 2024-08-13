package server.server;

import server.client.ClientController;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для управления подключениями и взаимодействием с клиентами
 */
public class ServerController {
    // Пример хранилища для клиентов и истории сообщений
    private List<ClientController> clients = new ArrayList<>();
    private StringBuilder chatHistory = new StringBuilder();
    private ServerView serverView;
    private boolean isRunning;

    // Устанавливает состояние сервера (работает/не работает)
    public void setRunning(boolean running) {
        this.isRunning = running;
    }

    // Проверяет, работает ли сервер
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * Метод для установки ServerView
     * @param serverView объект ServerView для отображения сообщений на серверном GUI
     */
    public void setServerView(ServerView serverView) {
        this.serverView = serverView;
    }

    /**
     * Метод для подключения пользователя
     * @param client клиент, который пытается подключиться
     * @return true, если подключение успешно, иначе false
     */
    public boolean connectUser(ClientController client) {
        // Проверка, не подключен ли уже клиент
        if (!clients.contains(client)) {
            clients.add(client);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Метод для отключения пользователя
     * @param client клиент, который хочет отключиться
     */
    public void disconnectUser(ClientController client) {
        clients.remove(client);
        client.disconnectedFromServer(); // Уведомляем клиента об отключении
    }

    /**
     * Метод для получения истории чата
     * @return строка с историей сообщений
     */
    public String getHistory() {
        return chatHistory.toString();
    }

    /**
     * Метод для передачи сообщения от клиента всем подключенным клиентам
     * @param message сообщение от клиента
     */
    public void message(String message) {
        if (!isRunning) {
            return;  // Если сервер не работает, ничего не делаем
        }

        // Сохраняем сообщение в истории
        chatHistory.append(message).append("\n");

        // Отправляем сообщение всем подключенным клиентам
        for (ClientController client : clients) {
            client.answerFromServer(message);
        }
        if (serverView != null) {
            serverView.showMessage(message);  // Отправка сообщения на GUI сервера
        }
    }

    // Загрузка истории чата при старте сервера
    public void loadChatHistory(String history) {
        chatHistory = new StringBuilder(history);
    }

}
