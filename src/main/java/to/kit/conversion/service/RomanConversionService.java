package to.kit.conversion.service;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import to.kit.util.NameUtils;
import to.kit.util.RomanConverter;

@Service
public class RomanConversionService {
	public static Stream<String> letters(final String text) {
		if (text == null) {
			return null;
		}
		final int len = text.length();
		final Iterator<String> it = new Iterator<String>() {
			private int ix = 0;

			@Override
			public boolean hasNext() {
				return this.ix < len;
			}

			@Override
			public String next() {
				if (len < this.ix) {
					throw new NoSuchElementException();
				}
				char c = text.charAt(this.ix++);

				if (this.ix < len && 'ｶ' <= c && c <= 'ﾎ' && !('ﾅ' <= c && c <= 'ﾉ')) {
					char next = text.charAt(this.ix);

					if (next == 'ﾞ' || next == 'ﾟ') {
						this.ix++;
						return String.valueOf(c) + String.valueOf(next);
					}
				}
				return String.valueOf(c);
			}
		};
		return StreamSupport.stream(Spliterators.spliteratorUnknownSize(it, Spliterator.ORDERED | Spliterator.NONNULL),
				false);
	}

    public String convert(String text, String... mapper) {
		RomanConverter conv = RomanConverter.getInstance();
		String kana = conv.convert(text);
		StringBuilder buff = new StringBuilder();

		letters(kana).parallel().map(NameUtils::toKatakana).forEachOrdered(buff::append);
		return buff.toString();
	}
}
