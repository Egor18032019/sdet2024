package models;

import lombok.*;

@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EntityResponseArray {
    EntityResponse[] entity;
}
