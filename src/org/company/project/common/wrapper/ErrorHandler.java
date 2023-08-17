package org.company.project.common.wrapper;

import org.apache.log4j.Logger;
import org.company.project.common.exception.NotMatchRecordVersionException;
import org.company.project.common.exception.RecordNotFoundException;
import org.company.project.common.exception.ValidationException;

import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ErrorHandler {
    private ErrorHandler(){}
    private static final Logger LOGGER = Logger.getLogger(ErrorHandler.class);
    private static final Map<String, String> MAP = new HashMap<>();
    public static Map getError (Exception e){
        e.printStackTrace();
        if(e instanceof ArithmeticException){
            LOGGER.error(e.getMessage());
            LOGGER.error(e.getClass());
//            String msg = "کاربر گرامی خطی منطقی ایجاد شده است.";
            MAP.put("CODE","101");
            MAP.put("MSG",e.getMessage());
        } else if (e instanceof RecordNotFoundException) {
            LOGGER.error(e.getMessage());
            LOGGER.error(e.getClass());
//            String msg = "کاربر گرامی رکوردی یافت نشد.";
            MAP.put("CODE","102");
            MAP.put("MSG",e.getMessage());
        }else if(e instanceof ValidationException){
            LOGGER.error(e.getMessage());
            LOGGER.error(e.getClass());
//            String msg = "کاربر گرامی اطلاعات بدرستی وارد نشده است.";
            MAP.put("CODE","103");
            MAP.put("MSG", e.getMessage());
        } else if (e instanceof NotMatchRecordVersionException) {
            LOGGER.error(e.getMessage());
            LOGGER.error(e.getClass());
            MAP.put("CODE","104");
            MAP.put("MSG","the record changed by another transaction");
        } else if (e instanceof SQLException) {
            LOGGER.error(e.getMessage());
            LOGGER.error(e.getClass());
//            String msg = "کاربر گرامی خطای درج در پایگاه داده رخ داده است.";
//            String asciiEncodingMSG = new String(msg.getBytes(), StandardCharsets.US_ASCII);
//            System.out.println(asciiEncodingMSG);
            MAP.put("CODE","105");
            MAP.put("MSG",e.getMessage());
        }else {
            LOGGER.error(e.getMessage());
            LOGGER.error(e.getClass());
            MAP.put("CODE","1000");
            MAP.put("MSG",e.getMessage());
        }
        return MAP;
    }
}
