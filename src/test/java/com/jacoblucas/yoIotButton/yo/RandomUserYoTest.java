package com.jacoblucas.yoIotButton.yo;

import com.jacoblucas.yoIotButton.model.YoRequest;
import com.jacoblucas.yoIotButton.model.YoResponse;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.io.IOException;

import static com.jacoblucas.yoIotButton.yo.YoApi.YO_URL;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RandomUserYoTest extends YoApiOperationTest {
    private String json = "{\n" +
            "    \"contacts\": [\n" +
            "        {\n" +
            "            \"display_name\": \"Brian Schuster\",\n" +
            "            \"is_api_user\": false,\n" +
            "            \"is_subscribable\": false,\n" +
            "            \"last_seen_time\": 1488679844464329,\n" +
            "            \"status\": \" \",\n" +
            "            \"type\": \"user\",\n" +
            "            \"user_id\": \"55da87c64aa76200270eaaba\",\n" +
            "            \"username\": \"BIGDOGG50\",\n" +
            "            \"yo_count\": 1105\n" +
            "        },\n" +
            "        {\n" +
            "            \"display_name\": \"Geoff Pare\",\n" +
            "            \"is_api_user\": true,\n" +
            "            \"is_subscribable\": false,\n" +
            "            \"last_seen_time\": 1488753299809060,\n" +
            "            \"type\": \"user\",\n" +
            "            \"user_id\": \"57afd8f4a609d26ba8da724b\",\n" +
            "            \"username\": \"GEOFFPARE\",\n" +
            "            \"yo_count\": 434\n" +
            "        },\n" +
            "        {\n" +
            "            \"display_name\": \"Alex Dewald\",\n" +
            "            \"is_api_user\": false,\n" +
            "            \"is_subscribable\": false,\n" +
            "            \"last_seen_time\": 1488866217885107,\n" +
            "            \"status\": \" \",\n" +
            "            \"type\": \"user\",\n" +
            "            \"user_id\": \"56cf259e008e92002813f7b1\",\n" +
            "            \"username\": \"ALEXDEWALD\",\n" +
            "            \"yo_count\": 1768\n" +
            "        },\n" +
            "        {\n" +
            "            \"display_name\": \"Sam G.\",\n" +
            "            \"first_name\": \"Sam\",\n" +
            "            \"is_api_user\": false,\n" +
            "            \"is_subscribable\": false,\n" +
            "            \"last_name\": \"Goodwin\",\n" +
            "            \"last_seen_time\": 1488830495736765,\n" +
            "            \"name\": \"Sam Goodwin\",\n" +
            "            \"photo\": \"https://s3.amazonaws.com/yoapp-images/570093d220fa9c5fb65ce86b42057476.jpg\",\n" +
            "            \"type\": \"user\",\n" +
            "            \"user_id\": \"570093d220fa9c5fb65ce86b\",\n" +
            "            \"username\": \"SAMGOODWIN\",\n" +
            "            \"yo_count\": 358\n" +
            "        },\n" +
            "        {\n" +
            "            \"display_name\": \"JACOBLUCAS\",\n" +
            "            \"first_name\": \"Jacob\",\n" +
            "            \"is_api_user\": true,\n" +
            "            \"is_subscribable\": false,\n" +
            "            \"last_name\": \"Lucas\",\n" +
            "            \"last_seen_time\": 1488862829438129,\n" +
            "            \"name\": \"Jacob Lucas\",\n" +
            "            \"photo\": \"https://s3.amazonaws.com/yoapp-images/56fc2986f449500596d0d01853a2a781.jpg\",\n" +
            "            \"type\": \"user\",\n" +
            "            \"user_id\": \"56fc2986f449500596d0d018\",\n" +
            "            \"username\": \"JACOBLUCAS\",\n" +
            "            \"yo_count\": 568\n" +
            "        },\n" +
            "        {\n" +
            "            \"display_name\": \"Mikejansen\",\n" +
            "            \"is_api_user\": false,\n" +
            "            \"is_subscribable\": false,\n" +
            "            \"last_seen_time\": 1488681786228474,\n" +
            "            \"type\": \"user\",\n" +
            "            \"user_id\": \"57afd9d3a609d26ba8da7275\",\n" +
            "            \"username\": \"MIKEJANSEN\",\n" +
            "            \"yo_count\": 287\n" +
            "        },\n" +
            "        {\n" +
            "            \"display_name\": \"Mrignatz\",\n" +
            "            \"is_api_user\": false,\n" +
            "            \"is_subscribable\": false,\n" +
            "            \"last_seen_time\": 1488151767542984,\n" +
            "            \"type\": \"user\",\n" +
            "            \"user_id\": \"5841fc9c403bb57aa46867f8\",\n" +
            "            \"username\": \"MRIGNATZ\",\n" +
            "            \"yo_count\": 41\n" +
            "        },\n" +
            "        {\n" +
            "            \"display_name\": \"GEOFFIOTBUTTON\",\n" +
            "            \"is_api_user\": true,\n" +
            "            \"is_subscribable\": false,\n" +
            "            \"type\": \"user\",\n" +
            "            \"user_id\": \"5828fca5403bb5303eaf1cd2\",\n" +
            "            \"username\": \"GEOFFIOTBUTTON\",\n" +
            "            \"yo_count\": 74\n" +
            "        },\n" +
            "        {\n" +
            "            \"display_name\": \"Mike Jansen\",\n" +
            "            \"is_api_user\": false,\n" +
            "            \"is_pseudo\": true,\n" +
            "            \"is_subscribable\": false,\n" +
            "            \"last_seen_time\": 1471142359089796,\n" +
            "            \"type\": \"pseudo_user\",\n" +
            "            \"user_id\": \"56fc29def449500596d0d022\",\n" +
            "            \"username\": \"14083986865\",\n" +
            "            \"yo_count\": 33\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    @Test
    public void TestRandomUserYoReadsEnv() throws IOException {
        YoRequestSender mockYoRequestSender = mock(YoRequestSender.class);
        when(mockYoRequestSender.getContacts(YO_URL + "contacts/", ACCESS_TOKEN)).thenReturn(json);

        ArgumentCaptor<String> accessTokenArg = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<YoRequest> requestArg = ArgumentCaptor.forClass(YoRequest.class);

        RandomUserYo randomUserYo = new RandomUserYo();
        randomUserYo.yo(mockYoRequestSender);

        verify(mockYoRequestSender).getContacts(anyString(), accessTokenArg.capture());
        verify(mockYoRequestSender).postYoRequest(anyString(), requestArg.capture());

        String accessToken = accessTokenArg.getValue();
        YoRequest yoRequest = requestArg.getValue();

        assertThat(accessToken, notNullValue());
        assertThat(accessToken, is(ACCESS_TOKEN));
        assertThat(yoRequest.getApiKey(), is(API_KEY));
        assertThat(yoRequest.getUsername(), notNullValue());
        assertThat(yoRequest.getUsername().length(), greaterThan(0));
    }

    @Test
    public void TestRandomUserYoSuccess() throws IOException {
        String postJson = "{\n" +
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
        when(mockYoRequestSender.getContacts(YO_URL + "contacts/", ACCESS_TOKEN)).thenReturn(json);
        when(mockYoRequestSender.postYoRequest(anyString(), any(YoRequest.class))).thenReturn(postJson);

        RandomUserYo randomUserYo = new RandomUserYo();
        YoResponse response = randomUserYo.yo(mockYoRequestSender);

        assertThat(response, notNullValue());
        assertThat(response.isSuccess(), is(true));
    }

    @Test
    public void TestRandomUserYoFailure() throws IOException {
        YoRequestSender mockYoRequestSender = mock(YoRequestSender.class);
        when(mockYoRequestSender.getContacts(YO_URL + "contacts/", ACCESS_TOKEN)).thenReturn(json);
        when(mockYoRequestSender.postYoRequest(anyString(), any(YoRequest.class))).thenThrow(new IOException("Fail!"));

        RandomUserYo randomUserYo = new RandomUserYo();
        YoResponse response = randomUserYo.yo(mockYoRequestSender);

        assertThat(response, notNullValue());
        assertThat(response.isSuccess(), is(false));
    }

    @Test
    public void TestRandomUserYoOnZeroContacts() throws IOException {
        String emptyContactsJson = "{contacts: []}";

        YoRequestSender mockYoRequestSender = mock(YoRequestSender.class);
        when(mockYoRequestSender.getContacts(YO_URL + "contacts/", ACCESS_TOKEN)).thenReturn(emptyContactsJson);

        RandomUserYo randomUserYo = new RandomUserYo();
        YoResponse response = randomUserYo.yo(mockYoRequestSender);

        assertThat(response, notNullValue());
        assertThat(response.isSuccess(), is(false));
    }
}
