<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="layout/header.jsp"%>

<main class="main" style="height: 290px;">
	<section class="container">
		<!--전체 리스트 시작-->
		<article class="search-list" id="searchList">

			<div class="tab">
				<ul class="tabnav">
					<li><a href="#person">사람</a></li>
					<li><a href="#tag">태그</a></li>
				</ul>
				<div class="tabcontent">
					<div id="person" align="center">
						<div class="search-summary">
							<div class="search-title" align="center">사람</div>
							<div class="search-count" align="center">${users.size()}명</div>
						</div>

						<c:forEach var="user" items="${users}">
							<div class="flex" style="width: 400px;">
								<img class="search-profile-img" src="/upload/${user.profile_image_url}" onerror="this.src='/images/person.png'" />
								<div class="search-profile">
									<div class="search-profile-item">
										<h5 class="pointer" onclick="location.href='/user/${user.id}'" align="left">${user.name}</h5>
										<div class="search-profile-content">${user.bio}</div>
									</div>
								</div>
							</div>
							<br>
						</c:forEach>
					</div>
					<div id="tag">
						<div class="search-summary">
							<div class="search-title" align="center">태그</div>
							<div class="search-count" align="center">${images.size()}개</div>
						</div>

						<section id="tab-content">
							<!--게시물컨컨테이너-->
							<div class="profileContainer">
								<!--그냥 감싸는 div (지우면이미지커짐)-->
								<div id="tab-1-content" class="tab-content-item show">
									<!--게시물컨 그리드배열-->
									<div class="tab-1-content-inner">

										<!--아이템들-->
										<c:forEach var="image" items="${images}">
											<div class="img-box" onclick="popup('#modal-search-${image.id}', '${image.hashtag}')">
												<a href=""> <img src="/upload/${image.post_image_url}" />
												</a>
												<div class="comment">
													<a href="#" class=""> <i class="fas fa-heart"></i><span>${image.likes.size()}</span>
													</a>
												</div>
											</div>
										</c:forEach>
										<!--아이템들end-->

									</div>
								</div>
							</div>
						</section>
					</div>
				</div>
			</div>

		</article>
	</section>

	<c:forEach var="image" items="${images}">
		<%@ include file="modal.jsp"%>
	</c:forEach>

	<script src="/js/story.js"></script>
	<script src="/js/search.js"></script>
</main>
</body>
</html>