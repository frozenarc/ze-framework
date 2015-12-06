package org.frozenarc.zeframework.applicationmessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents application messages.
 * @author Manan
 */
public class ApplicationMessages {
    
    public List<ApplicationMessage> getApplicationMessageList() {
        return applicationMessageList;
    }

    public Map<Integer, ApplicationMessage> getApplicationMessageMap() {
        return applicationMessageMap;
    }
    
    protected Map<Integer, ApplicationMessage> applicationMessageMap;
    protected List<ApplicationMessage> applicationMessageList;
    
    public ApplicationMessages() {
        this.applicationMessageList=new ArrayList<ApplicationMessage>();
        applicationMessageMap=new HashMap<Integer, ApplicationMessage>();
    }
    
    public void addMessage(ApplicationMessage message) {
        if(applicationMessageMap.get(message.getMessageCode())==null) {
            applicationMessageList.add(message);
            applicationMessageMap.put(message.getMessageCode(), message);
        }
    }
}
