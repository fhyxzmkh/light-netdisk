package com.backend.utils;
import cn.hutool.core.util.StrUtil;
import com.backend.exception.BusinessException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class StringTools {

    public static void checkParam(Object param) {
        try {
            Field[] fields = param.getClass().getDeclaredFields();
            boolean notEmpty = false;
            for (Field field : fields) {
                String methodName = "get" + StringTools.upperCaseFirstLetter(field.getName());
                Method method = param.getClass().getMethod(methodName);
                Object object = method.invoke(param);
                if (object != null && object instanceof java.lang.String && !StringTools.isEmpty(object.toString())
                        || object != null && !(object instanceof java.lang.String)) {
                    notEmpty = true;
                    break;
                }
            }
            if (!notEmpty) {
                throw new BusinessException("多参数更新，删除，必须有非空条件");
            }
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("校验参数是否为空失败");
        }
    }

    public static String upperCaseFirstLetter(String field) {
        if (isEmpty(field)) {
            return field;
        }
        //如果第二个字母是大写，第一个字母不大写
        if (field.length() > 1 && Character.isUpperCase(field.charAt(1))) {
            return field;
        }
        return field.substring(0, 1).toUpperCase() + field.substring(1);
    }

    public static boolean isEmpty(String str) {
        if (null == str || "".equals(str) || "null".equals(str) || "\u0000".equals(str)) {
            return true;
        } else if ("".equals(str.trim())) {
            return true;
        }
        return false;
    }

    public static String renameFileWithUUID(String fileName) {
        // 检查文件名是否为空
        if (fileName == null || fileName.isEmpty()) {
            throw new IllegalArgumentException("文件名不能为空");
        }

        // 查找最后一个点（.）的位置
        int dotIndex = fileName.lastIndexOf('.');

        String nameWithoutExtension;
        String extension = ""; // 默认扩展名为空

        // 如果有扩展名
        if (dotIndex != -1) {
            nameWithoutExtension = fileName.substring(0, dotIndex);
            extension = fileName.substring(dotIndex); // 包括点（.）
        } else {
            // 如果没有扩展名，整个文件名作为名称部分
            nameWithoutExtension = fileName;
        }

        // 生成UUID
        String uuid = StrUtil.uuid().replace("-", "");

        // 构建新的文件名
        return nameWithoutExtension + "_" + uuid + extension;
    }
}
