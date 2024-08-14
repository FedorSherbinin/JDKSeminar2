# Chat Server

Этот проект представляет собой простой чат-сервер на языке Java с графическим интерфейсом пользователя (GUI). Проект состоит из серверной и клиентской части, позволяя нескольким клиентам подключаться к серверу, отправлять сообщения и получать ответы.

## Функциональные возможности

- **Запуск и остановка сервера**: Сервер может быть запущен или остановлен через GUI.
- **Подключение и отключение клиентов**: Клиенты могут подключаться к серверу и отключаться от него. Когда сервер остановлен, подключение клиентов невозможно.
- **Отправка сообщений**: Клиенты могут отправлять сообщения на сервер, который рассылает их всем подключенным клиентам.
- **История чата**: История сообщений сохраняется в файл `log.txt` и загружается при запуске сервера.

## Содержание проекта

- `server/` - Директория с исходными кодами серверной части.
  - `ServerWindow.java` - Класс, отвечающий за графический интерфейс сервера.
  - `ServerController.java` - Класс, управляющий логикой работы сервера.
- `client/` - Директория с исходными кодами клиентской части.
  - `ClientWindow.java` - Класс, отвечающий за графический интерфейс клиента.
  - `ClientController.java` - Класс, управляющий логикой работы клиента.
- `log.txt` - Файл для сохранения истории чата.
# Простой чат на Java

Этот проект представляет собой простой чат, реализованный на Java, где клиенты могут подключаться к серверу и обмениваться сообщениями. Сервер поддерживает многопользовательский режим и сохраняет историю сообщений.

## Структура проекта

Проект состоит из следующих основных компонентов:

- **ServerController**: Контроллер сервера, который управляет подключением клиентов, отправкой и получением сообщений, а также хранением истории чата.
- **ClientController**: Контроллер клиента, который отвечает за взаимодействие клиента с сервером.
- **ServerWindow**: Графический интерфейс сервера.
- **ClientGUI**: Графический интерфейс клиента.
- **Repository**: Интерфейс, представляющий контракт для любого хранилища данных.
- **FileStorage**: Реализация интерфейса `Repository` для хранения данных в файле.

## Как запустить

1. Склонируйте репозиторий на свой локальный компьютер.
2. Откройте проект в вашей IDE.
3. Запустите класс `Main` для старта сервера и клиентов.

## Описание компонентов

### ServerController

`ServerController` управляет подключением клиентов и обработкой сообщений. Основные функции включают:

- **connectUser(ClientController client)**: Подключение клиента к серверу.
- **disconnectUser(ClientController client)**: Отключение клиента от сервера.
- **message(String message)**: Отправка сообщения от одного клиента всем подключенным клиентам.
- **loadHistoryFromRepository()**: Загрузка истории сообщений из хранилища.

### ClientController

`ClientController` отвечает за взаимодействие клиента с сервером. Он отправляет сообщения на сервер и получает ответы.

### Repository

`Repository` - это интерфейс для хранилища данных. В проекте реализован `FileStorage`, который сохраняет историю сообщений в файл.

### FileStorage

`FileStorage` реализует интерфейс `Repository` и отвечает за сохранение данных в файл и их загрузку. Путь к файлу задается в конструкторе.

## Примеры использования

### Запуск сервера

При запуске сервера загружается история сообщений из файла и сохраняется в оперативной памяти. Сервер готов принимать подключения от клиентов.

### Подключение клиента

Когда клиент подключается к серверу, он получает актуальную историю сообщений и может отправлять свои сообщения, которые будут видны всем подключенным клиентам.

## Планы на будущее

- Добавление поддержки базы данных для хранения истории сообщений.
- Улучшение интерфейса для удобства пользователей.
- Добавление функциональности регистрации и авторизации пользователей.