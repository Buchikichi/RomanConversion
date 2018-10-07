document.addEventListener('DOMContentLoaded', function() {
	new AppMain();
});

class AppMain {
	constructor() {
		this.roman = $('[name=roman]');
		this.roman.keyup(()=>this.convert());
		$('[name=conversion]').change(()=>this.convert());
	}

	convert() {
		var type = $('[name=conversion]:checked').val();
		var data = {
			roman: this.roman.val(),
			conversionTypes: ['RomanConversion', type],
		};

		$.ajax({
			type: 'post',
			url: 'convert',
			headers: {'Content-Type':'application/json'},
			dataType: 'json',
			data: JSON.stringify(data),
		}).then((data)=>{
//			console.log('then');
//			console.log(data);
			this.setResult(data.kana);
		});
	}

	setResult(kana) {
		$('[name=result]').val(kana);
	}
}
