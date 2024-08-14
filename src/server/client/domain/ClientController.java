package server.client.domain;

import server.client.ui.ClientView;
import server.server.domain.ServerController;

public class ClientController {
    private boolean connected;
    private String name;
    private ClientView clientView;
    private ServerController server;

    public void setClientView(ClientView clientView) {
        this.clientView = clientView;
    }

    public void setServer(ServerController server) {
        this.server = server;
    }

    public boolean connectToServer(String name) {
        this.name = name;
        if (server != null && server.isRunning()) {  // Проверяем, работает ли сервер
            if (server.connectUser(this)) {
                showOnWindow("Вы успешно подключились!\n");
                connected = true;
                String log = server.getHistory();
                if (log != null) {
                    showOnWindow(log);
                }
                return true;
            } else {
                showOnWindow("Подключение не удалось. Сервер не работает или доступ запрещен.");
            }
        } else {
            showOnWindow("Сервер недоступен.");
        }
        return false;
    }

    public void disconnectedFromServer() {
        if (connected) {
            connected = false;
            clientView.disconnectedFromServer();
            showOnWindow("Вы были отключены от сервера!");
        }
    }

    public void disconnectFromServer() {
        if (connected) {
            server.disconnectUser(this);
        }
    }

    public void answerFromServer(String text) {
        showOnWindow(text);
    }

    public void message(String text) {
        if (connected && server.isRunning()) {  // Проверяем, работает ли сервер и клиент подключен
            if (!text.isEmpty()) {
                server.message(name + ": " + text);
            }
        } else {
            showOnWindow("Не удалось отправить сообщение. Вы не подключены к серверу.");
        }
    }

    private void showOnWindow(String text) {
        clientView.showMessage(text);
    }
}
