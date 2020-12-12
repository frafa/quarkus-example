package it.ff.quarkus.hexagonal.tdd.histexrate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import it.ff.quarkus.hexagonal.tdd.histexrate.codegen.model.ExchangeRate;
import it.ff.quarkus.hexagonal.tdd.histexrate.codeimpl.application.port.HistoricalExchangeRatesUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@QuarkusTest
public class HistoricalExchangeRatesWebTest {

    @InjectMock
    private HistoricalExchangeRatesUseCase historicalExchangeRatesUseCase;

    @Inject
    private ObjectMapper objectMapper;

    @BeforeEach
    private void setup() {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Test
    public void retrieve_should_get_cambio_storico_when_valid_request() {
        List<ExchangeRate> l = new ArrayList<>();
        ExchangeRate c = new ExchangeRate();
        c.setRate(BigDecimal.valueOf(0.82));
        c.setCurrency("TRY");
        c.setDate(LocalDate.parse("2020-10-01"));
        l.add(c);
        when(historicalExchangeRatesUseCase.getHistoricExchangeRate(null, null, "TRY", null)).thenReturn(l);

        Assertions.assertTrue(
                given()
                        .accept(ContentType.JSON)
                        .when()
                        .get("/historic-rate?currency={currency}", "TRY")
                        .then()
                        .statusCode(200)
                        .extract().jsonPath()
                        .getList("historicRates.currency")
                        .stream()
                        .anyMatch(s -> s.equals("TRY")));
    }

    @Test
    public void insert_should_insert_cambio_storico_when_valid_request() throws Exception {
        ExchangeRate c = new ExchangeRate();
        c.setRate(BigDecimal.valueOf(0.77));
        c.setCurrency("USD");
        c.setDate(LocalDate.parse("2020-10-05"));
        when(historicalExchangeRatesUseCase.insertExchangeRate(any())).thenReturn(c);

        JsonPath p = given()
                .contentType(ContentType.JSON)
                .body(objectMapper.writeValueAsString(c))
                .post("/historic-rate")
                .then()
                .assertThat()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .extract().jsonPath();

        Assertions.assertEquals(p.getObject("$", ExchangeRate.class), c);
    }

//    @Test
//    public void update_should_update_cambio_storico_when_valid_request() throws Exception {
//        ExchangeRate c = new ExchangeRate().rate(BigDecimal.valueOf(0.77)).currency("USD").date(LocalDate.parse("2020-10-05"));
//        when(HistoricalExchangeRatesUseCase.updateExchangeRate(any())).thenReturn(1);
//
//        mockMvc.perform(put("/historic-rate")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(c))
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNoContent());
//    }
//
//    @Test
//    public void update_should_return_404_when_not_valid_request() throws Exception {
//        ExchangeRate c = new ExchangeRate().rate(BigDecimal.valueOf(0.77)).currency("USD").date(LocalDate.parse("2020-10-05"));
//        when(HistoricalExchangeRatesUseCase.updateExchangeRate(any())).thenReturn(0);
//
//        mockMvc.perform(put("/historic-rate")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(c))
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    public void delete_should_delete_cambio_storico_when_valid_request() throws Exception {
//        doNothing().when(HistoricalExchangeRatesUseCase).deleteExchangeRate(anyString(), any(LocalDate.class));
//
//        mockMvc.perform(delete("/historic-rate?currency={currency}&date={date}", "USD", "2020-01-01")
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNoContent());
//    }
//
//    @Test
//    public void delete_should_return_404_when_not_valid_request() throws Exception {
//        doThrow(EmptyResultDataAccessException.class).when(HistoricalExchangeRatesUseCase).deleteExchangeRate(anyString(), any(LocalDate.class));
//
//        mockMvc.perform(delete("/historic-rate?currency={currency}&date={date}", "USD", "2020-01-01")
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound());
//    }
}
