# Chapter3: **Adding an XOR Gateway and Running Java in a Spring Boot Project**

## **Adding an XOR Gateway**

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/6dc299eb-f5d7-462a-bbdc-1ceb8dbc7c17/Untitled.png)

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/806ea30e-d9e1-4553-aa55-4687da585b52/Untitled.png)

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/40b259a4-daef-4add-bcda-78ed760bb3f5/Untitled.png)

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