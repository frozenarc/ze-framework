package org.frozenarc.zeframework.tei;

import javax.servlet.jsp.tagext.TagData;
import javax.servlet.jsp.tagext.TagExtraInfo;
import javax.servlet.jsp.tagext.VariableInfo;

/**
 * Tei class for 'define' tag
 * @author Manan
 */
public class DefineTei extends TagExtraInfo {

    @Override
    public VariableInfo[] getVariableInfo(TagData data) {
        return new VariableInfo[] {
            new VariableInfo(data.getAttributeString("variable"), data.getAttributeString("typeclass"), true, VariableInfo.AT_END)
        };
    }
}
