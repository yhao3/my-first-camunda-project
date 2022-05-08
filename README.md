# Chapter3: **Adding an XOR Gateway and Running Java in a Spring Boot Project**

## **Adding an XOR Gateway**

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/6dc299eb-f5d7-462a-bbdc-1ceb8dbc7c17/Untitled.png)

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/40b259a4-daef-4add-bcda-78ed760bb3f5/Untitled.png)

### and the `process.bpmn` like this...

- `process.bpmn`
    
    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <bpmn:definitions xmlns:bpmn="http://www.omg.org/spec/BPMN/20100524/MODEL" xmlns:bpmndi="http://www.omg.org/spec/BPMN/20100524/DI" xmlns:dc="http://www.omg.org/spec/DD/20100524/DC" xmlns:di="http://www.omg.org/spec/DD/20100524/DI" xmlns:camunda="http://camunda.org/schema/1.0/bpmn" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" id="Definitions_0fr9mxs" targetNamespace="http://bpmn.io/schema/bpmn" exporter="Camunda Modeler" exporterVersion="5.0.0">
      <bpmn:process id="my-first-camunda-project-process" name="Hao" isExecutable="true">
        <bpmn:startEvent id="StartEvent_1" name="Hao want to travel around Taiwan">
          <bpmn:outgoing>SequenceFlow_1fp17al</bpmn:outgoing>
        </bpmn:startEvent>
        <bpmn:sequenceFlow id="SequenceFlow_1fp17al" sourceRef="StartEvent_1" targetRef="say-hello" />
        <bpmn:userTask id="say-hello" name="Prepare for Departure" camunda:formKey="camunda-forms:/forms/prepareForDeparture.form" camunda:candidateUsers="demo">
          <bpmn:incoming>SequenceFlow_1fp17al</bpmn:incoming>
          <bpmn:outgoing>Flow_0fxkaw3</bpmn:outgoing>
        </bpmn:userTask>
        <bpmn:sequenceFlow id="Flow_0fxkaw3" sourceRef="say-hello" targetRef="Gateway_07esapt" />
        <bpmn:userTask id="Activity_103k5k7" name="Get a Taxi" camunda:formKey="camunda-forms:/forms/getTaxi.form">
          <bpmn:incoming>Flow_19rkpxf</bpmn:incoming>
          <bpmn:outgoing>Flow_0id4i0q</bpmn:outgoing>
        </bpmn:userTask>
        <bpmn:sequenceFlow id="Flow_0id4i0q" sourceRef="Activity_103k5k7" targetRef="Gateway_1t1cj1o" />
        <bpmn:endEvent id="Event_0wssy3s" name="Hao has return Taiwan">
          <bpmn:incoming>Flow_0rblh6f</bpmn:incoming>
        </bpmn:endEvent>
        <bpmn:sequenceFlow id="Flow_0rblh6f" sourceRef="Activity_1ymuj81" targetRef="Event_0wssy3s" />
        <bpmn:userTask id="Activity_1ymuj81" name="Have fun">
          <bpmn:incoming>Flow_04e1rzl</bpmn:incoming>
          <bpmn:outgoing>Flow_0rblh6f</bpmn:outgoing>
        </bpmn:userTask>
        <bpmn:exclusiveGateway id="Gateway_07esapt" name="Where are you going">
          <bpmn:incoming>Flow_0fxkaw3</bpmn:incoming>
          <bpmn:outgoing>Flow_19rkpxf</bpmn:outgoing>
          <bpmn:outgoing>Flow_1ws90e5</bpmn:outgoing>
        </bpmn:exclusiveGateway>
        <bpmn:sequenceFlow id="Flow_19rkpxf" name="Taipei" sourceRef="Gateway_07esapt" targetRef="Activity_103k5k7">
          <bpmn:conditionExpression xsi:type="bpmn:tFormalExpression">#{city == 'Taipei'}</bpmn:conditionExpression>
        </bpmn:sequenceFlow>
        <bpmn:sequenceFlow id="Flow_1ws90e5" name="Taichung" sourceRef="Gateway_07esapt" targetRef="Activity_1ieie33">
          <bpmn:conditionExpression xsi:type="bpmn:tFormalExpression">#{city == 'Taichung'}</bpmn:conditionExpression>
        </bpmn:sequenceFlow>
        <bpmn:exclusiveGateway id="Gateway_1t1cj1o">
          <bpmn:incoming>Flow_0id4i0q</bpmn:incoming>
          <bpmn:incoming>Flow_1kjjjw1</bpmn:incoming>
          <bpmn:outgoing>Flow_04e1rzl</bpmn:outgoing>
        </bpmn:exclusiveGateway>
        <bpmn:sequenceFlow id="Flow_04e1rzl" sourceRef="Gateway_1t1cj1o" targetRef="Activity_1ymuj81" />
        <bpmn:sequenceFlow id="Flow_1kjjjw1" sourceRef="Activity_1ieie33" targetRef="Gateway_1t1cj1o" />
        <bpmn:serviceTask id="Activity_1ieie33" name="Book a place on the train" camunda:delegateExpression="#{reserveSeatOnTrain}">
          <bpmn:incoming>Flow_1ws90e5</bpmn:incoming>
          <bpmn:outgoing>Flow_1kjjjw1</bpmn:outgoing>
        </bpmn:serviceTask>
      </bpmn:process>
      <bpmndi:BPMNDiagram id="BPMNDiagram_1">
        <bpmndi:BPMNPlane id="BPMNPlane_1" bpmnElement="my-first-camunda-project-process">
          <bpmndi:BPMNEdge id="Flow_0fxkaw3_di" bpmnElement="Flow_0fxkaw3">
            <di:waypoint x="370" y="277" />
            <di:waypoint x="435" y="277" />
          </bpmndi:BPMNEdge>
          <bpmndi:BPMNEdge id="SequenceFlow_1fp17al_di" bpmnElement="SequenceFlow_1fp17al">
            <di:waypoint x="215" y="277" />
            <di:waypoint x="270" y="277" />
          </bpmndi:BPMNEdge>
          <bpmndi:BPMNEdge id="Flow_0id4i0q_di" bpmnElement="Flow_0id4i0q">
            <di:waypoint x="650" y="277" />
            <di:waypoint x="715" y="277" />
          </bpmndi:BPMNEdge>
          <bpmndi:BPMNEdge id="Flow_0rblh6f_di" bpmnElement="Flow_0rblh6f">
            <di:waypoint x="910" y="277" />
            <di:waypoint x="952" y="277" />
          </bpmndi:BPMNEdge>
          <bpmndi:BPMNEdge id="Flow_19rkpxf_di" bpmnElement="Flow_19rkpxf">
            <di:waypoint x="485" y="277" />
            <di:waypoint x="550" y="277" />
            <bpmndi:BPMNLabel>
              <dc:Bounds x="503" y="259" width="29" height="14" />
            </bpmndi:BPMNLabel>
          </bpmndi:BPMNEdge>
          <bpmndi:BPMNEdge id="Flow_1ws90e5_di" bpmnElement="Flow_1ws90e5">
            <di:waypoint x="460" y="252" />
            <di:waypoint x="460" y="120" />
            <di:waypoint x="550" y="120" />
            <bpmndi:BPMNLabel>
              <dc:Bounds x="487" y="123" width="45" height="14" />
            </bpmndi:BPMNLabel>
          </bpmndi:BPMNEdge>
          <bpmndi:BPMNEdge id="Flow_04e1rzl_di" bpmnElement="Flow_04e1rzl">
            <di:waypoint x="765" y="277" />
            <di:waypoint x="810" y="277" />
          </bpmndi:BPMNEdge>
          <bpmndi:BPMNEdge id="Flow_1kjjjw1_di" bpmnElement="Flow_1kjjjw1">
            <di:waypoint x="650" y="120" />
            <di:waypoint x="740" y="120" />
            <di:waypoint x="740" y="252" />
          </bpmndi:BPMNEdge>
          <bpmndi:BPMNShape id="_BPMNShape_StartEvent_2" bpmnElement="StartEvent_1">
            <dc:Bounds x="179" y="259" width="36" height="36" />
            <bpmndi:BPMNLabel>
              <dc:Bounds x="165" y="302" width="65" height="40" />
            </bpmndi:BPMNLabel>
          </bpmndi:BPMNShape>
          <bpmndi:BPMNShape id="UserTask_08mft2c_di" bpmnElement="say-hello">
            <dc:Bounds x="270" y="237" width="100" height="80" />
            <bpmndi:BPMNLabel />
          </bpmndi:BPMNShape>
          <bpmndi:BPMNShape id="Activity_1qn23u0_di" bpmnElement="Activity_103k5k7">
            <dc:Bounds x="550" y="237" width="100" height="80" />
            <bpmndi:BPMNLabel />
          </bpmndi:BPMNShape>
          <bpmndi:BPMNShape id="Gateway_07esapt_di" bpmnElement="Gateway_07esapt" isMarkerVisible="true">
            <dc:Bounds x="435" y="252" width="50" height="50" />
            <bpmndi:BPMNLabel>
              <dc:Bounds x="424" y="309" width="73" height="27" />
            </bpmndi:BPMNLabel>
          </bpmndi:BPMNShape>
          <bpmndi:BPMNShape id="Gateway_1t1cj1o_di" bpmnElement="Gateway_1t1cj1o" isMarkerVisible="true">
            <dc:Bounds x="715" y="252" width="50" height="50" />
          </bpmndi:BPMNShape>
          <bpmndi:BPMNShape id="Activity_0w7tt73_di" bpmnElement="Activity_1ymuj81">
            <dc:Bounds x="810" y="237" width="100" height="80" />
            <bpmndi:BPMNLabel />
          </bpmndi:BPMNShape>
          <bpmndi:BPMNShape id="Event_0wssy3s_di" bpmnElement="Event_0wssy3s">
            <dc:Bounds x="952" y="259" width="36" height="36" />
            <bpmndi:BPMNLabel>
              <dc:Bounds x="934" y="302" width="73" height="27" />
            </bpmndi:BPMNLabel>
          </bpmndi:BPMNShape>
          <bpmndi:BPMNShape id="Activity_0sql0r2_di" bpmnElement="Activity_1ieie33">
            <dc:Bounds x="550" y="80" width="100" height="80" />
          </bpmndi:BPMNShape>
        </bpmndi:BPMNPlane>
      </bpmndi:BPMNDiagram>
    </bpmn:definitions>
    ```
    

### Create a Java class and then call it on Service Task

1. implements `JavaDelegate` in order to call it from the engine
2. implements a method called `execute()`, this method allows us to get context from the engine in Runtime and be able to decide on what to do accordingly(照著做). We can get variables and set variables.
3. add a `@Named` annotation in order to call this being by name without needing to use the package name. 
💡 Alternatively you can also call it by it’s full class path

```java
package com.example.workflow;

