package it.ff.quarkus.hexagonal.tdd.histexrate.codegen.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Objects;

@ApiModel(description="Object returned from convert_to operation")@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaResteasyServerCodegen", date = "2020-10-19T20:58:13.138012+02:00[Europe/Rome]")
public class ConvertedAmount  implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private String currency;
  private java.util.List<ExchangeRate> exchangeRates = new ArrayList<>();
  private BigDecimal amount;

  /**
   * Currency of converted amount 
   **/
  
  @ApiModelProperty(required = true, value = "Currency of converted amount ")
  @JsonProperty("currency")
  @NotNull
  public String getCurrency() {
    return currency;
  }
  public void setCurrency(String currency) {
    this.currency = currency;
  }

  /**
   * List of exchange rates used to convert amount. The number of exchange rates is more than one if the base currency or the destination currency are not EUR.
   **/
  
  @ApiModelProperty(required = true, value = "List of exchange rates used to convert amount. The number of exchange rates is more than one if the base currency or the destination currency are not EUR.")
  @JsonProperty("exchange_rates")
  @NotNull
  public java.util.List<ExchangeRate> getExchangeRates() {
    return exchangeRates;
  }
  public void setExchangeRates(java.util.List<ExchangeRate> exchangeRates) {
    this.exchangeRates = exchangeRates;
  }

  /**
   * Converted amount
   **/
  
  @ApiModelProperty(required = true, value = "Converted amount")
  @JsonProperty("amount")
  @NotNull
  public BigDecimal getAmount() {
    return amount;
  }
  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConvertedAmount convertedAmount = (ConvertedAmount) o;
    return Objects.equals(currency, convertedAmount.currency) &&
        Objects.equals(exchangeRates, convertedAmount.exchangeRates) &&
        Objects.equals(amount, convertedAmount.amount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(currency, exchangeRates, amount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConvertedAmount {\n");
    
    sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
    sb.append("    exchangeRates: ").append(toIndentedString(exchangeRates)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
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

