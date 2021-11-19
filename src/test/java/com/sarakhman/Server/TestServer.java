package com.sarakhman.Server;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestServer {
    String forTest = "GET /api/v1/test?hello=world&id=2 HTTP/1.1\n" +
            "Host: localhost:3000\n" +
            "Connection: keep-alive\n" +
            "Cache-Control: max-age=0\n" +
            "sec-ch-ua: Google Chrome;v=95,\n" +
            "Chromium;v=95, ;Not A Brand;v=99";

    @Test
    public void testPrepareStringForClientWorkCorrectly() {
        String expected = "/api/v1/test?hello=world&id=2";
        String expected2 = "HTTP/1.1";

        List<String> contentForTest = Server.prepareStringFromClient(forTest);
        String actual = contentForTest.get(0);
        String actual2 = contentForTest.get(1);
        assertEquals(expected, actual);
        assertEquals(expected2, actual2);

    }

    @Test
    public void testDivideMessageToSentenceWorkCorrectly() {
        List<String> forTexts = Server.divideMessageToSentence(forTest);
        String expected = "GET /api/v1/test?hello=world&id=2 HTTP/1.1";
        String expected2 = "Host: localhost:3000";
        String expected3 = "Connection: keep-alive";
        String expected4 = "Cache-Control: max-age=0";
        String expected5 = "sec-ch-ua: Google Chrome;v=95,";
        String expected6 = "Chromium;v=95, ;Not A Brand;v=99";
        assertEquals(expected, forTexts.get(0));
        assertEquals(expected2, forTexts.get(1));
        assertEquals(expected3, forTexts.get(2));
        assertEquals(expected4, forTexts.get(3));
        assertEquals(expected5, forTexts.get(4));
        assertEquals(expected6, forTexts.get(5));


    }

    @Test
    public void testDivideFirstSentenceFromMessageWorkCorrectly() {
        List<String> forTexts = Server.divideMessageToSentence(forTest);
        List<String> actual = Server.getFirstSentenceFromMessage(forTexts);
        List<String> expected = new ArrayList<>();
        expected.add("GET");
        expected.add("/api/v1/test?hello=world&id=2");
        expected.add("HTTP/1.1");
        assertEquals(expected.get(0), actual.get(0));
        assertEquals(expected.get(1), actual.get(1));
        assertEquals(expected.get(2), actual.get(2));


    }


}


