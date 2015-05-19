package cn.futuremove.adminportal.util.code;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.*;

/**
 * Created by figoxu on 15/4/13.
 */

public class POJOGen {

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://123.57.151.176:3306/joymove_qrj?user=root&password=123456&useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&autoReconnect=true");
            List<String> tableNameList = POJOGen.getTableNameList(conn);
            for(String tableName : tableNameList){
                POJOGen.generatPOJO(conn, tableName, "test", "/Users/figoxu/delete_it/generator_pojo");
            }
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static List<String> getTableNameList(Connection conn) throws SQLException {
        List<String> list = new ArrayList<String>();
        DatabaseMetaData dmd = conn.getMetaData();
        ResultSet tables = dmd.getTables(null, dmd.getUserName(), null, new String[]{"TABLE"});
        while (tables.next()) {
            list.add(tables.getString("TABLE_NAME"));
        }

        return list;
    }

    public static void generatPOJO(Connection conn, String tableName, String packageName, String outputDir) throws SQLException, ClassNotFoundException, IOException {
        DatabaseMetaData dmd = conn.getMetaData();
        ResultSet columns = dmd.getColumns(null, dmd.getUserName(), tableName, null);
        StringBuilder classBuilder = new StringBuilder();
        classBuilder.append("package ").append(packageName).append(";").append("\n\n");
        classBuilder.append("public class ").append(buildClassName(tableName)).append("{").append("\n\n");
        Map<String, String> fieldMap = new LinkedHashMap<String, String>();
        while (columns.next()) {
//            System.out.println(columns.getString("COLUMN_NAME") + ", " + columns.getInt("DATA_TYPE") + ", " + columns.getString("TYPE_NAME"));
            fieldMap.put(columns.getString("COLUMN_NAME"), getClassNameForJavaType(columns.getInt("DATA_TYPE")));
        }
        Set<String> fieldNameSet = fieldMap.keySet();
        for (String fieldName : fieldNameSet) {
            classBuilder.append("\tprivate ").append(fieldMap.get(fieldName)).append(" ").append(buildPropertyName(fieldName)).append(";\n");
        }
        classBuilder.append("\n");
        for (String fieldName : fieldNameSet) {
            String propertyName = buildPropertyName(fieldName);
            Class<?> fieldType = String.class;
            if (fieldMap.get(fieldName).equals("Boolean")) {
                fieldType = Boolean.class;
            }
            classBuilder.append("\tpublic ").append(fieldMap.get(fieldName)).append(" ").append(ReflectUtil.generatReadMethodName(propertyName, fieldType));
            classBuilder.append("(){\n");
            classBuilder.append("\t\t return this.").append(propertyName).append(";\n\t}\n");
            classBuilder.append("\n");
            classBuilder.append("\tpublic void ").append(ReflectUtil.generatWriteMethodName(propertyName)).append("(").append(fieldMap.get(fieldName)).append(" ").append(propertyName).append("){\n");
            classBuilder.append("\t\tthis.").append(propertyName).append(" = ").append(propertyName).append(";\n\t}\n");
            classBuilder.append("\n");
        }
        classBuilder.append("}");

        FileUtil.writeToFile(classBuilder.toString(), new File(new File(outputDir), buildClassName(tableName) + ".java").getAbsolutePath());
    }

    private static String buildClassName(String tableName) {
        String[] tableNameSections = tableName.split("_");
        StringBuilder tb = new StringBuilder();
        for (String tableNameSection : tableNameSections) {
            String tmp = tableNameSection.toLowerCase();
            tb.append(Character.toUpperCase(tmp.charAt(0))).append(tmp.substring(1));
        }
        return tb.toString();
    }

    private static String buildPropertyName(String fieldName) {
        String[] fieldNameSections = fieldName.split("_");
        StringBuilder tb = new StringBuilder();
        for (int i = 0; i < fieldNameSections.length; i++) {
            String tableNameSection = fieldNameSections[i];
            String tmp = tableNameSection.toLowerCase();
            if (i == 0) {
                tb.append(tmp);
            } else {
                tb.append(Character.toUpperCase(tmp.charAt(0))).append(tmp.substring(1));
            }
        }

        if (tb.toString().equals("class")) {
            return "className";
        }
        return tb.toString();
    }

    public static void printInfo(ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        int numberOfColumns = rsmd.getColumnCount();
        for (int i = 1; i <= numberOfColumns; i++) {
            System.out.println("rsmd.getColumnName(i) = " + rsmd.getColumnName(i));
        }
//        while(rs.next()){
//            System.out.println(rs.getString("TABLE_NAME"));
//        }
//        System.out.println("numberOfColumns = " + numberOfColumns);
//        System.out.println("rsmd.getColumnName(1) = " + rsmd.getColumnName(1));
//        System.out.println("rsmd.getColumnLabel(1) = " + rsmd.getColumnLabel(1));
//        System.out.println("rsmd.getColumnType(1) = " + rsmd.getColumnType(1));
        System.out.println("rsmd.getColumnClassName(1) = " + rsmd.getColumnClassName(1));
    }

    static String getClassNameForJavaType(int javaType) {
        switch (javaType) {
            case -7:
            case 16:
                return "Boolean";
            case -6:
                return "Integer";
            case 5:
                return "Integer";
            case 4:
                return "Long";
            case -5:
                return "Long";

            case 2:
            case 3:
                return "java.math.BigDecimal";
            case 7:
                return "Float";
            case 6:
            case 8:
                return "Double";
            case -1:
            case 1:
            case 12:
                return "String";
            case -4:
            case -3:
            case -2:
                return "byte[]";
            case 91:
                return "java.sql.Date";
            case 92:
                return "java.sql.Time";
            case 93:
                return "java.sql.Timestamp";
        }
        return "Object";
    }

}