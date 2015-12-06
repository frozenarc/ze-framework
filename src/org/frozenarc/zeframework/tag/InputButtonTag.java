package org.frozenarc.zeframework.tag;

import org.frozenarc.zeframework.applicationexception.ApplicationException;
import org.frozenarc.zeframework.datatypeconvert.DataTypeConvertor;
import org.frozenarc.zeframework.util.FormatEvaluator;
import org.frozenarc.zeframework.util.FormatInfo;
import org.frozenarc.zeframework.util.ViewValueUtil;

/**
 * Super class for input type 'check' and 'radio'.
 * @author Manan
 */
public abstract class InputButtonTag extends InputTag {
    
    protected String checked;
    protected String groupname;
    protected String checkedvaluefrom;
    
    @Override
    public void handleValueFrom(StringBuilder builder) throws Exception {
        super.handleValueFrom(builder);
        if(checkedvaluefrom!=null) {
            Object objCheckedvaluefrom=ViewValueUtil.handleValueFrom(pageContext, checkedvaluefrom);
            FormatInfo info=FormatEvaluator.getFormatInfo(checkedvaluefrom);
            String checkedvalue = DataTypeConvertor.convertObject(objCheckedvaluefrom, info);
            if(checkedvalue!=null && checkedvalue.equals("true")) {
                builder.append(" checked=\""+checkedvalue+"\"");
            }
        }
    }
    
    @Override
    public void addAtributes(StringBuilder builder) throws ApplicationException {
        super.addAtributes(builder);
        if(checked!=null && checked.equals("true")) {
            builder.append(" checked=\""+checked+"\"");
        }
    }
    
    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }
    
    
    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }
    
    public String getCheckedvaluefrom() {
        return checkedvaluefrom;
    }

    public void setCheckedvaluefrom(String checkedvaluefrom) {
        this.checkedvaluefrom = checkedvaluefrom;
    }
    
}
