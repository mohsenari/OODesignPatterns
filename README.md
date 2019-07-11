# OODesignPatterns

Sample implementation of Object Oriented design patterns

## List of patterns to implement

* Creational
  * [ ] Builder
  * [x] [Factory Method](FactoryWithUnitTest.java)
  * [x] [Abstract Factory](AbstractfactoryWithUnitTest.java)
  * [ ] Prototype
  * [x] [Singleton](SingletonWithUnitTest.java)
* Structural
  * [ ] Adapter
  * [ ] Bridge
  * [ ] Composite
  * [x] [Decorator](DecoratorWithUnitTest.java)
  * [ ] Facade
  * [ ] Flyweight
  * [ ] Proxy
* Behavioural
  * [ ] Chain of Responsibility
  * [x] [Command](CommandWithUnitTest.java)
  * [ ] Interpreter
  * [ ] Iterator
  * [ ] Mediator
  * [ ] Memento
  * [x] [Observer](ObserverWithUnitTest.java)
  * [ ] State
  * [x] [Strategy](StrategyWithUnitTest.java)
  * [ ] Template Method
  * [ ] Visitor

## Usage

1. Clone the repository.

    ```bash
    git clone https://github.com/mohsenari/OODesignPatterns.git
    cd <path/to/cloned/directory>
    ```

2. Set CLASSPATH.

    Linux & macOS

    ```bash
    export CLASSPATH=$CLASSPATH:.:<path/to/cloned/directory>/junit/*
    ```

    Windows

    ```powershell
    SET CLASSPATH=%CLASSPATH;.;<path\to\cloned\directory>\junit\*
    ```

3. Compile any of the example files.

    ```bash
    javac StrategyWithUnitTest.java
    ```

4. Run the compiled example.

    ```bash
    java Main
    ```

5. Run unit tests.

    ```bash
    java org.junit.runner.JUnitCore StrategyWithUnitTest
    ```
