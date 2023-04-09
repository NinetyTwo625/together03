/**
	2. 스토리 페이지
	(1) 스토리 로드하기
	(2) 스토리 스크롤 페이징하기
	(3) 좋아요, 안좋아요
	(4) 댓글쓰기
	(5) 댓글삭제
 */

let principalId = $("#principalId").val();
let page = 0;

// (1) 스토리 로드하기
function storyLoad() {
	$.ajax({
		url: `/api/image?page=${page}`,
		dataType: "json"
	}).done(resp => {
		console.log(resp);
		resp.data.content.forEach((image) => {
			let item = getStoryItem(image);
			$("#storyList").append(item);
		});

		//로그인과 동시에 이벤트 등록
		notification();
	}).fail(error => {
		console.log(error);
	});
}

//notification
function notification() {
	let eventSource = new EventSource("http://localhost:80/sub");

	eventSource.addEventListener("notification", function(event) {
		let message = event.data;

		let notification_content = document.getElementById('notification-content');
		let notification_container = document.getElementById('notification-container');

		notification_content.textContent = message;

		notification_container.classList.add('show');
		setTimeout(() => {
			notification_container.classList.remove('show');
		}, 2000);
	});

	eventSource.addEventListener("error", function(event) {
		eventSource.close();
	});
}

storyLoad();

function getStoryItem(image) {
	let item = `<div class="story-list__item">
	<div class="sl__item__header">
		<div>
			<img class="profile-image" src="/upload/${image.user.profile_image_url}"
				onerror="this.src='/images/person.jpeg'" />
		</div>
		<div class="pointer" onclick="location.href='/user/${image.user.id}'">${image.user.name}</div>
	</div>

	<div class="sl__item__img">
		<img src="/upload/${image.post_image_url}" />
	</div>

	<div class="sl__item__contents">
		<div class="sl__item__contents__icon">
			<button>`;

	if (image.likesState)
		item += `<i class="fas fa-heart active" id="storyLikeIcon-${image.id}" onclick="toggleLike(${image.id})"></i>`;
	else item += `<i class="far fa-heart" id="storyLikeIcon-${image.id}" onclick="toggleLike(${image.id})"></i>`;

	item += `
			</button>

			<span class="like"><b id="storyLikeCount-${image.id}">${image.likesCount}</b>likes</span>
		</div>

		<div class="sl__item__contents__content">
			<p>${image.caption}</p>
		</div>

		<div class="sl__item__contents__tag__list">`;

	image.hashtagList.forEach((hashtag) => {
		item += `<div class="sl__item__contents__tag" onclick="location.href='/search?keyword=${hashtag.slice(1)}'">${hashtag}</div>`
	});

	item += `</div>

		<div class="story__comment__list" id="storyCommentList-${image.id}">`

	image.comments.forEach((comment) => {
		item +=
			`<div class="sl__item__contents__comment" id="storyCommentItem-${comment.id}">
						<p>
							<b class="pointer" onclick="location.href='/user/${comment.user.id}'">
								${comment.user.name} :</b> ${comment.content}
						</p>`;

		if (principalId == comment.user.id) {
			item +=
				`<button onclick="deleteComment(${comment.id})">
							<i class="fas fa-times"></i>
						</button>`;
		}

		item += `</div>`;
	});

	item += `
		</div>

		<div class="sl__item__input">
			<input type="text" placeholder="댓글 달기..." id="storyCommentInput-${image.id}" />
			<button type="button" onClick="addComment(${image.id})">게시</button>
		</div>

	</div>
</div><br>`;

	return item;
}

// (2) 스토리 스크롤 페이징하기
$(window).scroll(() => {
	let checkScroll = window.innerHeight + window.scrollY;
	if (checkScroll >= document.body.offsetHeight) {
		page++;
		storyLoad();
	}
});


// (3) 좋아요, 안좋아요
function toggleLike(imageId) {
	let likeIcon = $(`#storyLikeIcon-${imageId}`);
	if (likeIcon.hasClass("far")) {
		$.ajax({
			type: "POST",
			url: `/api/image/${imageId}/likes`,
			dataType: "json"
		}).done(resp => {
			$(`#storyLikeCount-${imageId}`).text(Number($(`#storyLikeCount-${imageId}`).text()) + 1);

			likeIcon.addClass("fas");
			likeIcon.addClass("active");
			likeIcon.removeClass("far");
		}).fail(error => {
			console.log(error);
		});
	} else {
		$.ajax({
			type: "DELETE",
			url: `/api/image/${imageId}/likes`,
			dataType: "json"
		}).done(resp => {
			$(`#storyLikeCount-${imageId}`).text(Number($(`#storyLikeCount-${imageId}`).text()) - 1);

			likeIcon.removeClass("fas");
			likeIcon.removeClass("active");
			likeIcon.addClass("far");
		}).fail(error => {
			console.log(error);
		});
	}
}

// (4) 댓글쓰기
function addComment(imageId) {

	let commentInput = $(`#storyCommentInput-${imageId}`);
	let commentList = $(`#storyCommentList-${imageId}`);

	let data = {
		imageId: imageId,
		content: commentInput.val()
	}

	if (data.content === "") {
		alert("댓글을 작성해주세요!");
		return;
	}

	$.ajax({
		type: "POST",
		url: "/api/comment",
		data: JSON.stringify(data),
		contentType: "application/json; charset=utf-8",
		dataType: "json"
	}).done(resp => {
		let content =
			`<div class="sl__item__contents__comment" id="storyCommentItem-${resp.data.id}">
			    <p>
			      <b class="pointer" onclick="location.href='/user/${resp.data.user.id}'">${resp.data.user.name} :</b>
			      ${resp.data.content}
			    </p>
			    <button onclick="deleteComment(${resp.data.id})"><i class="fas fa-times"></i></button>
			</div>`;
		commentList.prepend(content);
	}).fail(error => {
		console.log(error);
	});

	commentInput.val("");
}

// (5) 댓글 삭제
function deleteComment(commentId) {
	$.ajax({
		type: "DELETE",
		url: `/api/comment/${commentId}`,
		dataType: "json"
	}).done(resp => {
		$(`#storyCommentItem-${commentId}`).remove();
	}).fail(error => {
		console.log(error);
	});
}

/* 상태창, 알림창 스크롤 따라오기 */
$(document).ready(function() {
	let currentStatusPosition = parseInt($(".story-status").css("top"));
	let currentNotificationPosition = parseInt($(".notification-container").css("top"));

	$(window).scroll(function() {
		let position = $(window).scrollTop();
		$(".story-status").stop().animate({ "top": position + currentStatusPosition + "px" }, 500);
		$(".notification-container").stop().animate({ "top": position + currentNotificationPosition + "px" }, 500);
	});
});