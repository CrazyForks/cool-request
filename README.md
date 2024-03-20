<div align="center">
  <h1 align="center">
    Cool Request（v2023.3.15）
    <br />
    <br />
    <a href="https://plugin.houxinlin.com">
      <img src="https://plugin.houxinlin.com/img/logo.svg" alt="">
    </a>
  </h1>
</div>

This plugin is used for debugging HTTP interfaces and schedulers in IntelliJ IDEA.

![image](https://github.com/houxinlin/cool-request/assets/38684327/17a6db6a-4ea5-4637-91fb-c7acad51ad88)




[中文](README.zh.md)





[Documentation](https://plugin.houxinlin.com)

[Documentation  Github](https://houxinlin.github.io/)   If the official website cannot be accessed, please try GitHub hosting.
# Screenshots

![image](https://github.com/houxinlin/cool-request/assets/38684327/b30c7c64-249e-476b-8ddc-f51bb33b0c13)

![img.png](doc/screen.png)

![img.png](doc/script.png)

![img.png](doc/setting.png)
# Usage Steps:
1. Go to Setting->Plugins, search for Cool Request, and click install.

![img.png](doc/install.png)

## Features
- ✓️ Collect and display all Controller information defined in Spring Boot, supporting HTTP/reflection calls.
- ✓ Collect and display timers defined in Spring Boot; you can manually trigger them without waiting for a specified time.
- ✓ Optionally bypass interceptors during the request.
- ✓ Optionally specify proxy/original objects during the request.
- ✓ One-click export to OpenAPI format.
- ✓ One-click import to Apifox.
- ✓ Copy requests as curl commands.
- ✓ Compatible with Gradle, Maven multi-module projects.
- ✓ Compatible with Java/Kotlin languages.
- ✓ Powerful HTTP request parameter guessing feature to reduce the time developers spend on key entry.
- ✓ Write scripts for pre/post request using Java syntax.
- ✓ Save response results to a file.
- ✓ Quickly preview JSON, XML, images, HTML, and text responses.
- ✓ Multiple layout switching options.
- ✓ Automatically discover Spring Gateway paths.
- ✓ Multi-environment configuration.
-

## Update history

 1. 2024.3.1

 2. 2024.2.20

## Issues

1. What are proxy objects and original objects?

   Answer: This plugin does not call the Controller through HTTP requests but uses reflection internally. Therefore, when obtaining an object, the object may be CGLIB proxied, but you can choose the original object. However, some AOP may be disabled in this process.

2. What are interceptors?

   If your project has an interceptor that matches the Controller, when selecting to apply the interceptor, it will be called first if it matches the Controller. If no interceptor is selected, even if the interceptor matches the Controller, it will not be called. This is one of the original intentions of this plugin, which is used to debug Controllers without authentication.

## Build Original Code

```cmd
./gradlew buildPlugin
```
```
2. Open Plugin Setting
3. Install Plugin For Disk
4. Select ./build/distributions/cool-request-plugin.zip

```

## Star History

[![Star History Chart](https://api.star-history.com/svg?repos=houxinlin/cool-request&type=Date)](https://star-history.com/#houxinlin/cool-request&Date)

