package model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Map;

@Converter(autoApply = false)
public class AttendanceStatusConverter implements AttributeConverter<String, String> {

    private static final Map<String, String> DB_TO_NAME = Map.of(
            "P", "Present",
            "A", "Absent",
            "L", "Late"
    );

    private static final Map<String, String> NAME_TO_DB = Map.of(
            "Present", "P",
            "Absent", "A",
            "Late", "L"
    );

    @Override
    public String convertToDatabaseColumn(String status) {
        if (status == null) return null;
        return NAME_TO_DB.getOrDefault(status, status);
    }

    @Override
    public String convertToEntityAttribute(String dbCode) {
        if (dbCode == null) return null;
        return DB_TO_NAME.getOrDefault(dbCode, dbCode);
    }
}
