package models;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
@Builder
public class AdditionRequest {
    @JsonProperty("additional_info")
    String additionalInfo;
    @JsonProperty("additional_number")
    Integer additionalNumber;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        AdditionRequest additionRequest = (AdditionRequest) object;
        return Objects.equals(additionalInfo, additionRequest.additionalInfo) && Objects.equals(additionalNumber, additionRequest.additionalNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(additionalInfo, additionalNumber);
    }

    @Override
    public String toString() {
        return "AdditionRequest{" +
                "additional_info='" + additionalInfo + '\'' +
                ", additional_number=" + additionalNumber +
                '}';
    }
}
