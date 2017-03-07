package com.jacoblucas.yoIotButton.yo;

import com.jacoblucas.yoIotButton.model.YoRequest;
import com.jacoblucas.yoIotButton.model.YoResponse;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ContextAwareYoTest {

    private static final String USERNAME = "joeUsername";
    private static final String API_KEY = "myApiKey";

    @BeforeClass
    public static void setUp() throws Exception {
        Map<String, String> newEnv = new ConcurrentHashMap<>();
        newEnv.putAll(System.getenv());
        newEnv.put("apiKey", API_KEY);
        newEnv.put("username", USERNAME);
        setEnv(newEnv);
    }

    @AfterClass
    public static void tearDown() {
        Map<String, String> newEnv = new ConcurrentHashMap<>();
        newEnv.putAll(System.getenv());
        newEnv.remove("apiKey");
        newEnv.remove("username");
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

    @Test
    public void TestContextAwareYoReadsEnv() throws IOException {
        YoRequestSender mockYoRequestSender = mock(YoRequestSender.class);
        ArgumentCaptor<YoRequest> requestArg = ArgumentCaptor.forClass(YoRequest.class);
        ContextAwareYo contextAwareYo = new ContextAwareYo();
        contextAwareYo.yo(mockYoRequestSender);

        verify(mockYoRequestSender).postYoRequest(anyString(), requestArg.capture());

        YoRequest yoRequest = requestArg.getValue();
        assertThat(yoRequest, notNullValue());
        assertThat(yoRequest.getApiKey(), is(API_KEY));
        assertThat(yoRequest.getUsername(), is(USERNAME));
    }

    @Test
    public void TestContextAwareYoOnSuccess() throws IOException {
        String json = "{\n" +
                "  \"recipient\": {\n" +
                "    \"display_name\": \"Jacob L.\",\n" +
                "    \"first_name\": \"Jacob\",\n" +
                "    \"is_api_user\": false,\n" +
                "    \"is_subscribable\": false,\n" +
                "    \"last_name\": \"Lucas\",\n" +
                "    \"last_seen_time\": 1488749493338955,\n" +
                "    \"name\": \"Jacob Lucas\",\n" +
                "    \"photo\": \"https://s3.amazonaws.com/yoapp-images/56fc2986f449500596d0d01853a2a781.jpg\",\n" +
                "    \"type\": \"user\",\n" +
                "    \"user_id\": \"56fc2986f449500596d0d018\",\n" +
                "    \"username\": \"" + USERNAME + "\",\n" +
                "    \"yo_count\": 507\n" +
                "  },\n" +
                "  \"success\": true,\n" +
                "  \"yo_id\": \"58bc84ec403bb50490f63f84\"" +
                "}";

        YoRequestSender mockYoRequestSender = mock(YoRequestSender.class);
        when(mockYoRequestSender.postYoRequest(anyString(), any(YoRequest.class))).thenReturn(json);

        ContextAwareYo contextAwareYo = new ContextAwareYo();
        YoResponse resp = contextAwareYo.yo(mockYoRequestSender);

        assertThat(resp.isSuccess(), is(true));
        assertThat(resp.getRecipient().getUsername(), is(USERNAME));
    }

    @Test
    public void TestContextAwareYoOnFailure() throws IOException {
        YoRequestSender mockYoRequestSender = mock(YoRequestSender.class);
        when(mockYoRequestSender.postYoRequest(anyString(), any(YoRequest.class))).thenThrow(new IOException("fail!"));

        ContextAwareYo contextAwareYo = new ContextAwareYo();
        YoResponse resp = contextAwareYo.yo(mockYoRequestSender);

        assertThat(resp.isSuccess(), is(false));
    }
}
