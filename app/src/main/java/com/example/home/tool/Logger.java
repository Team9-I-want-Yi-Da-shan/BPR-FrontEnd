package com.example.home.tool;

import android.util.Log;

public class Logger {

   public static void debug(String tag, String message){
       int level = 1;
       StackTraceElement[] stacks = new Throwable().getStackTrace();
       String methodName = stacks[level].getMethodName();
       String className = stacks[level].getClassName();
       int lineNumber =  stacks[level].getLineNumber();
       Log.d(tag," Class:"+className+" Line:"+lineNumber+" Method:"+methodName+" "+message);
   }

}
