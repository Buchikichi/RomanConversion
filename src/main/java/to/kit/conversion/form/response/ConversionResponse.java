package to.kit.conversion.form.response;

/**
 * レスポンス.
 * @author H.Sasai
 */
public final class ConversionResponse {
	private boolean result;
	private String kana;

	/**
	 * @return the result
	 */
	public boolean isResult() {
		return this.result;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(boolean result) {
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
