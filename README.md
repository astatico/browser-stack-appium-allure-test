## browser-stack-appium-allure-test-task
### Stack
* jdk17
* lombok plugin
* appium
* JUnit5
* BrowserStack
* Allure-report

If you are going to run test framework, you should:
* upload Wikipedia.apk to BrowserStack cloud and get app id
* paste it to app capability in DriverManager.class
* copy your username and access key from BrowserStack and paste them to browserstack.yml like 2 next params: userName and accessKey

For run test framework you should use
> gradle clean test

To collect report run
> allureServe