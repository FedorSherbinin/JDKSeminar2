package server;

import server.client.domain.ClientController;
import server.client.ui.ClientGUI;
import server.server.repository.FileStorage;
import server.server.repository.Repository;
import server.server.domain.ServerController;
import server.server.ui.ServerWindow;

public class Main {
    public static void main(String[] args) {
        // Создание хранилища и контроллера сервера
        Repository fileStorage = new FileStorage("src/server/server/repository/history.txt");
        ServerController serverController = new ServerController(fileStorage);

        // Создание объектов сервера и связывание их
        ServerWindow serverWindow = new ServerWindow();
        serverController.setServerView(serverWindow);
        serverWindow.setServerController(serverController);

        // Загрузка истории чата
        serverController.loadHistoryFromRepository();

        // Создание объектов клиента1 и связывание их
        ClientGUI clientGUI1 = new ClientGUI();
        ClientController clientController1 = new ClientController();
        clientController1.setClientView(clientGUI1);
        clientGUI1.setClient(clientController1);
        clientController1.setServer(serverController);

        // Создание объектов клиента2 и связывание их
        ClientGUI clientGUI2 = new ClientGUI();
        ClientController clientController2 = new ClientController();
        clientController2.setClientView(clientGUI2);
        clientGUI2.setClient(clientController2);
        clientController2.setServer(serverController);
    }
}
