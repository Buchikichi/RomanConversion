package to.kit.conversion.form.request;

import to.kit.conversion.enumeration.ConversionType;

/**
 * フォーム.
 * @author H.Sasai
 */
public final class ConversionForm {
	private String roman;
	private ConversionType[] conversionTypes;

	/**
	 * @return the roman
	 */
	public String getRoman() {
		return this.roman;
	}
	/**
	 * @param roman the roman to set
	 */
	public void setRoman(String roman) {
		this.roman = roman;
	}
	/**
	 * @return the conversion
	 */
	public ConversionType[] getConversionTypes() {
		return this.conversionTypes;
	}
	/**
	 * @param value the conversion to set
	 */
	public void setConversionTypes(ConversionType[] value) {
		this.conversionTypes = value;
	}
}
