package son.roomshare.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class YesNoConverter  implements AttributeConverter<String, Boolean> {
    @Override
    public Boolean convertToDatabaseColumn(String attribute) {
        return "Y".equals(attribute);
    }

    @Override
    public String convertToEntityAttribute(Boolean dbData) {
        return dbData ? "Y" : "N";
    }
}
