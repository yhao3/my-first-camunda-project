# Chapter1: Getting Started
## ⬇️download initiallizer for Spring boot application

1. head to: [`start.camunda.com`](http://start.camunda.com)
    
    [Camunda Platform Initializr](https://start.camunda.com/)
    
2. generate project
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/ebfc3e02-e358-45c6-bddf-a25839da5611/Untitled.png)
    
3. unzip my-first-camunda-project.zip to workspace
    
    ```
    📁src
    📁target
    pom.xml
    ```
    
4. import project to Eclipse
    
    `Import...` > `Maven`/`Existing Maven Projects` > `Browes...` > Open: `my-first-camunda-project` > `Finish`
    

## files

```
📁src
   └-- 📁main
          └-- 📁java/com/example/workflow
          |      └-- Application.java
          └-- 📁resources
                 └-- application.yaml: setting for the engine, e.g username and password
                 └-- process.bpmn: simple BPMN process

📁target
📄[pom.xml](https://www.notion.so/Build-an-Application-with-Spring-Boot-05f7e773a9744effac8b6572193538b5)
```

- 📄pom.xml
    
    ```xml
    <project xmlns="http://maven.apache.org/POM/4.0.0"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    
      <modelVersion>4.0.0</modelVersion>
    
      <groupId>com.example.workflow</groupId>
      <artifactId>my-first-camunda-project</artifactId>
      <version>1.0.0-SNAPSHOT</version>
    
      <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>15</maven.compiler.source>
        <maven.compiler.target>15</maven.compiler.target>
      </properties>
    
      <dependencyManagement>
        <dependencies>
          <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-dependencies</artifactId>
            <version>2.6.4</version>
            <type>pom</type>
            <scope>import</scope>
          </dependency>
    
          <dependency>
            <groupId>org.camunda.bpm</groupId>
            <artifactId>camunda-bom</artifactId>
            <version>7.17.0</version>
            <scope>import</scope>
            <type>pom</type>
          </dependency>
        </dependencies>
      </dependencyManagement>
    
      <dependencies>
      
      	<!-- the Camunda starter with the REST API -->
      	<!-- Fun Fact: We can change our project from the Community to Enterprise version by just changeing these dependencies -->
        <dependency>
          <groupId>org.camunda.bpm.springboot</groupId>
          <artifactId>camunda-bpm-spring-boot-starter-rest</artifactId>
        </dependency>
    
    	  <!-- the webapps -->
        <dependency>
          <groupId>org.camunda.bpm.springboot</groupId>
          <artifactId>camunda-bpm-spring-boot-starter-webapp</artifactId>
        </dependency>
    
        <dependency>
          <groupId>org.camunda.bpm</groupId>
          <artifactId>camunda-engine-plugin-spin</artifactId>
        </dependency>
    
        <dependency>
          <groupId>org.camunda.spin</groupId>
          <artifactId>camunda-spin-dataformat-all</artifactId>
        </dependency>
    
        <dependency>
          <groupId>com.h2database</groupId>
          <artifactId>h2</artifactId>
        </dependency>
    
        <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-jdbc</artifactId>
        </dependency>
    
      </dependencies>
    
      <build>
        <plugins>
          <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <version>2.6.4</version>
          </plugin>
        </plugins>
      </build>
    
    </project>
    ```
    

## [Optional] Connect with GitHub

```xml
cd ~/yourworkspace/my-first-camunda-project
git init
git remote add origin https://github.com/yourname/my-first-camunda-project.git
git branch -M main
git add .
git commit -m 'first commit'
git push -u origin main
```

## open up process.bpmn this process model in our Spring Boot application

1. **Start Camunda Modeler**
    
    Run Camunda Modeler.exe (Windows), Camunda Modeler.app (Mac) or camunda-modeler (Linux).
    
2. drag process.bpmn and drop it into the **Modeler**

### design our process model

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/688bcd6d-03b1-440e-90a2-1c3c0d3f19e1/Untitled.png)

<aside>
📝 Note:

- 🔧Change type
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/4f82635c-2649-4a84-af55-00aa89512cb0/Untitled.png)
    
    - type:
        - **Send Task**
        - **...**
        - **User Task:** 
        indicates the engine that we need to wait here for users to give us some feedback before continuing, it also maens we’re gonna attach forms to them which we’ll do shortly.
        - **...**
</aside>

### create Camunda Forms for every User Task

1. `File` > `new File` > `Form (Camunda Platform 7)`
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/ff6b31aa-972c-4fd0-8b6f-3622204441b1/Untitled.png)
    
2. create from
    - prepareForDeparture.form
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/120399c7-c39a-4766-835b-59ef705437a1/Untitled.png)
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/505dc0de-8af2-4fdf-a1bd-0f16a79ad7f8/Untitled.png)
        
    - getTaxi.form
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/1087f50b-9e9c-47e1-b7be-f92242525647/Untitled.png)
        
3. save the file into Spring Boot project
    
    ```
    # save the file in:  
    ./src/main/resources/static/forms/xxx.form
    ```
    

So now, we have a model with some User Tasks and some forms. 

Next Chapter, we’re gonna need to connect those front-end files we’ve built to the user tasks themselves, and then we can get started actually running the first iteration of our process.