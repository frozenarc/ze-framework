package org.frozenarc.zeframework.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import javax.servlet.http.Part;
import org.frozenarc.zeframework.model.FileData;

/**
 *
 * @author Manan
 */
public class PartToFileDataConvertor {

    public static FileData[] convert(ArrayList<Part> partList) throws Exception {
        FileData[] fileDatas = new FileData[partList.size()];
        for (int i = 0; i < fileDatas.length; i++) {
            Part part = partList.get(i);
            fileDatas[i] = convert(part);
        }
        return fileDatas;
    }

    public static FileData convert(Part part) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        InputStream inputStream = part.getInputStream();
        try {
            FileData fileData = new FileData();
            fileData.setName(part.getName());
            fileData.setContentType(part.getContentType());
            fileData.setFileSize(part.getSize());
            String contentDisposition = part.getHeader("content-disposition");
            int index;
            fileData.setFileName(contentDisposition.substring(index = contentDisposition.indexOf("filename=\"") + 10, contentDisposition.indexOf("\"", index)));
            int length;
            byte[] chunk = new byte[400];
            while ((length = inputStream.read(chunk)) > 0) {
                outputStream.write(chunk, 0, length);
            }
            fileData.setData(outputStream.toByteArray());
            return fileData;
        } finally {
            outputStream.close();
            inputStream.close();
            part.delete();
        }
    }
}
