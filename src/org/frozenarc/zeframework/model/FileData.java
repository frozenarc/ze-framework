package org.frozenarc.zeframework.model;

/**
 * Uploading file data and properties would set to the class. The class can represents downloading file too.
 * @author Manan
 */
public class FileData {

    public String getContentDispositionType() {
        return contentDispositionType;
    }

    public void setContentDispositionType(String contentDispositionType) {
        this.contentDispositionType = contentDispositionType;
    }

    public String getDownloadContentType() {
        return downloadContentType;
    }

    public void setDownloadContentType(String downloadContentType) {
        this.downloadContentType = downloadContentType;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public FileData() {
        contentDispositionType="inline";
    }
    
    protected String name;
    protected String fileName;
    protected String contentType;
    protected String downloadContentType;
    protected String contentDispositionType;
    protected long fileSize;
    protected byte[] data;
}
