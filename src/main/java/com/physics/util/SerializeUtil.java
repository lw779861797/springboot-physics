package com.physics.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializeUtil {
    // 对象序列化成字数组
    public static byte[] serialize(Object object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            return baos.toByteArray();
        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
                if (baos != null) {
                    baos.close();
                }

            } catch(Exception e) {
//                logger.error(e.getMessage(), e);
            e.printStackTrace();
            }
        }
        return null;
    }
//    反序列化
    public static Object unserialize(byte[] bytes) {
        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
        try {
            bais = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
            e.printStackTrace();
        } finally {

            try {
                if (bais != null) {
                    bais.close();
                }
                if (ois != null) {
                    ois.close();
                }
            } catch(Exception e) {
//                logger.error(e.getMessage(), e);
                e.printStackTrace();
            }
        }
        return null;
    }
}
