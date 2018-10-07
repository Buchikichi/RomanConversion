package to.kit.conversion.service;

import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import to.kit.conversion.enumeration.ConversionType;
import to.kit.util.NameUtils;

@Service
public class RomanConversionService {
	public String convert(String text, ConversionType... conversionTypes) {
		StringBuilder buff = new StringBuilder();
		Stream<String> stream = NameUtils.letters(text, true).parallel();

		for (ConversionType conversionType : conversionTypes) {
			stream = stream.map(conversionType.getMapper());
		}
		stream.forEachOrdered(buff::append);
		return buff.toString();
	}
}
