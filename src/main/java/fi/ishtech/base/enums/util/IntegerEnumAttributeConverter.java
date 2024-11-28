package fi.ishtech.base.enums.util;

import java.util.ArrayList;
import java.util.Arrays;

import jakarta.persistence.AttributeConverter;

import org.springframework.core.ResolvableType;

/**
 *
 * @author Muneer Ahmed Syed
 */
public interface IntegerEnumAttributeConverter<T extends Enum<T> & IntegerEnumDbValue>
		extends AttributeConverter<T, Integer> {

	@Override
	default Integer convertToDatabaseColumn(T t) {
		if (t == null) {
			return null;
		}
		return t.getValue();
	}

	@Override
	default T convertToEntityAttribute(Integer dbValue) {
		if (dbValue == null) {
			return null;
		}

		var type = ResolvableType.forClass(this.getClass()).as(IntegerEnumAttributeConverter.class).getGeneric(0)
				.getRawClass();

		@SuppressWarnings("unchecked")
		var enumType = (Class<T>) type;
		var enumValues = new ArrayList<T>(Arrays.asList(enumType.getEnumConstants()));

		return enumValues.stream().filter(v -> v.getValue() == dbValue).findFirst().orElse(null);
	}

}