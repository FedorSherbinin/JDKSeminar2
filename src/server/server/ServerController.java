package server.server;

import server.client.ClientController;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс ServerController управляет логикой работы сервера, подключением клиентов и обменом сообщениями.
 */
public class ServerController {
    private List<ClientController> clients = new ArrayList<>();  // Список подключенных клиентов
    private StringBuilder chatHistory = new StringBuilder();  // Хранит историю сообщений чата
    private ServerWindow serverView;  // GUI сервера
    private boolean isRunning;  // Флаг работы сервера

    /**
     * Устанавливает состояние сервера (работает или нет).
     * @param running true, если сервер работает, иначе false.
     */
    public void setRunning(boolean running) {
        this.isRunning = running;
    }

    /**
     * Проверяет, работает ли сервер.
     * @return true, если сервер работает, иначе false.
     */
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * Подключение клиента к серверу.
     * @param client Клиент, который подключается.
     * @return true, если подключение успешно, иначе false.
     */
    public boolean connectUser(ClientController client) {
        if (isRunning) { // Проверяем, работает ли сервер
            if (!clients.contains(client)) { // Проверяем, не подключен ли уже клиент
                clients.add(client);
                return true;
            }
        }
        return false; // Возвращаем false, если сервер не работает или клиент уже подключен
    }

    /**
     * Отключение клиента от сервера.
     * @param client Клиент, который отключается.
     */
    public void disconnectUser(ClientController client) {
        clients.remove(client);  // Удаляем клиента из списка
        client.disconnectedFromServer();  // Уведомляем клиента об отключении
        if (serverView != null) {
            serverView.disconnectUser(client);  // Обновляем GUI сервера
        }
    }

    /**
     * Возвращает историю сообщений чата.
     * @return История сообщений в виде строки.
     */
    public String getHistory() {
        return chatHistory.toString();
    }

    /**
     * Метод для обработки сообщений от клиентов и рассылки их всем подключенным клиентам.
     * @param message Текст сообщения.
     */
    public void message(String message) {
        if (!isRunning) { // Проверяем, работает ли сервер
            return;
        }

        chatHistory.append(message).append("\n"); // Добавляем сообщение в историю чата

        for (ClientController client : clients) {
            client.answerFromServer(message);  // Отправляем сообщение всем подключенным клиентам
        }

        if (serverView != null) {
            serverView.message(message);  // Обновляем GUI сервера
        }
    }

    /**
     * Устанавливает GUI сервера.
     * @param serverView GUI сервера.
     */
    public void setServerView(ServerWindow serverView) {
        this.serverView = serverView;
    }

    /**
     * Загружает историю чата из строки.
     * @param history История чата в виде строки.
     */
    public void loadChatHistory(String history) {
        chatHistory = new StringBuilder(history);  // Инициализируем историю чата
    }
}
