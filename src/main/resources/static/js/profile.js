$('a.desc_change').on('click', function(e){
	e.preventDefault();
	var $parent = $(this).closest('div');
	$parent.find('span').hide();
	$parent.find('form.pf_desc').show();
	$parent.find('a.desc_change_cancel').show();
	$(this).hide();
});

$('a.desc_change_cancel').on('click', function(e){
	e.preventDefault();
	var $parent = $(this).closest('div');
	$parent.find('span').show();
	$parent.find('form.pf_desc').hide();
	$parent.find('a.desc_change').show();
	$(this).hide();
});

function pf_name() {
	if($('#pf_name').css('display') == 'none'){
		$('#pf_name').show();
	}else{
		$('#pf_name').hide();
	};
};

function pf_desc() {
	if($('#pf_desc').css('display') == 'none'){
		$('#pf_desc').show();
	}else{
		$('#pf_desc').hide();
	};
};