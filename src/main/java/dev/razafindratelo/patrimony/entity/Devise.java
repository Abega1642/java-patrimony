package dev.razafindratelo.patrimony.entity;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Devise {
    private DeviseType name;
    private String description;

    public static Devise ariary() {
        return new Devise(DeviseType.ARIARY, "Malagasy devise");
    }

    public static Devise usd() {
        return new Devise(DeviseType.USD, "USA devise");
    }

    public static Devise euro() {
        return new Devise(DeviseType.EURO, "European devise");
    }


    @Override
    public String toString() {
        return "Devise { " +
               "\n \t name= " + name +
               "\n \t description=" + description +
                "}";
    }
}
