$(function (){
    $('#name-change').click( function() {
        $('#profile_ori_name').toggle();
        $('#profile_cha_name').toggle();
        if( $(this).val() == '변경' ) {
          $(this).val('취소');
        }
        else {
          $(this).val('변경');
        }
    });

    $('#desc-change').click( function() {
        $('#profile_ori_desc').toggle();
        $('#profile_cha_desc').toggle();
        if( $(this).val() == '변경' ) {
          $(this).val('취소');
        }
        else {
          $(this).val('변경');
        }
    });
});