package models;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.*;

import java.util.Objects;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdditionResponse {
    @JsonAlias({"additional_info","additionalInfo"})
    String additionalInfo;
    @JsonAlias({"additional_number","additionalNumber"})
    Integer additionalNumber;
    Integer id;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        AdditionResponse that = (AdditionResponse) object;
        return Objects.equals(additionalInfo, that.additionalInfo) && Objects.equals(additionalNumber, that.additionalNumber) && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(additionalInfo, additionalNumber, id);
    }

    @Override
    public String toString() {
        return "AdditionResponse{" +
                "additional_info='" + additionalInfo + '\'' +
                ", additional_number=" + additionalNumber +
                ", id=" + id +
                '}';
    }
}
