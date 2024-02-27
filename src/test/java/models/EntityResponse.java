package models;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Objects;


@Setter
@Getter
public class EntityResponse {
    AdditionResponse addition;
    Integer id;
    @JsonAlias({"important_numbers","importantNumbers"})
    Integer[] importantNumbers;
    String title;
    Boolean verified;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        EntityResponse that = (EntityResponse) object;
        return Objects.equals(addition, that.addition) && Objects.equals(id, that.id) && Arrays.equals(importantNumbers, that.importantNumbers) && Objects.equals(title, that.title) && Objects.equals(verified, that.verified);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(addition, id, title, verified);
        result = 31 * result + Arrays.hashCode(importantNumbers);
        return result;
    }

    @Override
    public String toString() {
        return "EntityResponse{" +
                "addition=" + addition +
                ", id=" + id +
                ", important_numbers=" + Arrays.toString(importantNumbers) +
                ", title='" + title + '\'' +
                ", verified=" + verified +
                '}';
    }
}
