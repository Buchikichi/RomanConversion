package to.kit.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * 名前に関するユーティリティークラス.
 * @author Hidetaka Sasai
 */
public final class NameUtils {
	private static final String HALF_KANA = "ｧ ｱ ｨ ｲ ｩ ｳ ｪ ｴ ｫ ｵ ｶ ｶﾞｷ ｷﾞｸ ｸﾞｹ ｹﾞｺ ｺﾞｻ ｻﾞｼ ｼﾞｽ ｽﾞｾ ｾﾞｿ ｿﾞﾀ ﾀﾞﾁ ﾁﾞｯ ﾂ ﾂﾞﾃ ﾃﾞﾄ ﾄﾞﾅ ﾆ ﾇ ﾈ ﾉ ﾊ ﾊﾞﾊﾟﾋ ﾋﾞﾋﾟﾌ ﾌﾞﾌﾟﾍ ﾍﾞﾍﾟﾎ ﾎﾞﾎﾟﾏ ﾐ ﾑ ﾒ ﾓ ｬ ﾔ ｭ ﾕ ｮ ﾖ ﾗ ﾘ ﾙ ﾚ ﾛ * ﾜ ｲ ｴ ｦ ﾝ ｳﾞｶ ｹ ";
	private static final String[][] CONSONANT_LIST = {
		{"l", "ぁ", "ぃ", "ぅ", "ぇ", "ぉ"},
		{"x", "ぁ", "ぃ", "ぅ", "ぇ", "ぉ"},
		{"a", "あ", "い", "う", "え", "お"},
		{"â", "あ", "い", "う", "え", "お"},
		{"c", "か", "し", "く", "せ", "こ"},
		{"k", "か", "き", "く", "け", "こ"},
		{"g", "が", "ぎ", "ぐ", "げ", "ご"},
		{"s", "さ", "し", "す", "せ", "そ"},
		{"z", "ざ", "じ", "ず", "ぜ", "ぞ"},
		{"t", "た", "ち", "つ", "て", "と"},
		{"d", "だ", "ぢ", "づ", "で", "ど"},
		{"n", "な", "に", "ぬ", "ね", "の"},
		{"h", "は", "ひ", "ふ", "へ", "ほ"},
		{"b", "ば", "び", "ぶ", "べ", "ぼ"},
		{"p", "ぱ", "ぴ", "ぷ", "ぺ", "ぽ"},
		{"m", "ま", "み", "む", "め", "も"},
		{"ly", "ゃ", "ぃ", "ゅ", "ぇ", "ょ"},
		{"xy", "ゃ", "ぃ", "ゅ", "ぇ", "ょ"},
		{"y", "や", "ゐ", "ゆ", "ゑ", "よ"},
		{"r", "ら", "り", "る", "れ", "ろ"},
		{"w", "わ", "ゐ", "う", "ゑ", "を"},

		{"lk", "ゕ", "", "", "ゖ", ""},
		{"lt", "", "", "っ", "", ""},
		{"lw", "ゎ", "", "", "", ""},

		{"sh", "しゃ", "し", "しゅ", "しぇ", "しょ"},
		{"j",  "じゃ", "じ", "じゅ", "じぇ", "じょ"},
		{"ch", "ちゃ", "ち", "ちゅ", "ちぇ", "ちょ"},

		{"wh", "うぁ", "うぃ", "う", "うぇ", "うぉ"},
		{"q",  "くぁ", "くぃ", "く", "くぇ", "くぉ"},
		{"ts", "つぁ", "つぃ", "つ", "つぇ", "つぉ"},
		{"f",  "ふぁ", "ふぃ", "ふ", "ふぇ", "ふぉ"},
		{"v",  "ゔぁ", "ゔぃ", "ゔ", "ゔぇ", "ゔぉ"},

		{"th", "てゃ", "てぃ", "てゅ", "てぇ", "てょ"},
		{"dh", "でゃ", "でぃ", "でゅ", "でぇ", "でょ"},

		{"by", "びゃ", "びぃ", "びゅ", "びぇ", "びょ"},
		{"cy", "ちゃ", "ちぃ", "ちゅ", "ちぇ", "ちょ"},
		{"dy", "ぢゃ", "ぢぃ", "ぢゅ", "ぢぇ", "ぢょ"},
		{"gy", "ぎゃ", "ぎぃ", "ぎゅ", "ぎぇ", "ぎょ"},
		{"hy", "ひゃ", "ひぃ", "ひゅ", "ひぇ", "ひょ"},
		{"jy", "じゃ", "じぃ", "じゅ", "じぇ", "じょ"},
		{"ky", "きゃ", "きぃ", "きゅ", "きぇ", "きょ"},
		{"my", "みゃ", "みぃ", "みゅ", "みぇ", "みょ"},
		{"ny", "にゃ", "にぃ", "にゅ", "にぇ", "にょ"},
		{"py", "ぴゃ", "ぴぃ", "ぴゅ", "ぴぇ", "ぴょ"},
		{"ry", "りゃ", "りぃ", "りゅ", "りぇ", "りょ"},
		{"sy", "しゃ", "しぃ", "しゅ", "しぇ", "しょ"},
		{"ty", "ちゃ", "ちぃ", "ちゅ", "ちぇ", "ちょ"},
		{"zy", "じゃ", "じぃ", "じゅ", "じぇ", "じょ"},

		{"fy", "ふゃ", "ふぃ", "ふゅ", "ふぇ", "ふょ"},
		{"qy", "くゃ", "くぃ", "くゅ", "くぇ", "くょ"},
		{"vy", "ゔゃ", "ゔぃ", "ゔゅ", "ゔぇ", "ゔょ"},
	};
	/** Mapping. */
	private static final Map<String, String[]> CONSONANT_MAP = new HashMap<>();
	private static final String SUUJI = "〇一二三四五六七八九";

