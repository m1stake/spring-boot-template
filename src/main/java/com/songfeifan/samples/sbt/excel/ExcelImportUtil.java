package com.songfeifan.samples.sbt.excel;

import com.songfeifan.samples.sbt.util.ClassPathUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.jxls.reader.ReaderBuilder;
import org.jxls.reader.ReaderConfig;
import org.jxls.reader.XLSReader;
import org.xml.sax.SAXException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public abstract class ExcelImportUtil {

    private ExcelImportUtil() { /* */ }

    static {
        ReaderConfig.getInstance().setSkipErrors( true );
    }

    public static void parseData(Map<String, Object> dataBeans, String xmlConfigPath, InputStream dataIn) {
        try (InputStream inputXML = new BufferedInputStream(ClassPathUtil.getInputStream(xmlConfigPath));
             InputStream inputXLS = new BufferedInputStream(dataIn))
        {
            XLSReader mainReader = ReaderBuilder.buildFromXML(inputXML);
            mainReader.read(inputXLS, dataBeans);
        } catch (IOException | InvalidFormatException | SAXException e) {
            throw new RuntimeException(e);
        }
    }

}
