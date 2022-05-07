# Chapter2: Adding Forms and Starting Up A Spring Boot Project

## Adding a form to a user task

### Use Modeler

1. open up a sort of panel on the right side
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/c1e2a5a0-e40f-4fd1-b698-71de788283ca/Untitled.png)
    
2. select a user task
3. go to `Forms`
    
    
    | Type | select Embedded or External Task Forms !! |
    | --- | --- |
    | Form key | camunda-forms:/forms:/forms/xxx.form       (O)
    camunda-forms:/static/forms:/forms/xxx.form (X) |

### and the `process.bpmn` like this...

- `process.bpmn`
    
    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <bpmn:definitions xmlns:bpmn="http://www.omg.org/spec/BPMN/20100524/MODEL" xmlns:bpmndi="http://www.omg.org/spec/BPMN/20100524/DI" xmlns:dc="http://www.omg.org/spec/DD/20100524/DC" xmlns:di="http://www.omg.org/spec/DD/20100524/DI" xmlns:camunda="http://camunda.org/schema/1.0/bpmn" id="Definitions_0fr9mxs" targetNamespace="http://bpmn.io/schema/bpmn" exporter="Camunda Modeler" exporterVersion="5.0.0">
      <bpmn:process id="my-first-camunda-project-process" name="Hao" isExecutable="true">
        <bpmn:startEvent id="StartEvent_1" name="Hao want to travel around Taiwan">
          <bpmn:outgoing>SequenceFlow_1fp17al</bpmn:outgoing>
        </bpmn:startEvent>
        <bpmn:sequenceFlow id="SequenceFlow_1fp17al" sourceRef="StartEvent_1" targetRef="say-hello" />
        <bpmn:userTask id="say-hello" name="Prepare for Departure" camunda:formKey="camunda-forms:/forms/prepareForDeparture.form" camunda:candidateUsers="demo">
          <bpmn:incoming>SequenceFlow_1fp17al</bpmn:incoming>
          <bpmn:outgoing>Flow_0fxkaw3</bpmn:outgoing>
        </bpmn:userTask>
        <bpmn:sequenceFlow id="Flow_0fxkaw3" sourceRef="say-hello" targetRef="Activity_103k5k7" />
        <bpmn:userTask id="Activity_103k5k7" name="Get a Taxi" camunda:formKey="camunda-forms:/forms/getTaxi.form">
          <bpmn:incoming>Flow_0fxkaw3</bpmn:incoming>
          <bpmn:outgoing>Flow_0id4i0q</bpmn:outgoing>
        </bpmn:userTask>
        <bpmn:sequenceFlow id="Flow_0id4i0q" sourceRef="Activity_103k5k7" targetRef="Activity_1ymuj81" />
        <bpmn:endEvent id="Event_0wssy3s" name="Hao has return Taiwan">
          <bpmn:incoming>Flow_0rblh6f</bpmn:incoming>
        </bpmn:endEvent>
        <bpmn:sequenceFlow id="Flow_0rblh6f" sourceRef="Activity_1ymuj81" targetRef="Event_0wssy3s" />
        <bpmn:userTask id="Activity_1ymuj81" name="Have fun">
          <bpmn:incoming>Flow_0id4i0q</bpmn:incoming>
          <bpmn:outgoing>Flow_0rblh6f</bpmn:outgoing>
        </bpmn:userTask>
      </bpmn:process>
      <bpmndi:BPMNDiagram id="BPMNDiagram_1">
        <bpmndi:BPMNPlane id="BPMNPlane_1" bpmnElement="my-first-camunda-project-process">
          <bpmndi:BPMNEdge id="Flow_0rblh6f_di" bpmnElement="Flow_0rblh6f">
            <di:waypoint x="690" y="117" />
            <di:waypoint x="752" y="117" />
          </bpmndi:BPMNEdge>
          <bpmndi:BPMNEdge id="Flow_0id4i0q_di" bpmnElement="Flow_0id4i0q">
            <di:waypoint x="530" y="117" />
            <di:waypoint x="590" y="117" />
          </bpmndi:BPMNEdge>
          <bpmndi:BPMNEdge id="Flow_0fxkaw3_di" bpmnElement="Flow_0fxkaw3">
            <di:waypoint x="370" y="117" />
            <di:waypoint x="430" y="117" />
          </bpmndi:BPMNEdge>
          <bpmndi:BPMNEdge id="SequenceFlow_1fp17al_di" bpmnElement="SequenceFlow_1fp17al">
            <di:waypoint x="215" y="117" />
            <di:waypoint x="270" y="117" />
          </bpmndi:BPMNEdge>
          <bpmndi:BPMNShape id="_BPMNShape_StartEvent_2" bpmnElement="StartEvent_1">
            <dc:Bounds x="179" y="99" width="36" height="36" />
            <bpmndi:BPMNLabel>
              <dc:Bounds x="165" y="142" width="65" height="40" />
            </bpmndi:BPMNLabel>
          </bpmndi:BPMNShape>
          <bpmndi:BPMNShape id="UserTask_08mft2c_di" bpmnElement="say-hello">
            <dc:Bounds x="270" y="77" width="100" height="80" />
            <bpmndi:BPMNLabel />
          </bpmndi:BPMNShape>
          <bpmndi:BPMNShape id="Activity_1qn23u0_di" bpmnElement="Activity_103k5k7">
            <dc:Bounds x="430" y="77" width="100" height="80" />
            <bpmndi:BPMNLabel />
          </bpmndi:BPMNShape>
          <bpmndi:BPMNShape id="Event_0wssy3s_di" bpmnElement="Event_0wssy3s">
            <dc:Bounds x="752" y="99" width="36" height="36" />
            <bpmndi:BPMNLabel>
              <dc:Bounds x="734" y="142" width="73" height="27" />
            </bpmndi:BPMNLabel>
          </bpmndi:BPMNShape>
          <bpmndi:BPMNShape id="Activity_0w7tt73_di" bpmnElement="Activity_1ymuj81">
            <dc:Bounds x="590" y="77" width="100" height="80" />
          </bpmndi:BPMNShape>
        </bpmndi:BPMNPlane>
      </bpmndi:BPMNDiagram>
    </bpmn:definitions>
    ```
    

## Start the process up

1. head back to IDE
2. find the `Application.java` > right-click > click `Run`
    
    it will build my project and get all the necessary dependencies and then just start it up.
    
3. if you see `starting to acquire jobs` on console, it means things have started.
    
    ```
     ____                                 _         ____  _       _    __                      
    / ___| __ _ _ __ ___  _   _ _ __   __| | __ _  |  _ \| | __ _| |_ / _| ___  _ __ _ __ ___  
    | |   / _` | '_ ` _ \| | | | '_ \ / _` |/ _` | | |_) | |/ _` | __| |_ / _ \| '__| '_ ` _ \ 
    | |__| (_| | | | | | | |_| | | | | (_| | (_| | |  __/| | (_| | |_|  _| (_) | |  | | | | | |
    \____/\__,_|_| |_| |_|\__,_|_| |_|\__,_|\__,_| |_|   |_|\__,_|\__|_|  \___/|_|  |_| |_| |_|
    
      Spring-Boot:  (v2.6.4)
      Camunda Platform: (v7.17.0)
      Camunda Platform Spring Boot Starter: (v7.17.0)
    
    ...
    
    2022-05-07 20:19:11.072  INFO 5572 --- [ingJobExecutor]] org.camunda.bpm.engine.jobexecutor       : ENGINE-14018 JobExecutor[org.camunda.bpm.engine.spring.components.jobexecutor.SpringJobExecutor] starting to acquire jobs
    ```
    
4. open up a browser and head to `localhost:8080`
5. login
    - Username: demo
    - Password: demo
6. go to `Tasklist` to start my process
    - Tasklist works by having some filters to filter the tasks
7. click `➕Add a simple filter`, this will show us all tasks that are available (currently is none)
8. go up to the top right and then click on `🗄️Start process` > click `my-first-camunda-project-process` > click `Start` (Business key is unnecessary)
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/bde2cc85-e02e-4ab8-9521-8fb87765f93e/Untitled.png)
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/15866556-82de-456b-b4dc-629f3a34d850/Untitled.png)
    
    What this will do is it will start the process you can see in the bottom right, that it says process has been started. 
    
    If I click on the `All Tasks` one more time, it will refresh and we will see `Prepare for Departure`
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/0e4aaff9-ab63-491c-8e76-8c4b1a4c9acf/Untitled.png)
    
    Meanwhile, in Cockpit (click `🏠` little house icon and select `Cockpit`), we have the what’s going on in the engine. If click on `process` tab, we can see that `my-first-camunda-project-process` is here and the number of running instances is one.
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/3ee17adb-f72f-4997-b273-9b0cde0a0bb8/Untitled.png)
    
    If go inside this process by click the process name hyperlink, we can see we are in `"Prepare for Departure"`.
    
    So this is an admin view to see what’s going on, Meanwhile, our `Tasklist` view shows more of a perspective of a end user of the process.
    
9. Let’s complete this task
    
    go back to `Tasklist` > click on a `Task` > click on `👤Claim` (cause this task right now is read-only, unless somebody claims the task, they unable to access the data) > fulfill the form > click `Complete` > and next task shows up...
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/ecd0ec61-e3a6-4105-8718-2d7ba40485eb/Untitled.png)
    
10. Complete all tasks

Next chapter, we’re gonna do is actually add some new content to our process model to allow us to use the data added by user specifically their destination, to decide on the method of travel. And for that, we’re going to return to our process model.

<aside>
📝 **How to delete process?**

1. Go to Cockpit > `Deployments` > click `🗑️` trash icon
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/606fe738-c76c-4b9a-bac8-1b40f67c1164/Untitled.png)
    
2. select `Cascade`, `Skip Custom Listeners`, `Skip IO Mappings` options > click `Delete`
    
    💡`Cascade`: cascade remove runing process
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/21ec7999-9ee1-471d-b78a-2fa32132a21d/Untitled.png)
    
3. restart `Application.java`
</aside>