import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

@Named
public class ReserveSeatOnTrain implements JavaDelegate {

	@Override
	public void execute(DelegateExecution delegateExecution) throws Exception {
		
		String money = "0.0";
		String ticketType = "Coach"; // default ticket type
		
		money = (String) delegateExecution.getVariable("money");
		double moneyDouble = Double.parseDouble(money);
		
		if (moneyDouble >= 10000) {
			ticketType = "First Class";
		} else if (moneyDouble >= 5000) {
			ticketType = "Business Class";
		}
		
		delegateExecution.setVariable("ticketType", ticketType);
		
	}

}
```

### Connect the Service Task to this Java class

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/88a8902a-654f-4464-92c2-bd3d00808edf/Untitled.png)

1. use `Delegate Expression` type
2. write an expression: `#{Java class name}`
    
    This means that when we start this process, it should be able to call this Java class and when we get back to `"Have fun"`, we’ll then be able to see the ticket type as a variable that’s chosen if we have gone via Taichung
    
    <aside>
    ⚠️ ALERT: We need to change the case of the first letter
    
    </aside>
    

### restart Application to redeploy the process

<aside>
⚠️ ALERT:

Because the [connect process engine plugin](https://docs.camunda.org/manual/7.13/user-guide/process-engine/connectors/) is not enabled, so we should add the following dependency to our pom.xml.

```xml
<dependency>
  <groupId>org.camunda.bpm</groupId>
  <artifactId>camunda-engine-plugin-connect</artifactId>
</dependency>
```

And then we will see the `camunda-connect-core-1.5.2.jar`

</aside>

1. Restart `Application.java`
2. And `camunda-h2-database.mv.db` will showed up when we started first time, this is actually the H2 database that is used.
3. Open browser again, and then start the process up and complete all task

Next chapter, we will add some new BPMN symbols and show how to combine parallel executions in the same model.