	private NameUtils() {
		// nop
	}

	static {
		// consonant:子音, vowel:母音
		for (String[] row : CONSONANT_LIST) {
			String key = row[0];
			String[] value = Arrays.copyOfRange(row, 1, row.length);

			CONSONANT_MAP.put(key, value);
		}
	}

	private static UnaryOperator<Character> toFull = ch -> {
		char c = ch.charValue();

		if ('!' <= c && c <= '~') {
			return Character.valueOf((char) ('！' + (c - '!')));
		}
		return ch;
	};

	static char convertMacron(final char ch) {
		char result = 0;

		if ('Â' == ch) {
			result = 'A';
		} else if ('Ê' == ch) {
			result = 'E';
		} else if ('Î' == ch) {
			result = 'I';
		} else if ('Ô' == ch) {
			result = 'O';
		} else if ('Û' == ch) {
			result = 'U';
		} else if ('â' == ch) {
			result = 'a';
		} else if ('ê' == ch) {
			result = 'e';
		} else if ('î' == ch) {
			result = 'i';
		} else if ('ô' == ch) {
			result = 'o';
		} else if ('û' == ch) {
			result = 'u';
		}
		return result;
	}

	public static Stream<String> letters(final String text, final boolean hasRoman) {
		if (text == null) {
			return null;
		}
		final int len = text.length();
		final Iterator<String> it = new Iterator<String>() {
			private int ix = 0;
			private String reserve = null;

			@Override
			public boolean hasNext() {
				return this.ix < len;
			}

			@Override
			public String next() {
				if (len < this.ix) {
					throw new NoSuchElementException();
				}
				if (this.reserve != null) {
					String result = this.reserve;

					this.reserve = null;
					return String.valueOf(result);
				}
				char first = text.charAt(this.ix++);

				if (len <= this.ix) {
					return String.valueOf(first);
				}
				if ('ｶ' <= first && first <= 'ﾎ' && !('ﾅ' <= first && first <= 'ﾉ')) {
					char second = text.charAt(this.ix);

					if (second == 'ﾞ' || second == 'ﾟ') {
						this.ix++;
						return String.valueOf(first) + String.valueOf(second);
					}
					return String.valueOf(first);
				}
				if (!hasRoman) {
					return String.valueOf(first);
				}
				// ローマ字を含む場合
				char lowerFirst = Character.toLowerCase(first);
				char macron = convertMacron(first);

				if (macron != 0) {
					this.reserve = "ー";
					first = macron;
					lowerFirst = Character.toLowerCase(macron);
				}
				if (lowerFirst <= 'a' || 'z' < lowerFirst || lowerFirst == 'e' || lowerFirst == 'i' || lowerFirst == 'o'
						|| lowerFirst == 'u' || len < this.ix + 1) {
					return String.valueOf(first);
				}
				//
				char second = text.charAt(this.ix);
				char lowerSecond = Character.toLowerCase(second);
				char secondMacron = convertMacron(second);

				if (secondMacron != 0) {
					this.reserve = "ー";
					second = secondMacron;
					lowerSecond = Character.toLowerCase(secondMacron);
				}
				if (lowerSecond < 'a' || 'z' < lowerSecond || len < this.ix + 1) {
					return String.valueOf(first);
				}
				if (lowerSecond == 'a' || lowerSecond == 'e' || lowerSecond == 'i' || lowerSecond == 'o'
						|| lowerSecond == 'u') {
					this.ix++;
					return String.valueOf(first) + String.valueOf(second);
				}
				if (('n' == lowerFirst && 'y' != lowerSecond)
						|| ('m' == lowerFirst && ('b' == lowerSecond || 'm' == lowerSecond || 'p' == lowerSecond))) {
					return "n";
				}
				if (lowerFirst == lowerSecond || (lowerFirst == 't' && lowerSecond == 'c')) {
					return "ltu";
				}
				//
				char third = text.charAt(this.ix + 1);
				char lowerThird = Character.toLowerCase(third);
				char thirdMacron = convertMacron(third);

				if (thirdMacron != 0) {
					this.reserve = "ー";
					third = thirdMacron;
					lowerThird = Character.toLowerCase(thirdMacron);
				}
				if (lowerThird == 'a' || lowerThird == 'e' || lowerThird == 'i' || lowerThird == 'o'
						|| lowerThird == 'u') {
					this.ix += 2;
					return String.valueOf(first) + String.valueOf(second) + String.valueOf(third);
				}
				return String.valueOf(first);
			}
		};
		return StreamSupport.stream(Spliterators.spliteratorUnknownSize(it, Spliterator.ORDERED | Spliterator.NONNULL),
				false);
	}

