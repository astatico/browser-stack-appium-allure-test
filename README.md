## Test Framework for your Mobile App
#### with appium, and selenium-grid for parallel launch
### Presets
* install jdk8
* install lombok plugin
* customize lombok plugin (Build, Execution, Deployment --> Compiler --> Annotation Processors, Enable annotation processing)
* download last stable version selenium-grid https://www.selenium.dev/downloads/
* install appium

### Environment variables
* platform - ANDROID или IOS
* deviceName - имя устройства (для android указывается произвольное значение)
* udid - уникальный идентификатор устройства (команда "adb devices -l")
* appPath - путь из binary или локальный путь
* installApp - установка приложения при каждом запуске тестов (по умолчанию - true)
* featureToggles - дополнительные фичи, которые нужно включить пользователю

### Локальный запуск
Узнать UDID девайса:
*  instruments -s devices или xcrun simctl list - для iOS
*  adb devices -l - для Android

Для локального запуска необязательно запускать selenium-grid, достаточно одного appium-сервера
> appium -p 5566

Затем запустить сами тесты, пример для ios:
> ./gradlew test --project-prop platform=IOS --project-prop deviceName="iPhone 11" --project-prop udid=95316938-24B6-4C32-BB57-494FEC17B886 --project-prop appPath=http://binary/artifactory/releases/ru/alfabank/am/iOS/11.17_16734/AM_iOS_11.17_16734.ipa

Пример для android:
> ./gradlew test --project-prop platform=ANDROID --project-prop deviceName=Android --project-prop platformVersion=10.0 --project-prop udid=emulator-5554 --project-prop appPath=src/test/resources/Alfa-Mobile.apk

### Запуск с окружением как на Jenkins-агенте
> Скрипты по генерации конфига и запуску окружения будут добавлены позже

### Указание сборки приложения
Для скачивания приложения нужно указать в конфигах в Environment Variables параметр **appPath**
- Это может быть путь из binary, например *http://binary/artifactory/releases/ru/alfabank/am/iOS/11.17_16734/AM_iOS_11.17_16734.ipa*
  Тогда приложение будет предварительно скачано.
- Или локальный путь *src/test/resources/Alfa-Mobile.ipa*
  Тогда он будет преобразован из относительного в абсолютный.

Примеры приведены для .ipa файлов, для .app и .zip всё то же самое

### Создание пользователя (поле user)
* new в начале - обязателен практически для любого клиента
* all сразу после new - обязателен практически для любого клиента 

* за исключением ситуации когда вы делаете 
пользователя с 1 текущим рублевым счетом
и больше ничем - тогда у вас будет 
new one

* templates - шаблоны
* debit - дебетовая карта
* credit - кредитная карта
* activated - активировать добавленные карты
* family - семейный счет

* Не обязательные параметры, которые нужны только для читаемости:
card/cards/with/and

* new one - пользователь с 1 текущим рублевым счетом
* new all - пользователь без карт, но с разными счетами 
(семейный + счет погашения кредита + 4 текущих: 2 рублевых + долларовый + евровый)
* new all with templates
* new all with debit and credit cards 
* new all with activated debit and credit cards
* new all with activated debit card
* new all with activated credit card
* new all with templates and debit and credit cards
* new all with templates and activated debit and credit cards
* new all with family account

### Использование конфигов с хардкодными пользователями
1. Добавляете нужный вам файл или переиспользуете нужный по инструкции:
   http://confluence.moscow.alfaintra.net/pages/viewpage.action?pageId=1128253256
2. Создаете под парсинг файла конфиг по примеру ConfigUserWithOperationHistory.class
3. Реализуете функционал в вашем классе тестов, как, например, в AutopaymentCreateTest.class
4. Не забываем добавлять класс конфига в Application.class

### Требования к проекту. Проект умеет:
* Работать с разными устройствами
* Брать разные сборки
* Поддерживает PageObject
* Работает в кластере на нескольких устройствах параллельно
* Работает с диплинками
* Откидывает результаты в RP
* Поддерживает частичный запуск (только часть тестов)
* Масштабируемый
* Самопроверяющийся (понятные ошибки по предстартовой подготовке)
* Снимает снапшоты по настройке, всегда либо на падении (дефолт - на падении)

## Кодстайл
1) При написании кода, придерживайтесь существующего формата кодстайла. Для того, чтобы импортировать общий кодстайл в проект, нужно открыть:  File -> New Project Settings -> Preferences for new project -> вкладка editor/code style/java -> нажимаем на иконку шестеренки import Scheme -> Intellij IDEA code style XML. В корне проекта ищем файлик Default.xml и открываем его. Если такого файла нет, то сначала нужно его экспортировать из проекта, нажимая на шестеренку - export. И далее, уже импортировать. После этого, вы сможете применять дефолтные настройки кодстайла при написании своих классов. Для форматировани нужно нажать сочетание клавиш  cmd + option + L
2) Обращайте внимание на импорты. IDEA
 может заменять множественные импорты на *. Рекомендуется исправлять это и делать импорты раздельными, для того чтобы не загружать в тест целый класс, а только используемые методы.
3) В проект встроен checkStyle plugin, который проверяет не только кодстайл, но и различные полезные проверки по дублированию кода. Запускайте гредловые таски checkstyleMain и checkstyleTest для проверки чекстайла в main и test частях проекта соответственно. Чтобы запустить эти таски, нужно открыть вкладку gradle на левой панели IDEA, открыть модуль, в который вносились изменения (mobile-appium-am-tests или mobile-appium-framework) и открыть список other. Таски будут лежать там.
4) Добавление пустых строк между вызовами методов разных пейджей является допустимым для повышения читабельности теста.
5) Ассерты должны быть явно прописаны в самом тесте, а не вынесены в метод в пейдже.

## Pull Request
Чтобы ускорить процесс мерджа вашего PR:
1) Проверьте, соответствует ли ваш код правилам Кодстайла
2) Приложите к PR ссылку из Report Portal на прогон. Желательно прогон сделать в [Jenkins](http://ci-mob/jenkins/view/UI%20Tests/job/Autotests_AM_appium_no_bdd/)