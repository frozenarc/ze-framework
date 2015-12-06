package org.frozenarc.zeframework.tag;

import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.frozenarc.zeframework.applicationexception.ApplicationException;
import org.frozenarc.zeframework.constants.FWConstants;
import org.frozenarc.zeframework.model.FileData;
import org.frozenarc.zeframework.util.ViewValueUtil;

/**
 * Represents 'filedownload' tag
 * @author Manan
 */
public class FileDownloadTag extends LinkTag {

    public String getFiledatascope() {
        return filedatascope;
    }

    public void setFiledatascope(String filedatascope) {
        this.filedatascope = filedatascope;
    }

    public String getContentdisptype() {
        return contentdisptype;
    }

    public void setContentdisptype(String contentdisptype) {
        this.contentdisptype = contentdisptype;
    }

    public String getContenttype() {
        return contenttype;
    }

    public void setContenttype(String contenttype) {
        this.contenttype = contenttype;
    }
    protected String uuid;

    public String getValuefrom() {
        return valuefrom;
    }

    public void setValuefrom(String valuefrom) {
        this.valuefrom = valuefrom;
    }
    protected String valuefrom;
    protected String contenttype;
    protected String contentdisptype;
    protected String filedatascope;

    @Override
    public void addAtributes(StringBuilder builder) throws ApplicationException {
        uuid = UUID.randomUUID().toString();
        view = FWConstants.FILE_DOWNLOAD;
        querystring = FWConstants.FILE_ID + "=" + uuid;
        try {
            if (valuefrom != null) {
                FileData fileData = (FileData) ViewValueUtil.handleValueFrom(pageContext, valuefrom);
                if (fileData != null) {
                    if (contentdisptype != null) {
                        fileData.setContentDispositionType(contentdisptype);
                    }
                    if (contenttype != null) {
                        fileData.setDownloadContentType(contenttype);
                    } else {
                        fileData.setDownloadContentType(fileData.getContentType());
                    }
                }
                if (filedatascope != null && filedatascope.equals("application")) {
                    ((HttpServletRequest) pageContext.getRequest()).getServletContext().setAttribute(FWConstants.FILEDATA_ID + uuid, fileData);
                } else {
                    ((HttpServletRequest) pageContext.getRequest()).getSession().setAttribute(FWConstants.FILEDATA_ID + uuid, fileData);
                }
            }
        } catch (Exception ex) {
            throw new ApplicationException(ex);
        }
        super.addAtributes(builder);
    }
}
