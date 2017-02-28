document.addEventListener('DOMContentLoaded', function() {
	new AppMain();
});

class AppMain {
	constructor() {
		this.roman = $('#roman');
		this.roman.keyup(()=>{this.convert()});
	}

	convert() {
		var val = this.roman.val();
		var data = {roman: val};

		$.ajax({
			type: 'post',
			url: 'convert',
			dataType: 'json',
			data: data,
		}).then((data)=>{
//			console.log('then');
//			console.log(data);
			this.setResult(data.kana);
		});
	}

	setResult(kana) {
		$('#kana').val(kana);
	}
}
