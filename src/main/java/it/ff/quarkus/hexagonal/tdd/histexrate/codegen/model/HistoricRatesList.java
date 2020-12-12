package it.ff.quarkus.hexagonal.tdd.histexrate.codegen.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

@ApiModel(description="List of exchange rates")@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaResteasyServerCodegen", date = "2020-10-19T20:58:13.138012+02:00[Europe/Rome]")
public class HistoricRatesList  implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private java.util.List<ExchangeRate> historicRates = new ArrayList<>();

  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("historic_rates")
  public java.util.List<ExchangeRate> getHistoricRates() {
    return historicRates;
  }
  public void setHistoricRates(java.util.List<ExchangeRate> historicRates) {
    this.historicRates = historicRates;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    HistoricRatesList historicRatesList = (HistoricRatesList) o;
    return Objects.equals(historicRates, historicRatesList.historicRates);
  }

  @Override
  public int hashCode() {
    return Objects.hash(historicRates);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class HistoricRatesList {\n");
    
    sb.append("    historicRates: ").append(toIndentedString(historicRates)).append("\n");
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

