package cn.futuremove.adminportal.test;

import org.omg.CORBA.OBJECT_NOT_EXIST;

/**
 * Created by qurj on 15/6/16.
 */
public class MultiArgs {
    public static  void test(Object...args){

      // String.format(args);
    }

    public static void main(String[]args) {

        test("sdfdsf",1,"sdfsdf",new Object());
    }
}
