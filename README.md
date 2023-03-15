# Webots scala-controller example

**Webots** is an [open source](https://github.com/cyberbotics/webots/blob/master/LICENSE) and **multi-platform desktop application** used to **simulate robots**. Robot controllers may be programmed in [C](https://cyberbotics.com/doc/guide/using-c), [C++](https://cyberbotics.com/doc/guide/using-cpp), [Python](https://cyberbotics.com/doc/guide/using-python), [Java](https://cyberbotics.com/doc/guide/using-java), [MATLAB](https://cyberbotics.com/doc/guide/using-matlab) or [ROS](https://cyberbotics.com/doc/guide/using-ros) with a [simple API](https://cyberbotics.com/doc/reference/nodes-and-api-functions) covering all the basic robotics needs.  This project is an example showing how to setup an [sbt](https://www.scala-sbt.org) project for [Scala](https://www.scala-lang.org/) to write an example robot controller. The project uses Scala 3, but the code will compile on Scala 2 as well.

### Before you start

Before trying this, first make sure that you followed [Tutorial-1](https://cyberbotics.com/doc/guide/tutorial-1-your-first-simulation-in-webots?tab-language=java) for Java. A common hurdle is to get the environment variable `JAVA_HOME` set correctly. Although it is explained in  the chaper [Using Java](https://cyberbotics.com/doc/guide/using-java?tab-language=java) of the Cyberbotics User Guide, I used the following approach to launch the simulator on macOS:

1. Open a Terminal
2. Ensure that the environment variable `JAVA_HOME`  is set correctly. 
3. Launch Webots from the Terminal: `open -a "Webots"`

### Configure dependency

At the end of Tutorial 1, have a look on the Console to see how the Java controller is launched. It should look something like this.

```
INFO: EPuckGoForward: Starting controller: java -XstartOnFirstThread -classpath /Applications/Webots.app/Contents/lib/controller/java/Controller.jar:YOUR_WEBOTS_PROJECT_FOLDER/controllers/EPuckGoForward/ -Djava.library.path=/Applications/Webots.app/Contents/lib/controller/java EPuckGoForward
```

So, we'll need to point to `/Applications/Webots.app/Contents/lib/controller/java/Controller.jar` (on Linux/Window this will be another location) as an unmanaged library in your scala sbt project. The easiest way to do this is to create a soft link in the `lib` folder of this project. On Mac/Linux:

```
$ cd lib
$ ln -s /Applications/Webots.app/Contents/lib/controller/java/Controller.jar
```

### Fat jar

We need to get all the dependencies that Webots is not aware of, including Scala's default dependencies,  in a single JAR-file. This can be done with [sbt-assembly](https://github.com/sbt/sbt-assembly), so build this project using

```
$ sbt assembly
```

 ### Configure the Webots project

Last step is to make sure that the fat JAR-file can be found by the Webots project. For the example in Tutorial 1, go and look where the `EPuckGoForward.java` is stored, e.g.: `YOUR_WEBOTS_PROJECT_FOLDER/controllers/EPuckGoForward/`

First remove the  `EPuckGoForward.class` file in that folder (if present) to make sure this is not used i.s.o. the JAR-file that you built with sbt. Webots expects that the jar file is located here and is named exactly as the name you gave it in the webots project. So, let's create here another soft link to our JAR file.  On Mac/Linux:

```
$ cd YOUR_WEBOTS_PROJECT_FOLDER/controllers/EPuckGoForward/
$ ln -s YOUR_SCALA_PROJECT/target/scala-3.2.1/scala-controller-assembly-0.0.1.jar EPuckGoForward.jar
```

 That's it! Reset and start the simulation and it should run your Scala code.

