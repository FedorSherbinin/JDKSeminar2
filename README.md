# Чат-приложение

Это простое чат-приложение, написанное на Java. Приложение состоит из сервера и нескольких клиентов. Клиенты могут подключаться к серверу, отправлять сообщения и получать сообщения от других подключенных клиентов. Сервер сохраняет историю всех сообщений в файл.

## Функционал

- **Сервер:**
  - Запуск и остановка сервера.
  - Отображение сообщений от всех подключенных клиентов.
  - Показ имен подключенных клиентов.
  - Сохранение истории чата в файл.
  
- **Клиент:**
  - Подключение к серверу.
  - Отправка и получение сообщений в режиме реального времени.
  - Отображение истории чата при подключении к серверу.

## Структура проекта

Проект организован в несколько пакетов:

- `server.client.domain`: Содержит основную логику для клиента, включая `ClientController`.
- `server.client.ui`: Содержит классы пользовательского интерфейса для клиента.
- `server.server.domain`: Содержит серверную логику, включая `ServerController`.
- `server.server.repository`: Обрабатывает сохранение и загрузку истории чата из файла.
- `server.server.ui`: Содержит классы пользовательского интерфейса для сервера.
- `server`: Точка входа в приложение.

## Как запустить

1. **Запустите сервер:**
   - Запустите класс `ServerWindow`, находящийся в `server.server.ui`.
   - Откроется окно сервера, где можно запустить сервер.

2. **Запустите клиентов:**
   - Запустите класс `ClientGUI`, находящийся в `server.client.ui`.
   - Вы можете запустить несколько клиентов, чтобы имитировать многопользовательский чат.
   - Введите имя пользователя и подключитесь к серверу.

3. **Чат:**
   - После подключения клиенты могут отправлять сообщения на сервер, который будет транслировать их всем подключенным клиентам.
   - Сервер будет отображать чат-сообщения и показывать, какие пользователи подключены.

## Хранение файлов

- Сервер сохраняет все сообщения чата в файл с именем `history.txt`, расположенный в каталоге `src/server/`.
- Когда новый клиент подключается, он получает историю чата из этого файла.

## Зависимости

- Java SE Development Kit (JDK) 8 или выше.
- Для графического интерфейса (GUI) используется библиотека Swing.

## Как настроить

- Вы можете изменить путь к файлу истории сообщений сервера в классе `ServerWindow`.
- Чтобы изменить порт сервера или IP-адрес, модифицируйте соответствующие поля в классе `ClientGUI`.

## Будущие улучшения

- Добавить аутентификацию пользователей.
- Реализовать возможность отправки приватных сообщений между клиентами.
- Улучшить графический интерфейс для лучшего пользовательского опыта.
- Улучшить обработку ошибок и добавить более детализированные логи.
