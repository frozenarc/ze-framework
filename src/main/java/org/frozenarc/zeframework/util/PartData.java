package org.frozenarc.zeframework.util;

import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.Part;

/**
 *
 * @author Manan
 */
public class PartData {
    
    protected ArrayList<String> partNameList;
    protected HashMap<String, ArrayList<Part>> partMap;
    
    public PartData() {
        partNameList=new ArrayList<String>();
        partMap=new HashMap<String, ArrayList<Part>>();
    }
    
    public void addPart(Part part) {
        if(!partNameList.contains(part.getName())) {
            partNameList.add(part.getName());
            partMap.put(part.getName(), new ArrayList<Part>());
        }
        partMap.get(part.getName()).add(part);
    }
    
    public HashMap<String, ArrayList<Part>> getPartMap() {
        return partMap;
    }

    public void setPartMap(HashMap<String, ArrayList<Part>> partMap) {
        this.partMap = partMap;
    }

    public ArrayList<String> getPartNameList() {
        return partNameList;
    }

    public void setPartNameList(ArrayList<String> partNameList) {
        this.partNameList = partNameList;
    }
}
