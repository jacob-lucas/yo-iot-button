package com.jacoblucas.yo;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class SendResponseTest {

    @Test
    public void TestConstruction() {
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

        SendResponse sr = new SendResponse(json);

        System.out.println(sr);

        assertThat(sr.getYoId(), is("58bc84ec403bb50490f63f84"));
        assertThat(sr.isSuccess(), is(true));
        assertThat(sr.getRecipient(), notNullValue());

        Recipient rec = sr.getRecipient();
        assertThat(rec.getUserId(), is("56fc2986f449500596d0d018"));
        assertThat(rec.getUsername(), is("JACOBLUCAS"));
        assertThat(rec.getYoCount(), is(507));
    }
}
