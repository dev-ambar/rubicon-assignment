package com.learningpath.rubiconassignment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.learningpath.rubiconassignment.model.WaterOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class WaterOrderTest {
    @Autowired
    MockMvc mvc ;

    @Test
    public  void testWaterOrderShouldNotCreateInPast() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        WaterOrder wo = new WaterOrder();
        wo.setFarmId("frm1");
        wo.setStatus("requested");
        wo.setDuration(2);
        wo.setStartDateTime("16-01-2022 22:25");

        String requestJson = ow.writeValueAsString(wo);

        mvc.perform(post("/waterorderapi/orders").contentType(MediaType.APPLICATION_JSON).content(requestJson)).andExpect(status().isExpectationFailed());
    }

    @Test
    public  void testWaterOrderShouldCreateInCurrentANDFutureDate() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        WaterOrder wo = new WaterOrder();
        wo.setFarmId("frm1");
        wo.setStatus("requested");
        wo.setDuration(2);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String currentDateTime = dtf.format(LocalDateTime.now());
        wo.setStartDateTime(currentDateTime);

        String requestJson = ow.writeValueAsString(wo);

        mvc.perform(post("/waterorderapi/orders").contentType(MediaType.APPLICATION_JSON).content(requestJson)).andExpect(status().isCreated());
    }
}
