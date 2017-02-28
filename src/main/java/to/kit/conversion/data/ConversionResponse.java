package to.kit.conversion.data;

/**
 * レスポンス.
 * @author H.Sasai
 */
public final class ConversionResponse {
	private String result;
	private String kana;

	/**
	 * @return the result
	 */
	public String getResult() {
		return this.result;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}
	/**
	 * @return the kana
	 */
	public String getKana() {
		return this.kana;
	}
	/**
	 * @param kana the kana to set
	 */
	public void setKana(String kana) {
		this.kana = kana;
	}
}
