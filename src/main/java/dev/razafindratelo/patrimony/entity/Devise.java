package dev.razafindratelo.patrimony.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
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
