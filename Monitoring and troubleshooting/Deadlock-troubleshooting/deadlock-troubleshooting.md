# Deadlock troubleshooting

## I. Get deadlock

- Execute java application that simulates deadlock:
  ```
    java -jar deadlock-1.0.0-SNAPSHOT.jar
  ```
- Get `thread dump` using `jcmd`and `jstack`:

    ```
        jcmd <pid> Thread.print
    ```
    ```
        jstack -l <pid>
    ```
   ![Image](img/jstack-deadlock.png)


- Get `thread dump` using `jvisualvm`:

   ![Image](img/deadlock.png)
