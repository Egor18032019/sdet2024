package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Objects;

@Setter
@Getter
@Builder
public class EntityRequest {
    AdditionRequest addition;
    @JsonProperty("important_numbers")
    Integer[] importantNumbers;
    String title;
    Boolean verified;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        EntityRequest that = (EntityRequest) object;
        return Objects.equals(addition, that.addition) && Arrays.equals(importantNumbers, that.importantNumbers) && Objects.equals(title, that.title) && Objects.equals(verified, that.verified);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(addition, title, verified);
        result = 31 * result + Arrays.hashCode(importantNumbers);
        return result;
    }

    @Override
    public String toString() {
        return "EntityRequest{" +
                "addition=" + addition +
                ", important_numbers=" + Arrays.toString(importantNumbers) +
                ", title='" + title + '\'' +
                ", verified=" + verified +
                '}';
    }
}
