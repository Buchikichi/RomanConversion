package to.kit.conversion.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import to.kit.conversion.RomanConverter;
import to.kit.conversion.data.ConversionResponse;
import to.kit.conversion.form.ConversionForm;

/**
 * 変換コントローラー.
 * @author H.Sasai
 */
@Controller
@EnableAutoConfiguration
public final class ConversionController {
	/**
	 * Hello.
	 * @return hello
	 */
	@RequestMapping("/hello")
	@ResponseBody
	public String hello() {
		return "hello!!!";
	}

	/**
	 * 変換.
	 * @param form フォーム
	 * @return 変換結果
	 */
	@RequestMapping("/convert")
	@ResponseBody
	public ConversionResponse convert(@ModelAttribute ConversionForm form) {
		String roman = form.getRoman();
		ConversionResponse response = new ConversionResponse();
		RomanConverter conv = RomanConverter.getInstance();
		String kana = conv.convert(roman);

		response.setResult("OK");
		response.setKana(kana);
		return response;
	}
}
