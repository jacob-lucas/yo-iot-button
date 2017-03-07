package com.jacoblucas.yoIotButton.model;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class IotButtonEventTest {

    @Test
    public void TestSingleClick() {
        IotButtonEvent iotButtonEvent = new IotButtonEvent();
        iotButtonEvent.setClickType("SINGLE");

        assertThat(iotButtonEvent.isSingleClick(), is(true));
        assertThat(iotButtonEvent.isDoubleClick(), is(false));
        assertThat(iotButtonEvent.isLongClick(), is(false));
    }

    @Test
    public void TestDoubleClick() {
        IotButtonEvent iotButtonEvent = new IotButtonEvent();
        iotButtonEvent.setClickType("DOUBLE");

        assertThat(iotButtonEvent.isSingleClick(), is(false));
        assertThat(iotButtonEvent.isDoubleClick(), is(true));
        assertThat(iotButtonEvent.isLongClick(), is(false));
    }

    @Test
    public void TestLongClickTest() {
        IotButtonEvent iotButtonEvent = new IotButtonEvent();
        iotButtonEvent.setClickType("LONG");

        assertThat(iotButtonEvent.isSingleClick(), is(false));
        assertThat(iotButtonEvent.isDoubleClick(), is(false));
        assertThat(iotButtonEvent.isLongClick(), is(true));
    }
}