	public static Stream<String> letters(final String text) {
		return letters(text, false);
	}

	/**
	 * 全角に変換.
	 * @param str 文字列
	 * @return 変換後の文字列
	 */
	public static String toFull(final String str) {
		StringBuilder buff = new StringBuilder();

		for (final char c : str.toCharArray()) {
			Character ch = Character.valueOf(c);

			ch = toFull.apply(ch);
			buff.append(ch);
		}
		return buff.toString();
	}

	/**
	 * 漢数字に変換.<br/>
	 * 1桁のみ対応.
	 * @param str 文字列
	 * @return 変換後の文字列
	 */
	public static String toKansuuji(final String str) {
		StringBuilder buff = new StringBuilder();

		for (final char c : str.toCharArray()) {
			if ('0' <= c && c <= '9') {
				int ix = c - '0';
				buff.append(SUUJI.charAt(ix));
				continue;
			}
			buff.append(c);
		}
		return buff.toString();
	}

	/**
	 * ローマ字をひらがなに変換.
	 * @param str ローマ字を含む文字列
	 * @return 変換後の文字列
	 */
	public static String convertRoman(final String str) {
		String lower = str.toLowerCase();
		int len = str.length();
		char vowel = lower.charAt(len - 1);
		String prefix = "";
		int ix;
		String key;

		if ('a' == vowel) {
			ix = 0;
		} else if ('i' == vowel) {
			ix = 1;
		} else if ('u' == vowel) {
			ix = 2;
		} else if ('e' == vowel) {
			ix = 3;
		} else if ('o' == vowel) {
			ix = 4;
		} else if ('n' == vowel) {
			return "ん";
		} else {
			return str;
		}
		if (len == 1) {
			key = "a";
		} else if (len == 2) {
			key = String.valueOf(lower.charAt(0));
		} else {
			key = lower.substring(0, len - 1);
		}
		String[] row = CONSONANT_MAP.get(key);

		if (row == null) {
			return str;
		}
		return prefix + row[ix];
	}

	/**
	 * ひらがなに変換.<br/>
	 * ﾊﾝｶｸｶﾅおよびカタカナをひらがなに変換
	 * @param str 文字列
	 * @return 変換後の文字列
	 */
	public static String toHiragana(final String str) {
		char c = str.charAt(0);

		if ('ァ' <= c && c <= 'ヶ') {
			// 全角カタカナ
			return String.valueOf((char) ('ぁ' + c - 'ァ'));
		} 
		if ('ｦ' <= c && c <= 'ﾝ') {
			// ﾊﾝｶｸｶﾅ
			return String.valueOf((char) ('ぁ' + HALF_KANA.indexOf(str) / 2));
		}
		if (c == 'ｰ') {
			return "ー";
		}
		if (c == 'ﾞ') {
			return "゛";
		}
		if (c == 'ﾟ') {
			return "゜";
		}
		return str;
	}

	/**
	 * カタカナに変換.<br/>
	 * ﾊﾝｶｸｶﾅおよびひらがなをカタカナに変換
	 * @param str 文字列
	 * @return 変換後の文字列
	 */
	public static String toKatakana(final String str) {
		char c = str.charAt(0);

		if ('ぁ' <= c && c <= 'ゖ') {
			// 全角ひらがな
			return String.valueOf((char) ('ァ' + c - 'ぁ'));
		} 
		if ('ｦ' <= c && c <= 'ﾝ') {
			// ﾊﾝｶｸｶﾅ
			return String.valueOf((char) ('ァ' + HALF_KANA.indexOf(str) / 2));
		}
		if (c == 'ｰ') {
			return "ー";
		}
		if (c == 'ﾞ') {
			return "゛";
		}
		if (c == 'ﾟ') {
			return "゜";
		}
		return str;
	}

	/**
	 * ﾊﾝｶｸｶﾅに変換.<br/>
	 * ひらがなおよびカタカナをﾊﾝｶｸｶﾅに変換
	 * @param str 文字列
	 * @return 変換後の文字列
	 */
	public static String toHalfKana(final String str) {
		char c = str.charAt(0);

		if ('ぁ' <= c && c <= 'ゖ') {
			// 全角ひらがな
			int ix = (c - 'ぁ') * 2;

			return HALF_KANA.substring(ix, ix + 2).trim();
		} 
		if ('ァ' <= c && c <= 'ヶ') {
			// 全角カタカナ
			int ix = (c - 'ァ') * 2;

			return HALF_KANA.substring(ix, ix + 2).trim();
		} 
		return str;
	}
}
