package server.server.domain;

import server.client.domain.ClientController;
import server.server.repository.Repository;
import server.server.ui.ServerWindow;

import java.util.ArrayList;
import java.util.List;

public class ServerController {
    private List<ClientController> clients = new ArrayList<>();
    private StringBuilder chatHistory = new StringBuilder();
    private ServerWindow serverView;
    private boolean isRunning;
    private Repository repository;  // Добавляем поле для репозитория

    // Добавляем репозиторий через конструктор
    public ServerController(Repository repository) {
        this.repository = repository;
    }

    public void setRunning(boolean running) {
        this.isRunning = running;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public boolean connectUser(ClientController client) {
        if (isRunning) {
            if (!clients.contains(client)) {
                clients.add(client);
                // Логируем информацию о подключении пользователя, но не сохраняем в файл
                String connectionMessage = client.getName() + " подключился к серверу.";
                if (serverView != null) {
                    serverView.showMessage(connectionMessage);
                }
                return true;
            }
        }
        return false;
    }

    public void disconnectUser(ClientController client) {
        clients.remove(client);
        client.disconnectedFromServer();
        if (serverView != null) {
            serverView.disconnectUser(client);
        }
    }

    public String getHistory() {
        return chatHistory.toString();
    }

    public void message(String message) {
        if (!isRunning) {
            return;
        }

        chatHistory.append(message).append("\n");
        repository.save(message);  // Сохранение сообщения через репозиторий

        for (ClientController client : clients) {
            client.answerFromServer(message);
        }

        if (serverView != null) {
            serverView.message(message);
        }
    }


    public void setServerView(ServerWindow serverView) {
        this.serverView = serverView;
    }

    public void loadChatHistory(String history) {
        chatHistory = new StringBuilder(history);
    }

    public void loadHistoryFromRepository() {
        String history = repository.load();  // Загружаем историю через репозиторий
        if (history != null) {
            chatHistory.append(history);
        }
    }
}
