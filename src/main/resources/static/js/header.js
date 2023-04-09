let input = document.getElementById("search");
let relative_tab = $(".relative-tab")[0];

input.addEventListener("keyup", async (e) => {
	let keyword = $("#search").val();

	/* 검색 상자가 비어있거나 ESC 키를 누를 경우 */
	if(!keyword || e.key === "Escape") {
		$("#relative-keyword-account > ul").remove();
		$("#relative-keyword-tag > ul").remove();
		relative_tab.style.display = "none";

		return;
	}

	$.ajax({
		type: "GET",
		url: `/api/relative/${keyword}`
	}).done(resp => {
		let relative_keyword_account = $("#relative-keyword-account");
		let relative_keyword_tag = $("#relative-keyword-tag");

		$("#relative-keyword-account > ul").remove();
		$("#relative-keyword-tag > ul").remove();
		relative_tab.style.display = "none";

		let ul_account = document.createElement("ul");
		let ul_tag = document.createElement("ul");

		resp.data.relativeUserDto.forEach((user) => {
			let li = document.createElement("li");
			li.className = "relative-keyword-box";
			li.onclick = function() {
				location.href=`/user/${user.id}`;
			};
			li.innerHTML =
				`<div class="flex">
					<div class="relative-keyword-image">
						<img class="profile-image" src="/upload/${user.profile_image_url}"
							onerror="this.src='/images/person.jpeg'" />
					</div>
					<div class="relative-keyword-name">${user.name}</div>
				</div>`
			ul_account.append(li);
		});

		let tags = [];
		resp.data.relativeTagDto.forEach((image) => {
			let tagList = image.hashtag.split(",");
			tagList.forEach((tag) => {
				if(tag.startsWith("#" + keyword)) {
					tags.push(tag);
				}
			});
		});

		/* 중복 제거 */
		let set = new Set(tags);
		let uniqueTags = [...set];

		uniqueTags.forEach((tag) => {
			tag = tag.slice(1); 	// # 제거
			let li = document.createElement("li");
			li.className = "relative-keyword-box";
			li.onclick = function() {
				location.href=`/search?keyword=${tag}`;
			};
			li.innerHTML =
				`<div>#${tag}</div>`
			ul_tag.append(li);
		});

		relative_keyword_account.append(ul_account);
		relative_keyword_tag.append(ul_tag);

		relative_keyword_account[0].style.display = "block";
		relative_keyword_tag[0].style.display = "block";
		relative_tab.style.display = "block";
	}).fail(error => {
		console.log(error);
	});
});

/* 마우스 포인터로 다른 영역을 클릭할 경우 연관 검색어 창이 닫힘 */
document.addEventListener("click", e => {
    if(relative_tab.style.display === "block") {
        relative_tab.style.display = "none";
    }
});

$(function() {
	$('.relative-tabcontent > div').hide();
	$('.relative-tabnav a').click(function() {
		$('.relative-tabcontent > div').hide().filter(this.hash).fadeIn();
		$('.relative-tabnav a').removeClass('active');
		$(this).addClass('active');
		return false;
	}).filter(':eq(0)').click();
});