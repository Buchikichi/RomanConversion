package to.kit;

import org.springframework.boot.SpringApplication;

import to.kit.conversion.controller.ConversionController;

/**
 * アプリケーションメイン.
 *
 * @author H.Sasai
 */
public final class AppMain {
	/**
	 * メイン.
	 *
	 * @param args 引数
	 */
	public static void main(String[] args) {
		Object[] controllers = { ConversionController.class };

		SpringApplication.run(controllers, args);
	}
}
