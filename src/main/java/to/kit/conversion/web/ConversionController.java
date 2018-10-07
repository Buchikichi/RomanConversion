package to.kit.conversion.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import to.kit.conversion.form.request.ConversionForm;
import to.kit.conversion.form.response.ConversionResponse;
import to.kit.conversion.service.RomanConversionService;

/**
 * 変換コントローラー.
 * @author H.Sasai
 */
@Controller
@EnableAutoConfiguration
public final class ConversionController {
	@Autowired
	private RomanConversionService romanConversionService;

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
	public ConversionResponse convert(@RequestBody ConversionForm form) {
		String roman = form.getRoman();
		ConversionResponse response = new ConversionResponse();
		String kana = this.romanConversionService.convert(roman, form.getConversionTypes());

		response.setResult(true);
		response.setKana(kana);
		return response;
	}
}
