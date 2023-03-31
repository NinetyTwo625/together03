<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<link rel="stylesheet" href="story.css">

<main class="main">
	<section class="container">
		<article class="story-list">
			<div class="story-list__item">
				<div class="sl__item__header">
					<div>
						<img class="profile-image" src="/upload/${detailDto.profileImage}"
							onerror="this.src='/images/person.png'"/>

					</div>

					<div>${detailDto.name}</div>
				</div>
			
				<div class="sl__item__img">
					<img src="/upload/${detailDto.postImage}" />
				</div>
			
					<div class="sl__item">
                		<div class="sl__item__contents">
                			<div class="sl__item__contents__icon">
                				<button>
                		        	<i class="far fa-heart" id="storyLikeIcon-${detailDto.imageId}" onclick="toggleLike(${detailDto.imageId})"></i>
                				</button>
                			</div>
                			<span class="like"><b id="storyLikeCount-${detailDto.imageId}">${detailDto.likeCount}</b> likes</span>
                			<div class="sl__item__contents__content">
                				<p>${detailDto.caption}</p>
                			</div>
                			<div id="storyCommentList-${detailDto.imageId}">
                			    <div class="sl__item__contents__comment" id="storyCommentItems-${comment.id}">
                			        <p><b>${comment.name} :</b> ${comment.content}</p>
                			    </div>
                			</div>
                			<div class="sl__item__input">
                			    <input type="text" placeholder="댓글달기..." id="storyCommentInput-${detailDto.imageId}"/>
                			    <button type="button" onclick="addComment(${detailDto.imageId})">게시</button>
                			</div>
                		</div>
                	</div>
				</div>
			</div>

		</article>
	</section>
</main>
<script src="/js/story.js"></script>