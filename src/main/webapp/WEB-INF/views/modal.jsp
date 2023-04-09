<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="modal-search" id="modal-search-${image.id}">
	<div class="modal">
		<div class="story-list__item">
			<div class="sl__item__header">
				<div>
					<img class="profile-image" src="/upload/${image.user.profile_image_url}" onerror="this.src='/images/person.png'">
				</div>
				<div class="pointer" onclick="location.href='/user/${image.user.id}'">${image.user.name}</div>
			</div>

			<div class="sl__item__img">
				<img src="/upload/${image.post_image_url}">
			</div>

			<div class="sl__item__contents">
				<div class="sl__item__contents__icon">
					<c:set var="likesState" value="false" />
					<c:forEach var="likes" items="${image.likes}">
						<c:if test="${likes.user.id eq principal.user.id}">
							<c:set var="likesState" value="true" />
						</c:if>
					</c:forEach>

					<c:choose>
						<c:when test="${likesState}">
							<button>
								<i class="fas fa-heart active" id="storyLikeIcon-${image.id}" onclick="toggleLike(${image.id})"></i>
							</button>
						</c:when>
						<c:otherwise>
							<button>
								<i class="far fa-heart" id="storyLikeIcon-${image.id}" onclick="toggleLike(${image.id})"></i>
							</button>
						</c:otherwise>
					</c:choose>

					<span class="like"><b id="storyLikeCount-${image.id}">${image.likes.size()}</b>likes</span>
				</div>

				<div class="sl__item__contents__content">
					<p>${image.caption}</p>
				</div>

				<div class="sl__item__contents__tag__list"></div>

				<div class="story__comment__list" id="storyCommentList-${image.id}">
					<c:forEach var="comment" items="${image.comments}">
						<div class="sl__item__contents__comment" id="storyCommentItem-${comment.id}">
							<p>
								<b  class="pointer" onclick="location.href='/user/${comment.user.id}'">${comment.user.name} :</b>
								${comment.content}
							</p>
							<c:if test="${principal.user.id eq comment.user.id}">
								<button onclick="deleteComment(${comment.id})">
									<i class="fas fa-times"></i>
								</button>
							</c:if>
						</div>
					</c:forEach>
				</div>

				<div class="sl__item__input">
					<input type="text" placeholder="댓글 달기..." id="storyCommentInput-${image.id}" />
					<button type="button" onClick="addComment(${image.id})">게시</button>
				</div>

			</div>
		</div>
	</div>
</div>