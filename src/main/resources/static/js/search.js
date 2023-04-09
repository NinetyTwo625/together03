/*검색 탭*/
$(function() {
	$('.tabcontent > div').hide();
	$('.tabnav a').click(function() {
		$('.tabcontent > div').hide().filter(this.hash).fadeIn();
		$('.tabnav a').removeClass('active');
		$(this).addClass('active');
		return false;
	}).filter(':eq(0)').click();
	$(document).mouseup(function(e) {
		if($(".modal-search").has(e.target).length === 0) {
			$(".modal-search").css("display", "none");
		}
	})
});

/*모달*/
function popup(obj, hashtag) {
	$(".sl__item__contents__tag").remove();

	let hashtagList = hashtag.split(",");

	hashtagList.forEach((hashtag) => {
		$(".sl__item__contents__tag__list").append(`<div class="sl__item__contents__tag pointer"
			onclick="location.href='/search?keyword=${hashtag.slice(1)}'">${hashtag}</div>`);
	});

	$(obj).css("display", "flex");
}