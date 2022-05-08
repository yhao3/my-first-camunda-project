# **Running Timer Events, Catching BPMN Error Events in Spring Boot Project**

## 1. Adding a Timer Event to **send a postcard during** "Have fun"

1. Which Timer Event should we select?
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/4ce7ee66-3185-48c8-8263-e82bb6645637/Untitled.png)
    
    - **Timer Boundary Event**: (X)
    It’ll interrupt the current activity after the time has expired, and then it’ll send a token a different way.
    - **Timer Boundary Event (non-interrupting)**: (O)
    It can be fired multiple times or when it is fired it will not affect the current process of "have fun"
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/9d07de7e-798b-426e-bfe9-f7322210e9c7/Untitled.png)
    
2. Decide what to do when the timer does fire ⮕ "send a postcard"
    
    <aside>
    📝 NOTE:
    
    - Remeber change the task type to "User Task"
    - Watch out the flow’s source reference
        - ✅ Correct:
            
            ```xml
            <bpmn:sequenceFlow id="Flow_timerEventToSendPostcard" sourceRef="Event_timerEvent" targetRef="Activity_sendPostcard" />
            ```
            
            ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/ba47b326-69c4-444f-bbc7-9606998aba9b/Untitled.png)
            
        - ❌ Wrong:
            
            ```xml
            <bpmn:sequenceFlow id="Flow_timerEventToSendPostcard" sourceRef="Activity_haveFun" targetRef="Activity_sendPostcard" />
            ```
            
            ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/2f9d25fa-080c-4d1b-a92a-6c4fdb23bdf3/Untitled.png)
            
    </aside>
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/0fc17ab4-42bd-4640-9e07-abbc9f8e732f/Untitled.png)
    
3. But we also should probably send more than one postcard, so we also want this to happen perhaps multiple times as long as we’re doing this. ⮕ Setting the "Timer Type" to "`Cycle`" and "Value" is "`R/PT10S`" (every 10 seconds, repeat forever)
    - **Type**:
        - `Date`: 
        wait a specific date
        - `Duration`: 
        wait a certain amount of time and then you would trigger it, just the one time.
        - `Cycle`: 
        wait for a duration and then reactivate the duration each time.
    - **Value** example:
        - `R5/PT10S`: every 10 seconds, up to 5 times
        - `R/PT1M`: every minute, infinitely
        - `R/P1D`: every day, infinitely
        - ...

## 2. restart Application to redeploy the process and then start the process up and complete all task

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/4efd8147-6964-49d9-9a6a-4316908232e1/Untitled.png)

- Once we start the "Have fun" task, we should wait at this point of the process. And after 10 seconds of wating there, it’s actually going to trigger this timer and eventully it’s going to have a second token arrive "Send a postcard back home" (it’ll repaeat infinitely)
- Untill we eventually complete the "Have fun" task, the "Send a postcard" task will stop.

## 3. Adding an Error Event to catch the potential for something to go wrong with this travel

### two aspects of Error Events:

1. The first is we need to throw the error event 
2. and then we need to have something that’s ready to catch the error event
    
    The ways of throwing it: 
    
    - Use a symbol to throw it
    - throw it from code

### throw error event from code

- ReserveSeatOnTrain.java
    
    ```java
    package com.example.workflow;
    
    import javax.inject.Named;
    
    import org.camunda.bpm.engine.delegate.BpmnError;
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
    		} else if (moneyDouble <= 10) {
    			ticketType = null;
    			throw new BpmnError("CannotAfford", "Can't afford the ticket");
    		}
    		
    		delegateExecution.setVariable("ticketType", ticketType);
    		
    	}
    
    }
    ```
    

### We also need a BPMN symbol to catch this error event

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/cb216f7a-5f90-410a-bffa-1376649534ae/Untitled.png)

- Specify the error:
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/558bc511-f5ef-4393-9620-cc9c42d962c4/Untitled.png)
    
    ```
    Global Error referenced: -
    Name: Error name.
    Code: Java will throw the error of that code to be correlated against that.
    Message: -
    Code Variable: -
    Message Variable: Once the error occurs, the details of that error will be stored in a variable called xxx.
    ```
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/bc7796a6-2f6a-4d3b-8246-4978e76c26e9/Untitled.png)
    

### Eventually, if we restart a process and enter money less than 10 dollars...

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/0b865b73-4736-4d5d-ab26-58eb65912670/Untitled.png)