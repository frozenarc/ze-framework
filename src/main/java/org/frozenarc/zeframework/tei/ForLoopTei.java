package org.frozenarc.zeframework.tei;

import javax.servlet.jsp.tagext.TagData;
import javax.servlet.jsp.tagext.TagExtraInfo;
import javax.servlet.jsp.tagext.VariableInfo;

/**
 * Tei class for 'forloop' tag
 * @author Manan
 */
public class ForLoopTei extends TagExtraInfo {

    @Override
    public VariableInfo[] getVariableInfo(TagData data) {
        return new VariableInfo[] {
            new VariableInfo(data.getAttributeString("variable"), "java.lang.Integer", true, VariableInfo.AT_END)
        };
    }
}
