package vip.codehome.springboot.tutorials.util;

import org.springframework.util.StringUtils;
import vip.codehome.springboot.tutorials.common.BusinessException;

import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExUtil {
    public static void throwBusException(String msg){
        throw new BusinessException(msg);
    }
    public static String getSimpleMessage(Throwable e) {
        return null == e ? "null" : e.getMessage();
    }
    public static RuntimeException wrapRuntime(Throwable throwable) {
        return throwable instanceof RuntimeException ? (RuntimeException)throwable : new RuntimeException(throwable);
    }

    public static void wrapAndThrow(Throwable throwable) {
        if (throwable instanceof RuntimeException) {
            throw (RuntimeException)throwable;
        } else if (throwable instanceof Error) {
            throw (Error)throwable;
        } else {
            throw new UndeclaredThrowableException(throwable);
        }
    }

    public static Throwable unwrap(Throwable wrapped) {
        Throwable unwrapped = wrapped;

        while(true) {
            while(!(unwrapped instanceof InvocationTargetException)) {
                if (!(unwrapped instanceof UndeclaredThrowableException)) {
                    return unwrapped;
                }

                unwrapped = ((UndeclaredThrowableException)unwrapped).getUndeclaredThrowable();
            }

            unwrapped = ((InvocationTargetException)unwrapped).getTargetException();
        }
    }

    public static StackTraceElement[] getStackElements() {
        return Thread.currentThread().getStackTrace();
    }

    public static StackTraceElement getStackElement(int i) {
        return getStackElements()[i];
    }

    public static StackTraceElement getRootStackElement() {
        StackTraceElement[] stackElements = getStackElements();
        return stackElements[stackElements.length - 1];
    }



    public static boolean isCausedBy(Throwable throwable, Class<? extends Exception>... causeClasses) {
        return null != getCausedBy(throwable, causeClasses);
    }

    public static Throwable getCausedBy(Throwable throwable, Class<? extends Exception>... causeClasses) {
        for(Throwable cause = throwable; cause != null; cause = cause.getCause()) {
            Class[] var3 = causeClasses;
            int var4 = causeClasses.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                Class<? extends Exception> causeClass = var3[var5];
                if (causeClass.isInstance(cause)) {
                    return cause;
                }
            }
        }

        return null;
    }


    public static List<Throwable> getThrowableList(Throwable throwable) {
        ArrayList list;
        for(list = new ArrayList(); throwable != null && !list.contains(throwable); throwable = throwable.getCause()) {
            list.add(throwable);
        }

        return list;
    }

    public static Throwable getRootCause(Throwable throwable) {
        List<Throwable> list = getThrowableList(throwable);
        return list.size() < 1 ? null : (Throwable)list.get(list.size() - 1);
    }

}
