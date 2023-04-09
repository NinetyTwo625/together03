$(function() {
	$("#uploadBtn").on("click", function() {
		upload();
	});
});

function upload() {
	let form = $("#uploadForm")[0];
	let formData = new FormData(form);

	let hashtagList = document.getElementsByClassName("hashtag-value");
	for(let i = 0; i < hashtagList.length; i++) {
		formData.append("hashtagList[" + i + "]", hashtagList[i].innerText);
	}

	$.ajax({
		type: "POST",
		url: `/api/upload`,
		data: formData,
		contentType: false,
		processData: false
	}).done(resp => {
		console.log(resp);

		location.href=`http://localhost:80/user/${resp.data}`
	}).fail(error => {
		console.log(error);
	});
}


// 해시 태그 추가, 삭제 로직
function addHashtag() {
	let hashtag = $("#hashtag").val();
	let item = getHashtagItem(hashtag);
	$("#hashtagList").append(item);
}

function removeHashtag(hashtag) {
	document.getElementById(hashtag).remove();
}

function getHashtagItem(hashtag) {
	let item =
		`<div class="hashtag" id="${hashtag}">
			<span class="hashtag-value">#${hashtag}</span>
			<button type="button" onclick="removeHashtag('${hashtag}')">×</button>
		</div>`

	return item;
}

// 스토리 이미지 업로드를 위한 사진 선택 로직
function imageChoose(obj) {
	let f = obj.files[0];

	if (!f.type.match("image.*")) {
		alert("이미지를 등록해야 합니다.");
		return;
	}

	let reader = new FileReader();
	reader.onload = (e) => {
		$("#imageUploadPreview").attr("src", e.target.result);
	}
	reader.readAsDataURL(f); // 이 코드 실행시 reader.onload 실행됨.
}