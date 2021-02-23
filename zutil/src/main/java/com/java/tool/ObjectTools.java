package com.java.tool;

import lombok.extern.log4j.Log4j2;

import java.io.*;
import java.util.Collection;
import java.util.Map;

/**
 * 关于Object的工具集合
 * 集合的检测空也在这里面
 *
 * @author zhoup
 */
@Log4j2
public abstract class ObjectTools {
    /**
     * 将序列化对象转为Byte[](byte数组)
     *
     * @param serializable 对象
     * @return
     */
    public final static byte[] toArray(Serializable serializable) {
        byte[] result = null;
        try (ByteArrayOutputStream bytes = new ByteArrayOutputStream();
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(bytes)) {
            objectOutputStream.writeObject(serializable);
            objectOutputStream.flush();
            result = bytes.toByteArray();
        } catch (IOException e) {
            log.error("Io异常", e);
        }
        return result;
    }

    /**
     * 将byte[]数组转为对象
     *
     * @param returnType 返回类型
     * @param objArray   byte[]
     * @param <T>
     * @return
     */
    public final static <T extends Serializable> T toObject(Class<T> returnType, byte[] objArray) {
        Object o = null;
        ByteArrayInputStream bai = new ByteArrayInputStream(objArray);
        try (ObjectInputStream ois = new ObjectInputStream(bai);) {
            o = ois.readObject();
        } catch (IOException e0) {
            log.error("对象转换出错(IO异常),请检查ReturnType.", e0);
        } catch (ClassNotFoundException e) {
            log.error("对象转换出错(没有找到对应的类),请检查ReturnType.", e);
        }
        return (T) o;
    }

    /**
     * 校验集合是否为空，若为空则触发空指针
     *
     * @param collection
     * @param message
     * @param <E>
     * @return
     */
    public static <E> Collection<E> checkCollectionIsEmpty(Collection<E> collection, String message) {
        if (collection == null || collection.isEmpty()) {
            throw new NullPointerException(message);
        }
        return collection;
    }

    /**
     * 校验一个集合是否为空
     *
     * @return
     */
    public static <E> boolean collectionIsEmpty(Collection<E> collection) {
        if (collection == null) {
            return true;
        }
        return collection.isEmpty();
    }

    /**
     * 判断map是否为空
     *
     * @param map
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> boolean mapIsEmpty(Map<K, V> map) {
        if (map == null) {
            return true;
        }
        return map.isEmpty();
    }
}
