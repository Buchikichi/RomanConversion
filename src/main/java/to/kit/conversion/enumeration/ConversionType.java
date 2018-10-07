package to.kit.conversion.enumeration;

import java.util.function.Function;

import to.kit.util.NameUtils;

public enum ConversionType {
	HalfKana(NameUtils::toHalfKana),
	Katakana(NameUtils::toKatakana),
	Hiragana(NameUtils::toHiragana),
	RomanConversion(NameUtils::convertRoman),
	;
	private Function<String, String> mapper;

	ConversionType(Function<String, String> mapper){
		this.mapper = mapper;
	}

	public Function<String, String> getMapper() {
		return this.mapper;
	}
}
