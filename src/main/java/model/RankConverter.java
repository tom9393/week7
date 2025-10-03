package model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Map;

@Converter(autoApply = false)
public class RankConverter implements AttributeConverter<String, Integer> {

    private static final Map<Integer, String> CODE_TO_NAME = Map.of(
            1, "White Belt",
            2, "Yellow Belt",
            3, "Orange Belt",
            4, "Green Belt",
            5, "Brown Belt",
            6, "Black Belt"
    );

    private static final Map<String, Integer> NAME_TO_CODE = Map.of(
            "White Belt", 1,
            "Yellow Belt", 2,
            "Orange Belt", 3,
            "Green Belt", 4,
            "Brown Belt", 5,
            "Black Belt", 6
    );

    @Override
    public Integer convertToDatabaseColumn(String rankName) {
        if (rankName == null) return null;
        return NAME_TO_CODE.getOrDefault(rankName, 0);
    }

    @Override
    public String convertToEntityAttribute(Integer code) {
        if (code == null) return null;
        return CODE_TO_NAME.getOrDefault(code, "Unknown");
    }
}
