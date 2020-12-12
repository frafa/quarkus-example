package it.ff.quarkus.hexagonal.tdd.histexrate.codegen.model;

import java.util.Objects;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.io.Serializable;
import javax.validation.constraints.*;
import io.swagger.annotations.*;

@ApiModel(description="Exchange rate")@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaResteasyServerCodegen", date = "2020-10-19T20:58:13.138012+02:00[Europe/Rome]")
public class ExchangeRate  implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private BigDecimal rate;
  private String currency;
  private LocalDate date;

  /**
   **/
  
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("rate")
  @NotNull
  public BigDecimal getRate() {
    return rate;
  }
  public void setRate(BigDecimal rate) {
    this.rate = rate;
  }

  /**
   **/
  
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("currency")
  @NotNull
  public String getCurrency() {
    return currency;
  }
  public void setCurrency(String currency) {
    this.currency = currency;
  }

  /**
   **/
  
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("date")
  @NotNull
  public LocalDate getDate() {
    return date;
  }
  public void setDate(LocalDate date) {
    this.date = date;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ExchangeRate exchangeRate = (ExchangeRate) o;
    return Objects.equals(rate, exchangeRate.rate) &&
        Objects.equals(currency, exchangeRate.currency) &&
        Objects.equals(date, exchangeRate.date);
  }

  @Override
  public int hashCode() {
    return Objects.hash(rate, currency, date);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ExchangeRate {\n");
    
    sb.append("    rate: ").append(toIndentedString(rate)).append("\n");
    sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

