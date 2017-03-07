package com.jacoblucas.yoIotButton.yo;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class YoApiOperationTest {
    static final String USERNAME = "joeUsername";
    static final String API_KEY = "myApiKey";
    static final String ACCESS_TOKEN = "myAccessToken";

    @BeforeClass
    public static void setUp() throws Exception {
        Map<String, String> newEnv = new ConcurrentHashMap<>();
        newEnv.putAll(System.getenv());
        newEnv.put("apiKey", API_KEY);
        newEnv.put("username", USERNAME);
        newEnv.put("accessToken", ACCESS_TOKEN);
        setEnv(newEnv);
    }

    @AfterClass
    public static void tearDown() {
        Map<String, String> newEnv = new ConcurrentHashMap<>();
        newEnv.putAll(System.getenv());
        newEnv.remove("apiKey");
        newEnv.remove("username");
        newEnv.remove("accessToken");
        setEnv(newEnv);
    }

    // http://stackoverflow.com/questions/318239/how-do-i-set-environment-variables-from-java
    private static void setEnv(Map<String, String> newenv) {
        try
        {
            Class<?> processEnvironmentClass = Class.forName("java.lang.ProcessEnvironment");
            Field theEnvironmentField = processEnvironmentClass.getDeclaredField("theEnvironment");
            theEnvironmentField.setAccessible(true);
            Map<String, String> env = (Map<String, String>) theEnvironmentField.get(null);
            env.putAll(newenv);
            Field theCaseInsensitiveEnvironmentField = processEnvironmentClass.getDeclaredField("theCaseInsensitiveEnvironment");
            theCaseInsensitiveEnvironmentField.setAccessible(true);
            Map<String, String> cienv = (Map<String, String>)     theCaseInsensitiveEnvironmentField.get(null);
            cienv.putAll(newenv);
        }
        catch (NoSuchFieldException e)
        {
            try {
                Class[] classes = Collections.class.getDeclaredClasses();
                Map<String, String> env = System.getenv();
                for(Class cl : classes) {
                    if("java.util.Collections$UnmodifiableMap".equals(cl.getName())) {
                        Field field = cl.getDeclaredField("m");
                        field.setAccessible(true);
                        Object obj = field.get(env);
                        Map<String, String> map = (Map<String, String>) obj;
                        map.clear();
                        map.putAll(newenv);
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
