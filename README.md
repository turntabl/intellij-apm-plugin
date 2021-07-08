# Java Application Performance Monitoring Plugin for Intellij
![Company](https://avatars.githubusercontent.com/u/21255133?s=200&v=4)
![Company](https://avatars.githubusercontent.com/u/31739?s=200&v=4)


[comment]: <> (![Build]&#40;https://github.com/turntabl/intellij-apm-plugin/workflows/Build/badge.svg&#41;)


<!-- Plugin description -->
## Plugin Description
This Java Application Performance Monitoring Plugin for Intellij that tracks every detail of the JVM (CPU, thread, memory, garbage collection, etc) and also monitors
applications live in production environments.

This specific section is a source for the [plugin.xml](/src/main/resources/META-INF/plugin.xml) file which will be extracted by the [Gradle](/build.gradle) during the build process.

To keep everything working, do not remove 
<!-- ... --> sections.
<!-- Plugin description end -->

## Features
### Metrics
      - Flight Recoder
      - Java Application
      - Java Virtual Machine
      - Operating System
### Events
      - JVM Information
      - JFR Compilation
      - JFR Method Sample
      - Java Monitor wait
      
### Flame Graph
      - A flame graph of methods with and without their thread names.
      - CPULoad Graph



## Installation
  ### Procedure To install Plugin from Disk
[comment]: <> (  <kbd>Install Plugin</kbd>)
[comment]: <> (- Using IDE built-in plugin system:)
[comment]: <> (<kbd>File from the Toolbar or Menu Bar</kbd> > <kbd>Settings/Plugins</kbd> )

[comment]: <> (  <kbd>select from the pop up menu</kbd> > <kbd>Install Plugin from Disk</kbd>)

[comment]: <> (  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>Marketplace</kbd> > <kbd>Search for "intellij-plugin"</kbd> >)

- Manually:

  Download the [latest release](https://github.com/turntabl/intellij-apm-plugin/releases/latest) and install it manually using
 
  - Steps
      -First Go to: the Toolbar or Menu bar select<kbd>File from the Menu Bar</kbd> > <kbd>Settings/Plugins </kbd> 
      - Provide Jar file from the link above
      -Click on the settings Icon<kbd>select from the pop up menu</kbd> > <kbd>Install Plugin from Disk</kbd> 
      - You will see a Preview of the Plugin
      - Click on Apply and Okay which verifies it been applied.
  
  ## The Images of how to Install from Disk
  ![plugin](Images/plugin1.png)
  
  ![plugin](Images/plugin2.png)
  
  ![plugin](Images/plugin3.png)
  
  ![plugin](Images/plugin.png)

## Required IntelliJ Configuration
 ### Build Your Own Artifact.
 ### Run with profiler.

## How to Build Your Own Artifact

[comment]: <> (  <kbd>Building of Artifact</kbd>)
[comment]: <> (<kbd>File from the Toolbar or Menu Bar</kbd> > <kbd>Settings/Plugins</kbd> )
[comment]: <> (<kbd>Select Artifact</kbd> > <kbd>Click on the Addition/plus Sign</kbd> )
[comment]: <> (<kbd>Select the second option</kbd>)
[comment]: <> (<kbd>Select include in project Build</kbd>)
[comment]: <> (<kbd>Click on Apply/Okay</kbd>)
-Steps To Follow
  - Go to: the Toolbar or Menu bar and click on <kbd>File from the Toolbar or Menu Bar</kbd> > <kbd>Project Structure </kbd>
  - Select Artifact from the Menu display <kbd>Select Artifact</kbd> > <kbd>Click on the Addition/plus Sign</kbd> 
  - Select JAR from the pop up menu and  <kbd>Select the second option</kbd> > "from modules with dependencies"
  - Choose the main class and click on Ok <kbd>Select include in project Build</kbd> > <kbd>Click on Apply/Okay</kbd>
  - Go to: <kbd>the Menu Bar and select build Artifact</kbd>
  - Build the project artifact first. The project's jar will be generated
  - The jar generated will be located in this directory (`out > artifacts`)

 ## Screenshot of how to build the artifact
![plugin](Images/artifact1.png)

![plugin](Images/artifact2.png)

![plugin](Images/artifact3.png)
 
## follow step 5 through to step 7 above
 
## Notice
 -Ensure that the name of your jar and its directory or folder have the same name as the name of your project

## To Run with profiler

 ## Notice
  - Ensure to set your main class
   - Do not tamper with the Vm options and Environment variables.
   - Before you run your application with profiler, open the tool window at the status bar to activate our web server.

## Steps to follow
  - Run the project default main first before you run with the profiler.
  - To Run with profiler: Goto <kbd>Run</kbd> > <kbd>Run with NewRelic Profiler</kbd>
  - Caution: Ensure you stop NewRelic profiler from running when you are done profiling your application
 
  ## Images of How to Run your application with Profiler
![plugin](Images/result1.png)

![plugin](Images/result2.png)

![plugin](Images/result3.png)

![plugin](Images/result4.png)


  
## Usage 
  

[comment]: <> (  - Goto <kbd>Run</kbd> > <kbd>Edit Configurations</kbd>)

[comment]: <> (  - In the pop up window, click on create new application)

[comment]: <> (  - Choose a name, Run on > Local Machine)

[comment]: <> (  - For the VM Options, enter this command  `-javaagent:./lib/jfr-daemon-1.2.0-SNAPSHOT.jar -jar ./lib/testProject.jar`)
  
[comment]: <> (### Working with different types of projects  )

[comment]: <> (- Pure Java Project)

[comment]: <> (    - Build project first)

[comment]: <> (    - Run the project default main first before you run with the plugin.)

[comment]: <> (- Gradle Projects)

[comment]: <> (  - Build project first)

[comment]: <> (  - Run the project default main first before you run with the plugin.)

[comment]: <> (  - For cases where a null pointer exception is thrown, kindly run the project default main again before running with profiler.)

[comment]: <> (- Maven Projects)

[comment]: <> (  - Build project first)

[comment]: <> (  - Run the project default main first before you run with the plugin.)

[comment]: <> (  - For cases where a null pointer exception is thrown, kindly run the project default main again before running with profiler.)

## Tools Used
-Libraries Used
  -D3 Library click on the link to read more about it[d3-flame-graph](https://github.com/spiermar/d3-flame-graph)
  - Burn Library click on the provided link for more details about it [burn-library](https://github.com/spiermar/burn)
    -The Burn Library was in GoLang but was converted to Java for the purpose of this plugin.
  -Jfree Chat library is use for one the Metrics Graph in this Plugin[Jfree-chat](https://www.jfree.org/jfreechart/)
