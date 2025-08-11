package org.nextory.ui_automation.mobile.base.Utils;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestNGListener implements IAnnotationTransformer {

    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        IRetryAnalyzer retryAnalyzer = getRetryAnalyzerUsingReflection(annotation);
        if (retryAnalyzer == null) {
            annotation.setRetryAnalyzer(RetryAnalyzer.class);
        }
    }

    // Use reflection to get the RetryAnalyzer dynamically
    private IRetryAnalyzer getRetryAnalyzerUsingReflection(ITestAnnotation annotation) {
        try {
            Method method = annotation.getClass().getMethod("getRetryAnalyzer");  // Find method via reflection
            return (IRetryAnalyzer) method.invoke(annotation);  // Call method dynamically
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            System.out.println("Method getRetryAnalyzer() not found in ITestAnnotation. Returning null.");
            return null;
        }
    }
}
