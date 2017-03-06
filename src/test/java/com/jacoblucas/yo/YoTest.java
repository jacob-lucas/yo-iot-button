package com.jacoblucas.yo;

import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class YoTest {
    @Test
    public void TestSendYo() throws IOException {
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
                "    \"username\": \"JACOBLUCAS\",\n" +
                "    \"yo_count\": 507\n" +
                "  },\n" +
                "  \"success\": true,\n" +
                "  \"yo_id\": \"58bc84ec403bb50490f63f84\"" +
                "}";

        final YoRequestSender yrs = mock(YoRequestSender.class);
        final YoRequest req = YoRequest.builder().apiKey("apiKey").username("JACOBLUCAS").build();
        when(yrs.post(Yo.YO_URL, req)).thenReturn(json);

        Yo testYo = new Yo() {
            @Override
            YoResponse yo() throws IOException {
                return sendYo(req, yrs);
            }
        };

        YoResponse resp = testYo.yo();

        assertThat(resp, notNullValue());
        assertThat(resp.getYoId(), is("58bc84ec403bb50490f63f84"));
        assertThat(resp.isSuccess(), is(true));
    }
}
