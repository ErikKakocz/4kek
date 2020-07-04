package com.ShadowwolfIndustries.demo.model.converter;


import com.ShadowwolfIndustries.demo.model.enums.VoteType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class VoteTypeConverter implements AttributeConverter<VoteType, String> {

    @Override
    public String convertToDatabaseColumn(VoteType type) {
        if (type == null) {
            return null;
        }
        return type.getCode();
    }

    @Override
    public VoteType convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(VoteType.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
